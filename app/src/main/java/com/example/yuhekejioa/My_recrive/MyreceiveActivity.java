package com.example.yuhekejioa.My_recrive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.yuhekejioa.Adapter.MeInitiateXAdapter;
import com.example.yuhekejioa.Adapter.MyreceiveAdapter;
import com.example.yuhekejioa.Bean.MyreceiveBean;
import com.example.yuhekejioa.My_Initiated.MeInitiateActivity;
import com.example.yuhekejioa.My_Initiated.WaitActivity;
import com.example.yuhekejioa.R;
import com.example.yuhekejioa.Utils.Constant;
import com.example.yuhekejioa.Utils.LoadingDialog;
import com.example.yuhekejioa.Utils.MyLog;
import com.example.yuhekejioa.Utils.NetworkUtils;
import com.example.yuhekejioa.Utils.SpacesItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// 我接收的
public class MyreceiveActivity extends AppCompatActivity {


    private ImageView back;
    private RecyclerView recyclerView;
    private List<MyreceiveBean.DataBean> list = new ArrayList<>();
    private int pageNum = 1;
    private SmartRefreshLayout home_RefreshLayout;

    private RelativeLayout relative_no;
    private Dialog loadingDialog;
    private MyreceiveAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myreceive);
        initview();

    }

    private void initview() {
        back = findViewById(R.id.back);
        recyclerView = findViewById(R.id.recyclerview);
        home_RefreshLayout = findViewById(R.id.home_RefreshLayout);
        relative_no = findViewById(R.id.relative_no);//暂无数据布局


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        home_RefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                MyLog.e("刷新");
                pageNum = 1;
                initdata();
            }
        });
        home_RefreshLayout.setRefreshHeader(new ClassicsHeader(MyreceiveActivity.this));
        relative_no.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        //创建适配器
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MyreceiveActivity.this);

        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new MyreceiveAdapter(MyreceiveActivity.this, list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initdata();
    }


    private void initdata() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.createLoadingDialog(MyreceiveActivity.this, "正在加载中...");
            loadingDialog.show();
        }
        NetworkUtils.sendPost(Constant.ip + "/app/taskReceive/myReceiveTaskList", null, this, new NetworkUtils.HttpCallback() {
            @Override
            public void onSuccess(JSONObject res) {
                //如果res为空的话就不进行下面的操作
                if (res == null || this == null) {
                    return;
                }
                try {
                    int code = res.getInt("code");
                    String msg = res.getString("msg");
                    JSONArray data = res.getJSONArray("data");
                    if (data.length() > 0) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (loadingDialog != null) {
                                    loadingDialog.dismiss();
                                    loadingDialog = null;
                                }
                                relative_no.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                        });
                        //判断等于1的时候集合清除
                        if (String.valueOf(pageNum).equals("1")) {
                            list.clear();
                        }

                        for (int i = 0; i < data.length(); i++) {
                            JSONObject jsonObject = data.getJSONObject(i);
                            final String taskNo = jsonObject.getString("taskNo");//任务编号
                            String addNickName = jsonObject.getString("addNickName");//发起人
                            String wantFinishTiem = jsonObject.getString("wantFinishTiem");//结束时间
                            final int taskStatus = jsonObject.getInt("taskStatus");//状态码
                            int id = jsonObject.getInt("id");
//                            int inspected = jsonObject.getInt("inspected");
                            int canDelay = jsonObject.getInt("canDelay");

                            String title = jsonObject.getString("title");

                            MyreceiveBean.DataBean myreceiveBean = new MyreceiveBean.DataBean();
                            myreceiveBean.setAddNickName(addNickName);
                            myreceiveBean.setTaskNo(taskNo);
                            myreceiveBean.setWantFinishTiem(wantFinishTiem);
                            myreceiveBean.setTaskStatus(taskStatus);
                            myreceiveBean.setId(id);
                            myreceiveBean.setCanDelay(canDelay);
                            myreceiveBean.setTitle(title);
                            //    myreceiveBean.setInspected(inspected);//添加任务状态
                            list.add(myreceiveBean);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (code == 200) {
                                    if (list.size() > 0) {
                                        if (loadingDialog != null) {
                                            loadingDialog.dismiss();
                                            loadingDialog = null;
                                        }
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

                                if (loadingDialog != null) {
                                    loadingDialog.dismiss();
                                    loadingDialog = null;
                                }
                                relative_no.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.GONE);
                                home_RefreshLayout.closeHeaderOrFooter();
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
                            relative_no.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                            home_RefreshLayout.closeHeaderOrFooter();
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

                        if (loadingDialog != null) {
                            loadingDialog.dismiss();
                            loadingDialog = null;
                        }
                        relative_no.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
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
                        if (loadingDialog != null) {
                            loadingDialog.dismiss();
                            loadingDialog = null;
                        }
                        relative_no.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                        home_RefreshLayout.closeHeaderOrFooter();
                    }
                });
            }
        });
    }
}
