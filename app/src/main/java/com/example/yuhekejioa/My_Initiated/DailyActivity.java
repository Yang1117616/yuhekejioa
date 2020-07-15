package com.example.yuhekejioa.My_Initiated;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.yuhekejioa.Adapter.DailyAdapter;
import com.example.yuhekejioa.Bean.DailyBean;
import com.example.yuhekejioa.My_recrive.MyreceiveActivity;
import com.example.yuhekejioa.R;
import com.example.yuhekejioa.Utils.Constant;
import com.example.yuhekejioa.Utils.MyLog;
import com.example.yuhekejioa.Utils.NetworkUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//  我发起的-----查看每日工作
public class DailyActivity extends AppCompatActivity {
    private SmartRefreshLayout home_RefreshLayout;
    private RecyclerView accomplish_List;
    private String taskNo;
    private ImageView back;
    private int pageNum = 1;
    private List<DailyBean.DataBean> list = new ArrayList<>();
    private RelativeLayout relative_no;
    private DailyAdapter adapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        Intent intent = getIntent();
        taskNo = intent.getStringExtra("taskNo");
        initview();
    }

    private void initview() {
        home_RefreshLayout = findViewById(R.id.home_RefreshLayout);//加载刷新页面
        accomplish_List = findViewById(R.id.accomplish_List);
        back = findViewById(R.id.back);
        relative_no = findViewById(R.id.relative_no);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //刷新网络请求
        home_RefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                MyLog.e("刷新");
                pageNum = 1;
                initdata();
            }
        });

        home_RefreshLayout.setRefreshHeader(new ClassicsHeader(DailyActivity.this));
        relative_no.setVisibility(View.GONE);
        accomplish_List.setVisibility(View.GONE);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DailyActivity.this);
        accomplish_List.setLayoutManager(linearLayoutManager);
        adapter = new DailyAdapter(DailyActivity.this, list);
        accomplish_List.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initdata();
    }

    //网络请求
    private void initdata() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("taskNo", taskNo);
        NetworkUtils.sendPost(Constant.ip + "/app/task/getTaskProgress", hashMap, this, new NetworkUtils.HttpCallback() {
            @Override
            public void onSuccess(JSONObject res) {
                //如果res为空的话就不进行下面的操作
                if (res == null || this == null) {
                    return;
                }
                try {
                    int code = res.getInt("code");
                    JSONArray data = res.getJSONArray("data");
                    if (data.length() > 0) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                relative_no.setVisibility(View.GONE);
                                accomplish_List.setVisibility(View.VISIBLE);
                            }
                        });
                        //判断等于1的时候集合清除
                        if (String.valueOf(pageNum).equals("1")) {
                            list.clear();
                        }
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject jsonObject = data.getJSONObject(i);//获取集合中的实体类
                            DailyBean.DataBean dataBean = new DailyBean.DataBean();
                            dataBean.setCreateTime(jsonObject.getString("createTime"));//时间
                            dataBean.setProgress(jsonObject.getInt("progress"));//进度
                            dataBean.setJobContent(jsonObject.getString("jobContent"));//任务内容
                            list.add(dataBean);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (code == 200) {
                                    if (list.size() > 0) {
                                        //适配器刷新
                                        if (adapter != null) {
                                            adapter.notifyDataSetChanged();
                                        }
                                        home_RefreshLayout.closeHeaderOrFooter();
                                    }

                                }
                            }
                        });

                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                relative_no.setVisibility(View.VISIBLE);
                                accomplish_List.setVisibility(View.GONE);
                                home_RefreshLayout.closeHeaderOrFooter();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    relative_no.setVisibility(View.VISIBLE);
                                    accomplish_List.setVisibility(View.GONE);
                                    home_RefreshLayout.closeHeaderOrFooter();
                                }
                            });
                        }
                    });
                }
            }

            @Override
            public void onError(final String msg) {
                super.onError(msg);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        relative_no.setVisibility(View.VISIBLE);
                        accomplish_List.setVisibility(View.GONE);
                        home_RefreshLayout.closeHeaderOrFooter();

                    }
                });
            }

            @Override
            public void onErrorD() {
                super.onErrorD();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        relative_no.setVisibility(View.VISIBLE);
                        accomplish_List.setVisibility(View.GONE);
                        home_RefreshLayout.closeHeaderOrFooter();
                    }
                });
            }
        });
    }
}
