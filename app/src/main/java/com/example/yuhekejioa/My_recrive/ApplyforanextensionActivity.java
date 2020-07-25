package com.example.yuhekejioa.My_recrive;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.yuhekejioa.My_Initiated.AcceptancefailedActivity;
import com.example.yuhekejioa.R;
import com.example.yuhekejioa.Utils.Constant;
import com.example.yuhekejioa.Utils.LoadingDialog;
import com.example.yuhekejioa.Utils.NetworkUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

//我发起的-------申请延期
public class ApplyforanextensionActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView back;
    private TextView numbering;//任务编号
    private TextView currentdate;//当前日期
    private TextView originaltime;//原地时间
    private TextView yanqitime;//申请延期时间
    private EditText yuheedittext;//延期原因输入框
    private EditText percentage;//当前完成度输入框
    private Button button_submit;//提交按钮

    private TimePickerView pvTime;//时间类
    private String taskNo;
    private String wantFinishTiem;
    private int id;
    private Dialog loadingDialog;

    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdaily);
        Intent intent = getIntent();
        taskNo = intent.getStringExtra("taskNo");//任务编号
        wantFinishTiem = intent.getStringExtra("wantFinishTiem");//原定时间
        id = intent.getIntExtra("id", 0);
        intiview();

    }

    private void intiview() {
        back = findViewById(R.id.back);
        numbering = findViewById(R.id.numbering);
        currentdate = findViewById(R.id.currentdate);
        originaltime = findViewById(R.id.originaltime);
        yanqitime = findViewById(R.id.yanqitime);
        yuheedittext = findViewById(R.id.yuheedittext);
        percentage = findViewById(R.id.percentage);
        button_submit = findViewById(R.id.button_submit);

        numbering.setText(taskNo);
        originaltime.setText(wantFinishTiem);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        currentdate.setText(simpleDateFormat.format(date));


        back.setOnClickListener(this);
        yanqitime.setOnClickListener(this);
        button_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back://返回按钮
                ApplyforanextensionActivity.this.finish();
                break;
            case R.id.yanqitime://选择延期时间
                if (pvTime != null) {
                    pvTime.show(view);//弹出时间选择器，传递参数过去，回调的时候则可以绑定此view
                }
                initTimePicker();
                break;
            case R.id.button_submit://提交按钮
                initsubmit();
                break;
        }
    }

    //时间选择器
    private void initTimePicker() {
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                yanqitime.setText(getTime(date));
                yanqitime.setTextColor(Color.parseColor("#ff000000"));
                Log.i("pvTime", "onTimeSelect");

            }
        }).setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
            @Override
            public void onTimeSelectChanged(Date date) {
                Log.i("pvTime", "onTimeSelectChanged");
            }
        })
                .setType(new boolean[]{true, true, true, true, true, true})
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);

            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    //提交按钮
    private void initsubmit() {
        String edit_yuheedittext = yuheedittext.getText().toString();//延期原因
        if (TextUtils.isEmpty(edit_yuheedittext)) {
            Toast.makeText(this, "请填写延期原因", Toast.LENGTH_SHORT).show();
            return;
        }
        String edit_percentage = percentage.getText().toString();
        if (TextUtils.isEmpty(edit_percentage)) {
            Toast.makeText(this, "请填写当前完成度", Toast.LENGTH_SHORT).show();
            return;
        }

        String new_time = yanqitime.getText().toString();//申请延期时间

        if (new_time.equals("请选择")) {
            Toast.makeText(this, "请选择申请延期时间", Toast.LENGTH_SHORT).show();
            return;
        }
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.createLoadingDialog(ApplyforanextensionActivity.this, "正在加载中...");
            loadingDialog.show();
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("delayReason", edit_yuheedittext);
        hashMap.put("newTime", new_time);
        hashMap.put("progress", edit_percentage);
        hashMap.put("taskId", String.valueOf(id));
        NetworkUtils.sendPost(Constant.ip + "/app/taskReceive/saveDelay", hashMap, this, new NetworkUtils.HttpCallback() {
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
                                Toast.makeText(ApplyforanextensionActivity.this, msg, Toast.LENGTH_SHORT).show();
                                ApplyforanextensionActivity.this.finish();
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
                                Toast.makeText(ApplyforanextensionActivity.this, msg, Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(ApplyforanextensionActivity.this, msg, Toast.LENGTH_SHORT).show();
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