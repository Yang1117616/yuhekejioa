package com.example.yuhekejioa.My_Initiated;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuhekejioa.R;
import com.example.yuhekejioa.Utils.Constant;
import com.example.yuhekejioa.Utils.NetworkUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

// 我发起的--------终止
public class TerminationActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView back;
    private TextView numbering;
    private TextView status;
    private EditText yuheedittext;
    private Button button_submit;
    private int taskId;
    private int inspected;
    private String taskNo;
    private String statusStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termination);
        Intent intent = getIntent();
        taskId = intent.getIntExtra("taskId", 0);//任务id
        inspected = intent.getIntExtra("inspected", 0);//任务状态
        taskNo = intent.getStringExtra("taskNo");//任务编号
        statusStr = intent.getStringExtra("statusStr");
        initview();
    }

    private void initview() {
        back = findViewById(R.id.back);
        numbering = findViewById(R.id.numbering);
        status = findViewById(R.id.status);
        yuheedittext = findViewById(R.id.yuheedittext);
        button_submit = findViewById(R.id.button_submit);
        back.setOnClickListener(this);
        button_submit.setOnClickListener(this);

        numbering.setText(taskNo);
        status.setText(statusStr);
    }

    @Override
    //点击事件
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                TerminationActivity.this.finish();
                break;
            case R.id.button_submit:
                initsubmit();
                break;
        }
    }

    //终止方法
    private void initsubmit() {

        String thereason = yuheedittext.getText().toString();
        if (TextUtils.isEmpty(thereason)) {
            Toast.makeText(this, "终止原因不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("taskId", String.valueOf(taskId));
        hashMap.put("stopReason", thereason);

        NetworkUtils.sendPost(Constant.ip + "/app/task/terminate", hashMap, this, new NetworkUtils.HttpCallback() {
            @Override
            public void onSuccess(JSONObject res) {
                //如果res为空的话就不进行下面的操作
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
                                Toast.makeText(TerminationActivity.this, msg, Toast.LENGTH_SHORT).show();
                                TerminationActivity.this.finish();
                            }
                        });
                    } else if (code==500){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(TerminationActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(TerminationActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
