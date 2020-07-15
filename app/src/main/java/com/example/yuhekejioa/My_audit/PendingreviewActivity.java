package com.example.yuhekejioa.My_audit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuhekejioa.Adapter.PendingAdapter;
import com.example.yuhekejioa.Bean.PendingBean;
import com.example.yuhekejioa.R;
import com.example.yuhekejioa.Utils.Constant;
import com.example.yuhekejioa.Utils.NetworkUtils;
import com.example.yuhekejioa.Utils.SpacesItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PendingreviewActivity extends AppCompatActivity {

    private int id;//任务单id
    private TextView numbering;//任务编号
    private TextView current_time1;//发起时间
    private TextView sponsor_name;//发起人
    private TextView choosedepartment_text;//接收部门
    private TextView receiver_text;//接收人
    private TextView enddate_text;//结束时间
    private TextView editText;//任务描述
    private EditText yuheedittext;//补充说明
    private Button button_submit;//提交按钮
    private ImageView back;
    private RecyclerView recyclerView;//附件展示
    private TextView text_there;

    private List<PendingBean> list = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //Android避免进入一页面后EditText自动弹出软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pendingreview2);
        Intent intent = getIntent();
        id = intent.getIntExtra("taskId", 0);


        numbering = findViewById(R.id.numbering);
        current_time1 = findViewById(R.id.current_time1);
        sponsor_name = findViewById(R.id.sponsor_name);
        choosedepartment_text = findViewById(R.id.choosedepartment_text);
        receiver_text = findViewById(R.id.receiver_text);
        enddate_text = findViewById(R.id.enddate_text);
        editText = findViewById(R.id.editText);
        yuheedittext = findViewById(R.id.yuheedittext);
        button_submit = findViewById(R.id.button_submit);
        back = findViewById(R.id.back);

        recyclerView = findViewById(R.id.recyclerview);
        text_there = findViewById(R.id.text_there);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        initrequest();
    }

    //接口请求
    private void initrequest() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("taskId", String.valueOf(id));
        NetworkUtils.sendPost(Constant.ip +"/app/task/getTask", hashMap, this, new NetworkUtils.HttpCallback() {
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
                        final String taskNo = data.getString("taskNo");//任务编号
                        final String createTime = data.getString("createTime");//发起时间
                        final String addNickName = data.getString("addNickName");//发起人
                        final String receiveDept = data.getString("receiveDept");//接收部门
                        final String receiveNickName = data.getString("receiveNickName");//接收人
                        final String updateTime = data.getString("updateTime");//结束时间
                        final String taskDescribe = data.getString("taskDescribe");//任务描述
                        //接口实体类中的集合
                        JSONArray sysFilesSponsor = data.getJSONArray("sysFilesSponsor");
                        for (int i = 0; i < sysFilesSponsor.length(); i++) {
                            //集合中的实体类
                            JSONObject jsonObject = sysFilesSponsor.getJSONObject(i);
                            String name = jsonObject.getString("name");//文件名字
                            PendingBean pendingBean = new PendingBean();
                            pendingBean.setName(name);
                            list.add(pendingBean);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //添加数据
                                numbering.setText(taskNo);
                                current_time1.setText(createTime);
                                sponsor_name.setText(addNickName);
                                receiver_text.setText(receiveNickName);
                                enddate_text.setText(updateTime);
                                editText.setText(taskDescribe);
                                choosedepartment_text.setText(receiveDept);


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
            }
        });
    }
}
