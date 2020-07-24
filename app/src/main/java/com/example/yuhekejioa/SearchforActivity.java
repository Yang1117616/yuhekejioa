package com.example.yuhekejioa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuhekejioa.Adapter.MeInitiateXAdapter;
import com.example.yuhekejioa.Adapter.SearchforAdapter;
import com.example.yuhekejioa.Bean.SearchforBean;
import com.example.yuhekejioa.My_Initiated.MeInitiateActivity;
import com.example.yuhekejioa.Utils.Constant;
import com.example.yuhekejioa.Utils.MyLog;
import com.example.yuhekejioa.Utils.NetworkUtils;
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
import java.util.HashMap;
import java.util.List;

// 首页-----搜索页面
public class SearchforActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout back;//返回按钮
    private EditText edit_searchfor;//输入框
    private ImageView image_delete;//输入框删除按钮
    private TextView text_searchfor;
    private SmartRefreshLayout home_RefreshLayout;
    private RecyclerView recyclerview;
    private int pageNum = 1;
    private String title;
    //默认创建数据显示集合
    private List<SearchforBean.DataBean.ListBean> list = new ArrayList<>();
    private SearchforAdapter adapter;
    private RelativeLayout relative_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchfor);
        initview();
    }


    private void initview() {
        back = findViewById(R.id.back);
        edit_searchfor = findViewById(R.id.edit_searchfor);
        image_delete = findViewById(R.id.image_delete);
        text_searchfor = findViewById(R.id.text_searchfor);
        home_RefreshLayout = findViewById(R.id.home_RefreshLayout);
        recyclerview = findViewById(R.id.recyclerview);
        relative_no = findViewById(R.id.relative_no);


        back.setOnClickListener(this);
        image_delete.setOnClickListener(this);
        text_searchfor.setOnClickListener(this);
        //下拉刷新
        home_RefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                MyLog.e("刷新");
                pageNum = 1;
                initData();
            }
        });
//        //上拉加载
//        home_RefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(RefreshLayout refreshlayout) {
//                MyLog.e("加载");
//                pageNum = pageNum + 1;
//                initData();
//            }
//        });
        home_RefreshLayout.setRefreshHeader(new ClassicsHeader(SearchforActivity.this));
        //     home_RefreshLayout.setRefreshFooter(new ClassicsFooter(SearchforActivity.this));
        relative_no.setVisibility(View.GONE);
        recyclerview.setVisibility(View.GONE);
        edit_searchfor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                title = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //更改完后的数据 长度如果等于0 删除按钮隐藏 长度不等于0 删除按钮显示
                if (editable.length() == 0) {
                    image_delete.setVisibility(View.GONE);
                } else {
                    image_delete.setVisibility(View.VISIBLE);
                }
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchforActivity.this);
        recyclerview.setLayoutManager(linearLayoutManager);
        recyclerview.setHasFixedSize(true);
        adapter = new SearchforAdapter(SearchforActivity.this, list);
        recyclerview.setAdapter(adapter);
    }


    //网络请求
    private void initData() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNum", String.valueOf(pageNum));
        hashMap.put("pageSize", String.valueOf(20));
        hashMap.put("title", title);
        NetworkUtils.sendPost(Constant.ip + "/app/index/search", hashMap, this, new NetworkUtils.HttpCallback() {
            @Override
            public void onSuccess(JSONObject res) {
                if (res == null || this == null) {
                    return;
                }
                try {
                    int code = res.getInt("code");
                    String msg = res.getString("msg");
                    JSONObject data = res.getJSONObject("data");//最外面的
                    JSONArray sysFilesSponsor = data.getJSONArray("list");

                    if (sysFilesSponsor.length() > 0) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                relative_no.setVisibility(View.GONE);
                                recyclerview.setVisibility(View.VISIBLE);
                            }
                        });
                        //判断等于1的时候集合清除
                        if (String.valueOf(pageNum).equals("1")) {
                            list.clear();
                        }
                        for (int i = 0; i < sysFilesSponsor.length(); i++) {
                            JSONObject jsonObject = sysFilesSponsor.getJSONObject(i);
                            String title = jsonObject.getString("title");//标题
                            String wantFinishTiem = jsonObject.getString("wantFinishTiem");//结束时间
                            int inspected = jsonObject.getInt("taskStatus");//状态码
                            String addNickName = jsonObject.getString("addNickName");//发起人
                            String receiveNickName = jsonObject.getString("receiveNickName");//接收人
                            int identity = jsonObject.getInt("identity");//接收人 发起人 状态码
                            String taskNo = jsonObject.getString("taskNo");//任务编号
                            String statusStr = jsonObject.getString("statusStr");//任务单状态
                            int id = jsonObject.getInt("id");
                            int canDelay = jsonObject.getInt("canDelay");
                            int isUrgent = jsonObject.getInt("isUrgent");
                            int canUpdate = jsonObject.getInt("canUpdate");
                            int isFixed = jsonObject.getInt("isFixed");
                            //添加要获取的数据
                            SearchforBean.DataBean.ListBean dataBean = new SearchforBean.DataBean.ListBean();
                            dataBean.setTitle(title);
                            dataBean.setWantFinishTiem(wantFinishTiem);
                            dataBean.setTaskStatus(inspected);
                            dataBean.setAddNickName(addNickName);
                            dataBean.setReceiveNickName(receiveNickName);
                            dataBean.setIdentity(identity);
                            dataBean.setTaskNo(taskNo);
                            dataBean.setStatusStr(statusStr);
                            dataBean.setId(id);
                            dataBean.setCanDelay(canDelay);
                            dataBean.setIsUrgent(isUrgent);
                            dataBean.setCanUpdate(canUpdate);
                            dataBean.setIsFixed(isFixed);
                            list.add(dataBean);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (code == 200) {
                                    if (list.size() > 0) {
                                        if (adapter != null) {
                                            adapter.notifyDataSetChanged();
                                        }
                                        home_RefreshLayout.closeHeaderOrFooter();
                                    }
                                } else if (code == 500) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(SearchforActivity.this, msg, Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                relative_no.setVisibility(View.VISIBLE);
                                recyclerview.setVisibility(View.GONE);
                                home_RefreshLayout.closeHeaderOrFooter();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            relative_no.setVisibility(View.VISIBLE);
                            recyclerview.setVisibility(View.GONE);
                            home_RefreshLayout.closeHeaderOrFooter();
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
                        relative_no.setVisibility(View.VISIBLE);
                        recyclerview.setVisibility(View.GONE);
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
                        recyclerview.setVisibility(View.GONE);
                        home_RefreshLayout.closeHeaderOrFooter();
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                SearchforActivity.this.finish();
                break;
            case R.id.image_delete://输入框删除按钮
                edit_searchfor.setText("");//删除输入框中输入的内容
                break;
            case R.id.text_searchfor://搜索按钮
                initData();
                break;
        }
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
