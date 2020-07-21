package com.example.yuhekejioa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuhekejioa.Bean.ChooseBean;

import com.example.yuhekejioa.Bean.ChoosepersonBean;
import com.example.yuhekejioa.R;

import java.util.HashMap;
import java.util.List;

public class MyExpandableListView extends BaseExpandableListAdapter {
    Context context;
    List<ChooseBean.DataBean> list_father;//父条目
    List<ChooseBean.DataBean.ChildrenBeanX> list_son;//子条目
    // 子列表checkBox的状态记录，数据结构与子列表数据一样
    private static HashMap<Integer, Boolean> isSelected;//用来控制checkBox的选中情况
    private MyExpandableListView.MyCallBack myCallBack;

    public MyExpandableListView(Context context, List<ChooseBean.DataBean> list_father, List<ChooseBean.DataBean.ChildrenBeanX> list_son) {
        super();
        this.context = context;
        this.list_father = list_father;
        this.list_son = list_son;
        isSelected = new HashMap<Integer, Boolean>();
        initdata();
    }
    private void initdata() {
        for (int i = 0; i < list_son.size(); i++) {
            isSelected.put(i, false);
        }
    }
    @Override
    public int getGroupCount() {
        return list_father.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return list_son.size();
    }

    @Override
    public Object getGroup(int i) {
        return i;
    }

    @Override
    public Object getChild(int i, int i1) {
        return i1;
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.firstclass, null);
            holder = new ViewHolder();
            holder.name = view.findViewById(R.id.name);
            holder.iv_group = view.findViewById(R.id.iv_group);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText(list_father.get(i).getDeptName());
        //判断展开的时候角标变化
        if (b) {
            holder.iv_group.setImageResource(R.drawable.image_down);
        } else {
            holder.iv_group.setImageResource(R.drawable.image_up);
        }

        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.secondlevel, null);
            holder = new ViewHolder();
            holder.name = view.findViewById(R.id.name);
            holder.box = view.findViewById(R.id.box);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.name.setText(list_son.get(i1).getDeptName());

        ChooseBean.DataBean.ChildrenBeanX childrenBeanX = list_son.get(i1);
        holder.box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSelected.get(i1)) {
                    isSelected.put(i1, false);
                    setIsSelected(isSelected);
                    myCallBack.removeSelete(childrenBeanX);

                } else {
                    isSelected.put(i1, true);
                    setIsSelected(isSelected);
                    myCallBack.getSelete(childrenBeanX);
                }
            }
        });
        // 根据isSelected来设置checkbox的选中状况
        holder.box.setChecked(getIsSelected().get(i1));


        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    public interface MyCallBack {
        void getSelete( ChooseBean.DataBean.ChildrenBeanX  sonBean);

        void removeSelete( ChooseBean.DataBean.ChildrenBeanX sonBean);
    }

    public void setCallBack(MyExpandableListView.MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        MyExpandableListView.isSelected = isSelected;
    }

    static class ViewHolder {
        TextView name;
        CheckBox box;
        ImageView iv_group;
    }

}
