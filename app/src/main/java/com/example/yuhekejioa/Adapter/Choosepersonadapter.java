package com.example.yuhekejioa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.yuhekejioa.Bean.ChoosepersonBean;

import com.example.yuhekejioa.Bean.SonBean;
import com.example.yuhekejioa.R;

import java.util.HashMap;
import java.util.List;

public class Choosepersonadapter extends BaseAdapter {
    Context context;
    List<ChoosepersonBean.DataBean> list;
    private static HashMap<Integer, Boolean> isSelected;//用来控制checkBox的选中情况
    private MyCallBack myCallBack;

    public Choosepersonadapter(Context context, List<ChoosepersonBean.DataBean> list) {
        this.context = context;
        this.list = list;
        isSelected = new HashMap<Integer, Boolean>();
        initdata();
    }

    private void initdata() {
        for (int i = 0; i < list.size(); i++) {
            isSelected.put(i, false);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.secondlevel, null);
            holder = new ViewHolder();
            holder.tv = (TextView) view.findViewById(R.id.name);
            holder.checkBox = view.findViewById(R.id.box);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv.setText(list.get(i).getNickName());
        ChoosepersonBean.DataBean dataBean = list.get(i);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSelected.get(i)) {
                    isSelected.put(i, false);
                    setIsSelected(isSelected);
                    myCallBack.removeSelete(dataBean);

                } else {
                    isSelected.put(i, true);
                    setIsSelected(isSelected);
                    myCallBack.getSelete(dataBean);
                }
            }
        });
        // 根据isSelected来设置checkbox的选中状况
        holder.checkBox.setChecked(getIsSelected().get(i));
        return view;
    }

    public interface MyCallBack {
        void getSelete(ChoosepersonBean.DataBean sonBean);

        void removeSelete(ChoosepersonBean.DataBean sonBean);
    }

    public void setCallBack(MyCallBack myCallBack) {
        this.myCallBack = myCallBack;
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        Choosepersonadapter.isSelected = isSelected;
    }


    public class ViewHolder {
        TextView tv;
        CheckBox checkBox;
    }
}
