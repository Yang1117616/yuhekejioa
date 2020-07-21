package com.example.yuhekejioa.My_Initiated;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.yuhekejioa.Adapter.FatherBean;
import com.example.yuhekejioa.Adapter.MyExpandableListView;
import com.example.yuhekejioa.Bean.ChooseBean;

import com.example.yuhekejioa.Bean.DepartmentBean;
import com.example.yuhekejioa.Bean.SonBean;
import com.example.yuhekejioa.R;
import com.example.yuhekejioa.Utils.Constant;
import com.example.yuhekejioa.Utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.List;

//我发起的-----选择部门
public class ChooseDepartmentActivity extends AppCompatActivity {
    private ImageView back;
    private ExpandableListView expandableListView;
    private Button button_submit;

    private List<ChooseBean.DataBean> list_father = new ArrayList<>();//父布局
    private List<ChooseBean.DataBean.ChildrenBeanX> list_son = new ArrayList<>();
    private String son_name;
    private MyExpandableListView adapter;

    private List<FatherBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_department);
        initview();
    }

    private void initview() {
        back = findViewById(R.id.back);
        expandableListView = findViewById(R.id.expandableListView);
        button_submit = findViewById(R.id.button_submit);
        // 去掉自带箭头，在一级列表中动态添加
        expandableListView.setGroupIndicator(null);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChooseDepartmentActivity.this.finish();
            }
        });
        //确定按钮
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initsubmit();
            }
        });

    }

    //获取的checkbox选中的数据
    private void initsubmit() {
        Intent intent = new Intent(ChooseDepartmentActivity.this, InitiateActivity.class);
        intent.putExtra("list", (Serializable) list);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initdata();
    }
    //网络请求
    private void initdata() {
        NetworkUtils.sendPost(Constant.ip + "/app/task/getDepts", null, this, new NetworkUtils.HttpCallback() {
            @Override
            public void onSuccess(JSONObject res) {
                if (res == null || this == null) {
                    return;
                }
                try {
                    int code = res.getInt("code");
                    if (code == 200) {
                        JSONObject data = res.getJSONObject("data");
                        JSONArray children = data.getJSONArray("children");//父内容
                        for (int i = 0; i < children.length(); i++) {
                            JSONObject jsonObject = children.getJSONObject(i);
                            String deptName = jsonObject.getString("deptName");//父内容
                            ChooseBean.DataBean choose_father = new ChooseBean.DataBean();
                            choose_father.setDeptName(deptName);
                            list_father.add(choose_father);//父布局添加
                            JSONArray son = jsonObject.getJSONArray("children");//子内容
                            for (int j = 0; j < son.length(); j++) {
                                JSONObject json_son = son.getJSONObject(j);
                                //子内容
                                son_name = json_son.getString("deptName");
                                int deptId = json_son.getInt("deptId");//部门id
                                ChooseBean.DataBean.ChildrenBeanX childrenBeanX = new ChooseBean.DataBean.ChildrenBeanX();
                                childrenBeanX.setDeptName(son_name);
                                childrenBeanX.setDeptId(deptId);
                                list_son.add(childrenBeanX);
                            }
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter = new MyExpandableListView(ChooseDepartmentActivity.this, list_father, list_son);
                                expandableListView.setAdapter(adapter);
                                expandableListView.expandGroup(0);//默认展开第一组
                                adapter.setCallBack(new MyExpandableListView.MyCallBack() {
                                    @Override
                                    public void getSelete(ChooseBean.DataBean.ChildrenBeanX sonBean) {
                                        String deptName = sonBean.getDeptName();
                                        int deptId = sonBean.getDeptId();
                                        FatherBean fatherBean = new FatherBean();
                                        fatherBean.setName(deptName);
                                        fatherBean.setDeptId(deptId);
                                        list.add(fatherBean);
                                        adapter.notifyDataSetChanged();

                                    }
                                    @Override  //移除
                                    public void removeSelete(ChooseBean.DataBean.ChildrenBeanX sonBean) {
                                        for (int i = 0; i < list.size(); i++) {
                                            if (list.get(i).getName().equals(sonBean.getDeptName())) {
                                                list.remove(i);
                                                break;
                                            }
                                        }
                                        adapter.notifyDataSetChanged();
                                    }
                                });

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
                        new AlertDialog.Builder(ChooseDepartmentActivity.this)
                                .setMessage(msg)
                                .setPositiveButton("确定", null)
                                .show();
                        Toast.makeText(ChooseDepartmentActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

//    //    //二级列表适配器
//    class MyExpandableListView extends BaseExpandableListAdapter {
//
//        @Override
//        public int getGroupCount() {
//            return list_father.size();
//        }
//
//        @Override
//        public int getChildrenCount(int i) {
//            return list_son.size();
//        }
//
//        @Override
//        public Object getGroup(int i) {
//
//            return list_father.get(i);
//        }
//
//        @Override//二级列表中单个item
//        public Object getChild(int i, int i1) {
//            return list_son.get(i1);
//        }
//
//        @Override
//        public long getGroupId(int i) {
//            return i;
//        }
//
//        @Override
//        public long getChildId(int i, int i1) {
//            return i1;
//        }
//
//        @Override
//        /*
//        每个item的id是否固定，一般为true
//         */
//        public boolean hasStableIds() {
//            return true;
//        }
//
//        /*
//         填充一级列表
//     * isExpanded 是否已经展开
//         */
//        public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
//            if (view == null) {
//                view = getLayoutInflater().inflate(R.layout.firstclass, null);
//            }
//            TextView tv_group = view.findViewById(R.id.name);
//            ImageView iv_group = view.findViewById(R.id.iv_group);
//            if (b) {
//                iv_group.setImageResource(R.drawable.image_down);
//            } else {
//                iv_group.setImageResource(R.drawable.image_up);
//            }
//            tv_group.setText(list_father.get(i).getDeptName());
//            return view;
//        }
//
//        @Override//填充二级列表
//        public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
//
//            if (view == null) {
//                view = getLayoutInflater().inflate(R.layout.secondlevel, null);
//            }
//            TextView tv = (TextView) view.findViewById(R.id.name);
//            CheckBox check = view.findViewById(R.id.box);
//            tv.setText(list_son.get(i1).getDeptName());
//            return view;
//        }
//
//        @Override
//        public boolean isChildSelectable(int i, int i1) {
//            return true;
//        }
//    }
}
