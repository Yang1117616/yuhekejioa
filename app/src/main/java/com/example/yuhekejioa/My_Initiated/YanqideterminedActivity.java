package com.example.yuhekejioa.My_Initiated;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yuhekejioa.R;
import com.example.yuhekejioa.Utils.Constant;
import com.example.yuhekejioa.Utils.NetworkUtils;
import com.example.yuhekejioa.WheelDialog.WheelDialogFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


//我发起的——----延期待确定
public class YanqideterminedActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView numbering;//任务编号
    private TextView current_time1;//发起时间
    private TextView sponsor_name;//发起人
    private TextView choosedepartment_text;//接收部门
    private TextView receiver_text;//接收人
    private TextView enddate_text;//结束时间
    private TextView editText;//任务描述
    private TextView editText1;//延期原因
    private TextView opinionselection;//请选择验收结果
    private EditText yuheedittext;//补充说明
    private Button button_view;//查看每日工作
    private Button button_submit;//提交按钮
    private ImageView back;//返回按钮
    private TextView degreeofcompletion;//当前完成度
    private TextView extensiontime;//申请延期时间
    private int taskId;
    private String[] strings;//确认结果状态
    private String taskNo;

    private int num;//确认结果
    private int id;
    private String createTime;
    private String addNickName;
    private String receiveDept;
    private String receiveNickName;
    private String wantFinishTiem;
    private String taskDescribe;
    private String delayReason;
    private int progress;
    private String newTime;
    private int msgid;
   private  TextView edit_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yanqidetermined);
        Intent intent = getIntent();
        taskId = intent.getIntExtra("taskId", 0);
        msgid = intent.getIntExtra("id", 0);
        initview();
        initwangluo();

    }


    private void initview() {
        numbering = findViewById(R.id.taskNo);
        current_time1 = findViewById(R.id.current_time);
        sponsor_name = findViewById(R.id.sponsor_name);
        choosedepartment_text = findViewById(R.id.choosedepartment_text);
        receiver_text = findViewById(R.id.receiver_text);
        enddate_text = findViewById(R.id.enddate_text);
        editText = findViewById(R.id.editText);
        editText1 = findViewById(R.id.editText1);//任务 成果
        yuheedittext = findViewById(R.id.yuheedittext);
        button_submit = findViewById(R.id.button_submit);
        back = findViewById(R.id.back);
        opinionselection = findViewById(R.id.opinionselection);//请选择验收结果
        button_view = findViewById(R.id.button_view);//查看每日工作
        degreeofcompletion = findViewById(R.id.degreeofcompletion);//当前完成时间
        extensiontime = findViewById(R.id.extensiontime);//申请延期时间
         edit_title=findViewById(R.id.edit_title);


        back.setOnClickListener(this);//返回按钮
        opinionselection.setOnClickListener(this);//选择验收结果
        button_view.setOnClickListener(this);
        button_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                YanqideterminedActivity.this.finish();
                break;
            case R.id.opinionselection://选择验收结果
                showdialog();
                break;
            case R.id.button_view:
                Intent intent = new Intent(YanqideterminedActivity.this, DailyActivity.class);
                intent.putExtra("taskNo", taskNo);
                startActivity(intent);
                YanqideterminedActivity.this.finish();
                break;
            case R.id.button_submit:
                initsubmit();
                break;
        }
    }


    //提交
    private void initsubmit() {
        String text = yuheedittext.getText().toString();
        if (TextUtils.isEmpty(text)) {
            Toast.makeText(this, "请输入补充说明", Toast.LENGTH_SHORT).show();
            return;
        }
        String opinionselection_text = opinionselection.getText().toString();

        if (opinionselection_text.equals("通过")) {
            num = 0;
        } else if (opinionselection_text.equals("未通过")) {
            num = 1;
        }

        HashMap<String, String> hashMap1 = new HashMap<>();
        hashMap1.put("taskId", String.valueOf(taskId));
        hashMap1.put("notReason", text);
        hashMap1.put("isAdopt", String.valueOf(num));
        hashMap1.put("delayId", String.valueOf(id));

        NetworkUtils.sendPost(Constant.ip + "/app/task/delayConfirm", hashMap1, this, new NetworkUtils.HttpCallback() {
            @Override
            public void onSuccess(JSONObject res) {
                if (res == null || this == null) {
                    return;
                }
                try {
                    int code = res.getInt("code");
                    final String msg = res.getString("msg");
                    if (code == 200) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(YanqideterminedActivity.this, msg, Toast.LENGTH_SHORT).show();
                                YanqideterminedActivity.this.finish();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(YanqideterminedActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(YanqideterminedActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

    // 确认结果
    private void showdialog() {
        final Bundle bundle = new Bundle();
        bundle.putBoolean(WheelDialogFragment.DIALOG_BACK, false);
        bundle.putBoolean(WheelDialogFragment.DIALOG_CANCELABLE, false);
        bundle.putBoolean(WheelDialogFragment.DIALOG_CANCELABLE_TOUCH_OUT_SIDE, false);
        bundle.putString(WheelDialogFragment.DIALOG_LEFT, "取消");
        bundle.putString(WheelDialogFragment.DIALOG_RIGHT, "确定");
        bundle.putString(WheelDialogFragment.DIALOG_TITLE, "请选择");
        strings = new String[2];
        strings[0] = "通过";
        strings[1] = "未通过";
        bundle.putStringArray(WheelDialogFragment.DIALOG_WHEEL, strings);
        WheelDialogFragment dialogFragment = WheelDialogFragment.newInstance(WheelDialogFragment.class, bundle);
        dialogFragment.setWheelDialogListener(new WheelDialogFragment.OnWheelDialogListener() {
            @Override
            public void onClickLeft(WheelDialogFragment dialog, String value) {
                dialog.dismiss();
            }

            @Override
            public void onClickRight(WheelDialogFragment dialog, int value) {
                String s = strings[value];
                opinionselection.setText(s);
                opinionselection.setTextColor(Color.parseColor("#ff000000"));
                dialog.dismiss();
            }

            @Override
            public void onValueChanged(WheelDialogFragment dialog, String value) {
            }
        });
        dialogFragment.show(getSupportFragmentManager(), "");
    }

    //网络请求
    private void initwangluo() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("taskId", String.valueOf(taskId));
        hashMap.put("msgId", String.valueOf(msgid));
        NetworkUtils.sendPost(Constant.ip +"/app/task/getTask", hashMap, this, new NetworkUtils.HttpCallback() {

            @Override
            public void onSuccess(JSONObject res) {
                if (res == null || this == null) {
                    return;
                }
                try {
                    int code = res.getInt("code");
                    String msg = res.getString("msg");
                    if (code == 200) {
                        JSONObject data = res.getJSONObject("data");
                        taskNo = data.getString("taskNo");//任务编号
                        //发起时间
                        createTime = data.getString("createTime");
                        //发起人
                        addNickName = data.getString("addNickName");
                        //接收部门
                        receiveDept = data.getString("receiveDept");
                        //接收人
                        receiveNickName = data.getString("receiveNickName");
                        //结束时间
                        wantFinishTiem = data.getString("wantFinishTiem");
                        //任务描述
                        taskDescribe = data.getString("taskDescribe");
                        //延期原因
                        delayReason = data.getString("delayReason");

                       final String title = data.getString("title");


                        JSONObject taskDelay = data.getJSONObject("taskDelay");//延期类
                        //当前延期完成度
                        progress = taskDelay.getInt("progress");
                        //延期时间
                        newTime = taskDelay.getString("newTime");
                        id = taskDelay.getInt("id");//延期类id

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                numbering.setText(taskNo);
                                current_time1.setText(createTime);
                                sponsor_name.setText(addNickName);
                                receiver_text.setText(receiveNickName);
                                choosedepartment_text.setText(receiveDept);
                                editText.setText(taskDescribe);
                                enddate_text.setText(wantFinishTiem);
                                editText1.setText(delayReason);
                                degreeofcompletion.setText(progress + "%" + "");
                                extensiontime.setText(newTime);
                                edit_title.setText(title);
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(YanqideterminedActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                        new AlertDialog.Builder(YanqideterminedActivity.this)
                                .setMessage(msg)
                                .setPositiveButton("确定", null)
                                .show();
                        Toast.makeText(YanqideterminedActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
