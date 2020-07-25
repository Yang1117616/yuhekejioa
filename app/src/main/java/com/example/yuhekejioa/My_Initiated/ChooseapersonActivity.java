package com.example.yuhekejioa.My_Initiated;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yuhekejioa.Adapter.Choosepersonadapter;
import com.example.yuhekejioa.Bean.ChoosepersonBean;
import com.example.yuhekejioa.Bean.SonBean;
import com.example.yuhekejioa.R;
import com.example.yuhekejioa.Utils.Constant;
import com.example.yuhekejioa.Utils.LoadingDialog;
import com.example.yuhekejioa.Utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//选择人员
public class ChooseapersonActivity extends AppCompatActivity {

    private ImageView back;
    private ListView listView;
    private Button button_submit;
    private List<ChoosepersonBean.DataBean> list = new ArrayList<>();
    private Choosepersonadapter choosepersonadapter;
    private List<SonBean> list_son = new ArrayList<>();
    private int deptId;
    private Dialog loadingDialog;

    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooseaperson);
        Intent intent = getIntent();
        deptId = intent.getIntExtra("deptId", 0);

        //获取传过来的部门id
        initview();
        initwangluo();
    }

    private void initwangluo() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.createLoadingDialog(ChooseapersonActivity.this, "正在加载中...");
            loadingDialog.show();
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("deptId", String.valueOf(deptId));
        NetworkUtils.sendPost(Constant.ip + "/app/task/getReceiveUser", hashMap, this, new NetworkUtils.HttpCallback() {
            @Override
            public void onSuccess(JSONObject res) {
                if (res == null || this == null) {
                    return;
                }
                try {
                    int code = res.getInt("code");
                    String msg = res.getString("msg");
                    if (code == 200) {
                        JSONArray data = res.getJSONArray("data");
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject jsonObject = data.getJSONObject(i);
                            String nickName = jsonObject.getString("nickName");
                            String userNo = jsonObject.getString("userNo");
                            ChoosepersonBean.DataBean son = new ChoosepersonBean.DataBean();
                            son.setNickName(nickName);
                            son.setUserNo(userNo);
                            list.add(son);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (loadingDialog != null) {
                                    loadingDialog.dismiss();
                                    loadingDialog = null;
                                }
                                choosepersonadapter = new Choosepersonadapter(ChooseapersonActivity.this, list);
                                listView.setAdapter(choosepersonadapter);
                                choosepersonadapter.setCallBack(new Choosepersonadapter.MyCallBack() {

                                    @Override
                                    public void getSelete(ChoosepersonBean.DataBean sonBean) {
                                        Log.e("TAG", "getSelete:" + sonBean.getNickName());
                                        String nickName = sonBean.getNickName();
                                        String userNo = sonBean.getUserNo();
                                        SonBean sonBean1 = new SonBean();
                                        sonBean1.setName(nickName);
                                        sonBean1.setUserNo(userNo);
                                        list_son.add(sonBean1);
                                        choosepersonadapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    //移除
                                    public void removeSelete(ChoosepersonBean.DataBean sonBean) {
                                        //for循环 集合中添加的数据跟实体类中的一致就移除
                                        for (int i = 0; i < list_son.size(); i++) {
                                            if (list_son.get(i).getName().equals(sonBean.getNickName())) {
                                                list_son.remove(i);
                                                break;
                                            }
                                        }
                                        choosepersonadapter.notifyDataSetChanged();
                                    }
                                });
                            }
                        });
                    }else if(code==500){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (loadingDialog != null) {
                                    loadingDialog.dismiss();
                                    loadingDialog = null;
                                }
                                Toast.makeText(ChooseapersonActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(ChooseapersonActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void initview() {
        back = findViewById(R.id.back);
        listView = findViewById(R.id.listview);
        button_submit = findViewById(R.id.button_submit);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseapersonActivity.this.finish();
            }
        });
        //确定按钮
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseapersonActivity.this, InitiateActivity.class);
                intent.putExtra("listx", (Serializable) list_son);
                setResult(RESULT_OK, intent);
                finish();

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
