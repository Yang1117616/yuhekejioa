
package com.example.yuhekejioa.My_recrive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuhekejioa.My_Initiated.DailyActivity;
import com.example.yuhekejioa.My_Initiated.YanqideterminedActivity;
import com.example.yuhekejioa.R;
import com.example.yuhekejioa.Utils.Constant;
import com.example.yuhekejioa.Utils.NetworkUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

//我接收的-----延期待确认界面
public class LookingforwardtoconfirmationActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView numbering;//任务编号
    private TextView current_time1;//发起时间
    private TextView sponsor_name;//发起人
    private TextView enddate_text;//结束时间
    private TextView editText;//任务描述
    private TextView editText1;//延期原因
    private Button button_view;//查看每日工作
    private ImageView back;
    private TextView degreeofcompletion;//当前完成度
    private TextView extensiontime;//申请延期时间
    private int taskId;
    private String taskNo;

    private TextView edit_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookingforwardtoconfirmation);
        Intent intent = getIntent();
        taskId = intent.getIntExtra("taskId", 0);
        initview();
        initwangluo();
    }

    private void initwangluo() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("taskId", String.valueOf(taskId));
        NetworkUtils.sendPost(Constant.ip + "/app/task/getTask", hashMap, this, new NetworkUtils.HttpCallback() {
            @Override
            public void onSuccess(JSONObject res) {
                //如果res为空的话就不进行下面的操作
                if (res == null || this == null) {
                    return;
                }
                try {
                    int code = res.getInt("code");
                    if (code == 200) {
                        JSONObject data = res.getJSONObject("data");
                        //任务编号
                        taskNo = data.getString("taskNo");
                        final String createTime = data.getString("createTime");//发起时间
                        final String addNickName = data.getString("addNickName");//发起人

                        final String updateTime = data.getString("wantFinishTiem");//结束时间
                        final String taskDescribe = data.getString("taskDescribe");//任务描述
                        final String title = data.getString("title");

                        JSONObject taskDelay = data.getJSONObject("taskDelay");//申请延期类
                        int progress = taskDelay.getInt("progress");//当前完成进度
                        String newTime = taskDelay.getString("newTime");//申请延期时间

                        String reason = taskDelay.getString("reason");//延期原因
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                numbering.setText(taskNo);
                                current_time1.setText(createTime);
                                sponsor_name.setText(addNickName);
                                enddate_text.setText(updateTime);
                                editText.setText(taskDescribe);
                                edit_title.setText(title);
                                degreeofcompletion.setText(progress + "%");
                                extensiontime.setText(newTime);
                                editText1.setText(reason);//延期原因
                            }
                        });
                    } else {
                        String msg = res.getString("msg");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LookingforwardtoconfirmationActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(LookingforwardtoconfirmationActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    private void initview() {
        numbering = findViewById(R.id.taskNo);
        current_time1 = findViewById(R.id.current_time);
        sponsor_name = findViewById(R.id.sponsor_name);
        enddate_text = findViewById(R.id.enddate_text);
        editText = findViewById(R.id.editText);
        editText1 = findViewById(R.id.editText1);//延期成果
        back = findViewById(R.id.back);
        button_view = findViewById(R.id.button_view);//查看每日工作
        degreeofcompletion = findViewById(R.id.degreeofcompletion);
        extensiontime = findViewById(R.id.extensiontime);
        edit_title = findViewById(R.id.edit_title);
        back.setOnClickListener(this);
        button_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                LookingforwardtoconfirmationActivity.this.finish();
                break;
            case R.id.button_view:
                Intent intent = new Intent(LookingforwardtoconfirmationActivity.this, DailyActivity.class);
                intent.putExtra("taskNo", taskNo);
                startActivity(intent);
                LookingforwardtoconfirmationActivity.this.finish();
                break;
        }
    }
}
