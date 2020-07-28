package com.example.yuhekejioa.My_recrive;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Time;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuhekejioa.My_Initiated.AcceptancefailedActivity;
import com.example.yuhekejioa.R;
import com.example.yuhekejioa.Utils.Constant;
import com.example.yuhekejioa.Utils.LoadingDialog;
import com.example.yuhekejioa.Utils.NetworkUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.HashMap;

// 我接收的-------汇报每日工作
public class ReportActivity extends AppCompatActivity {

    private int id;
    private String taskNo;
    private TextView numbering;
    private TextView current_time1;
    private EditText yuheedittext;
    private EditText percentage;
    private Button button_submit;
    private RelativeLayout back;
    private Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //Android避免进入一页面后EditText自动弹出软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Intent intent = getIntent();
        //获取的id
        id = intent.getIntExtra("id", 0);
        //任务编号
        taskNo = intent.getStringExtra("taskNo");
        initview();
    }

    private void initview() {
        numbering = findViewById(R.id.numbering);
        current_time1 = findViewById(R.id.current_time1);
        yuheedittext = findViewById(R.id.yuheedittext);
        percentage = findViewById(R.id.percentage);
        button_submit = findViewById(R.id.button_submit);
        back = findViewById(R.id.back);

        numbering.setText(taskNo);//需要获取赋值后的任务编号
        //获取系统当前时间
        long currentTime = System.currentTimeMillis();
        String timeNow = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(currentTime);
        current_time1.setText(timeNow);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submit();
            }
        });
    }

    private void submit() {
        String renwubianhao = numbering.getText().toString();
        String edit_yuhe = yuheedittext.getText().toString().trim();
        if (TextUtils.isEmpty(edit_yuhe)) {
            Toast.makeText(this, "请输入今日工作内容", Toast.LENGTH_SHORT).show();
            return;
        }
        String trim = percentage.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            Toast.makeText(this, "请输入当前完成度", Toast.LENGTH_SHORT).show();
            return;
        }
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.createLoadingDialog(ReportActivity.this, "正在加载中...");
            loadingDialog.show();
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("jobContent", edit_yuhe);
        hashMap.put("progress", trim);
        hashMap.put("taskId", String.valueOf(id));
        hashMap.put("taskNo", renwubianhao);

        NetworkUtils.sendPost(Constant.ip + "/app/taskReceive/saveProgress", hashMap, this, new NetworkUtils.HttpCallback() {
            @Override
            public void onSuccess(JSONObject res) {
                if (res == null || this == null) {
                    return;
                }
                try {
                    int code = res.getInt("code");
                    String msg = res.getString("msg");
                    if (code == 200) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (loadingDialog != null) {
                                    loadingDialog.dismiss();
                                    loadingDialog = null;
                                }
                                Toast.makeText(ReportActivity.this, msg, Toast.LENGTH_SHORT).show();
                                ReportActivity.this.finish();
                            }
                        });
                    } else if (code == 500) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (loadingDialog != null) {
                                    loadingDialog.dismiss();
                                    loadingDialog = null;
                                }
                                Toast.makeText(ReportActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (loadingDialog != null) {
                                loadingDialog.dismiss();
                                loadingDialog = null;
                            }
                        }
                    });
                }
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (loadingDialog != null) {
                            loadingDialog.dismiss();
                            loadingDialog = null;
                        }
                        Toast.makeText(ReportActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    //防止快速点击出现多个相同页面的问题
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (isFastDoubleClick()) {
                return true;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private long lastClickTime = System.currentTimeMillis();

    private boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (timeD >= 0 && timeD <= 1000) {
            return true;
        } else {
            lastClickTime = time;
            return false;
        }
    }
}
