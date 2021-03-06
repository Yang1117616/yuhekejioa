package com.example.yuhekejioa.My_Initiated;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.yuhekejioa.Adapter.MeInitiateXAdapter;
import com.example.yuhekejioa.Bean.MeInitiateBean;
import com.example.yuhekejioa.MainActivity;
import com.example.yuhekejioa.My_recrive.MyreceiveActivity;
import com.example.yuhekejioa.R;
import com.example.yuhekejioa.Utils.Constant;
import com.example.yuhekejioa.Utils.IsNetwork;
import com.example.yuhekejioa.Utils.LoadingDialog;
import com.example.yuhekejioa.Utils.MyLog;
import com.example.yuhekejioa.Utils.NetworkUtils;
import com.example.yuhekejioa.Utils.SpacesItemDecoration;
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

//我发起的
public class MeInitiateActivity extends AppCompatActivity {
    private RelativeLayout back;
    private RecyclerView recyclerView;
    private List<MeInitiateBean.DataBean.ListBean> list = new ArrayList<MeInitiateBean.DataBean.ListBean>();
    private SmartRefreshLayout home_RefreshLayout;
    private int pageNum = 1;
    private MeInitiateXAdapter adapter;
    private RelativeLayout relative_no;
    private Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_initiate);
        initview();
        //判断有无网络
        if (!new IsNetwork().isNetworkAvailable(MeInitiateActivity.this)) {
            Toast toast = Toast.makeText(MeInitiateActivity.this, "当前没有可用网络", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initdata();
    }

    private void initview() {
        back = findViewById(R.id.back);
        recyclerView = findViewById(R.id.recyclerview);
        home_RefreshLayout = findViewById(R.id.home_RefreshLayout);
        relative_no = findViewById(R.id.relative_no);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //下拉刷新 重新加载数据
        home_RefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                MyLog.e("刷新");
                pageNum = 1;
                initdata();
            }
        });

        home_RefreshLayout.setRefreshHeader(new ClassicsHeader(MeInitiateActivity.this));
        relative_no.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        //创建适配器
        //设置布局管理器

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MeInitiateActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MeInitiateXAdapter(MeInitiateActivity.this, list);
        adapter.setHasStableIds(true);
        recyclerView.setAdapter(adapter);
        recyclerView.getLayoutManager().setAutoMeasureEnabled(false);
        adapter.notifyDataSetChanged();
    }


    private void initdata() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.createLoadingDialog(MeInitiateActivity.this, "正在加载中...");
            loadingDialog.show();
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNum", String.valueOf(pageNum));
        hashMap.put("pageSize", String.valueOf(200));
        NetworkUtils.sendPost(Constant.ip + "/app/task/myAddTaskList", hashMap, MeInitiateActivity.this, new NetworkUtils.HttpCallback() {
            @Override
            public void onSuccess(JSONObject res) {
                //如果res为空的话就不进行下面的操作
                if (res == null || this == null) {
                    return;
                }
                try {
                    int code = res.getInt("code");
                    String msg = res.getString("msg");
                    JSONObject data = res.getJSONObject("data");
                    JSONArray list = data.getJSONArray("list");
                    //获取的接口中的集合
                  //  JSONArray data = res.getJSONArray("data");
                    if (list.length() > 0) {
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
                            MeInitiateActivity.this.list.clear();
                        }
                        for (int i = 0; i < list.length(); i++) {
                            JSONObject jsonObject = list.getJSONObject(i);
                            String taskNo = jsonObject.getString("taskNo");//任务编号
                            String addNickName = jsonObject.getString("addNickName");//发起人
                            String receiveNickName = jsonObject.getString("receiveNickName");//接收人
                            String wantFinishTiem = jsonObject.getString("wantFinishTiem");//结束时间
                            int taskStatus = jsonObject.getInt("taskStatus");//状态码
                            int id = jsonObject.getInt("id");
                            String title = jsonObject.getString("title");//任务单标题
                            //  String inspected = jsonObject.getString("inspected");//显示结果
                            String statusStr = jsonObject.getString("statusStr");
                            int canUpdate = jsonObject.getInt("canUpdate");
                            String taskDescribe = jsonObject.getString("taskDescribe");//任务描述
                            String receiveDept = jsonObject.getString("receiveDept");//研发部门
                            int isUrgent = jsonObject.getInt("isUrgent");//是否是加急任务单
                            int isFixed = jsonObject.getInt("isFixed");//是否是固定任务单


                            MeInitiateBean.DataBean.ListBean mereceiveBean = new MeInitiateBean.DataBean.ListBean();
                            mereceiveBean.setReceiveDept(receiveDept);
                            mereceiveBean.setAddNickName(addNickName);
                            mereceiveBean.setTaskDescribe(taskDescribe);
                            mereceiveBean.setReceiveNickName(receiveNickName);
                            mereceiveBean.setTaskNo(taskNo);
                            mereceiveBean.setWantFinishTiem(wantFinishTiem);
                            mereceiveBean.setTaskStatus(taskStatus);
                            mereceiveBean.setStatusStr(statusStr);
                            mereceiveBean.setTitle(title);
                            mereceiveBean.setId(id);
                            mereceiveBean.setIsUrgent(isUrgent);
                            mereceiveBean.setCanUpdate(canUpdate);
                            mereceiveBean.setIsFixed(isFixed);
                            MeInitiateActivity.this.list.add(mereceiveBean);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (code == 200) {
                                    if (MeInitiateActivity.this.list.size() > 0) {
                                        if (loadingDialog != null) {
                                            loadingDialog.dismiss();
                                            loadingDialog = null;
                                        }
                                        if (adapter != null) {
                                            adapter.notifyDataSetChanged();
                                        }
                                        home_RefreshLayout.closeHeaderOrFooter();
                                    }
                                } else if (code == 500) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(MeInitiateActivity.this, msg, Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    });
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