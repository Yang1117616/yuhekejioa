package com.example.yuhekejioa.My_audit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yuhekejioa.Adapter.MyInitiateAdapter;
import com.example.yuhekejioa.Bean.MyInitiatedBean;
import com.example.yuhekejioa.My_Initiated.WaitActivity;
import com.example.yuhekejioa.R;
import com.example.yuhekejioa.Utils.Constant;
import com.example.yuhekejioa.Utils.NetworkUtils;
import com.example.yuhekejioa.Utils.SpacesItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MyAuditActivity extends AppCompatActivity {
    private ImageView back;
    private SmartRefreshLayout home_RefreshLayout;
    private RecyclerView accomplish_List;
    private List<MyInitiatedBean.DataBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_initiated);
        initView();
        initdata();
    }

    private void initdata() {

        NetworkUtils.sendPost(Constant.ip + "/app/taskAudit/myTaskList", null, MyAuditActivity.this, new NetworkUtils.HttpCallback() {
            @Override
            public void onSuccess(JSONObject res) {
                //如果res为空的话就不进行下面的操作
                if (res == null || this == null) {
                    return;
                }
                try {
                    int code = res.getInt("code");
                    if (code == 200) {
                        //获取的接口中的集合
                        JSONArray data = res.getJSONArray("data");
                        //循环遍历接口中的集合
                        for (int i = 0; i < data.length(); i++) {
                            //获取集合中的实体类
                            JSONObject jsonObject = data.getJSONObject(i);
                            String taskNo = jsonObject.getString("taskNo");//任务编号
                            String addNickName = jsonObject.getString("addNickName");//发起人
                            String wantFinishTiem = jsonObject.getString("wantFinishTiem");//结束时间
                            int id = jsonObject.getInt("id");//任务单id

                            MyInitiatedBean.DataBean dataBean = new MyInitiatedBean.DataBean();
                            dataBean.setTaskNo(taskNo);
                            dataBean.setAddNickName(addNickName);
                            dataBean.setWantFinishTiem(wantFinishTiem);
                            dataBean.setId(id);

                            list.add(dataBean);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //在子线程中创建适配器
                                //设置布局管理器
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyAuditActivity.this);
                                accomplish_List.setLayoutManager(linearLayoutManager);
                                //recyclerview设置每个item之间的间距
//                                int space = 6;
//                                accomplish_List.addItemDecoration(new SpacesItemDecoration(SpacesItemDecoration.px2dp(space)));
                                MyInitiateAdapter adapter = new MyInitiateAdapter(MyAuditActivity.this, list);
                                accomplish_List.setAdapter(adapter);
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
                        new AlertDialog.Builder(MyAuditActivity.this)
                                .setMessage(msg)
                                .setPositiveButton("确定", null)
                                .show();
                        Toast.makeText(MyAuditActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private void initView() {
        back = findViewById(R.id.back);
        home_RefreshLayout = findViewById(R.id.home_RefreshLayout);
        accomplish_List = findViewById(R.id.accomplish_List);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
