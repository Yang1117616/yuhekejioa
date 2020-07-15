package com.example.yuhekejioa.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yuhekejioa.Bean.MyInitiatedBean;
import com.example.yuhekejioa.My_audit.PendingreviewActivity;
import com.example.yuhekejioa.R;


import java.util.List;

public class MyInitiateAdapter extends RecyclerView.Adapter<MyInitiateAdapter.ViewHolder> {
    List<MyInitiatedBean.DataBean> list;
    Context context;

    public MyInitiateAdapter(Context context, List<MyInitiatedBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.myinitiate_layout, parent,false);
        ViewHolder holder = new ViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.taskNo.setText(list.get(position).getTaskNo());
        holder.addNickName.setText(list.get(position).getAddNickName());
        holder.wantFinishTiem.setText(list.get(position).getWantFinishTiem());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView taskNo;
        private TextView addNickName;
        private TextView wantFinishTiem;
        private Button button_audit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskNo = itemView.findViewById(R.id.taskNo);
            addNickName = itemView.findViewById(R.id.addNickName);
            wantFinishTiem = itemView.findViewById(R.id.wantFinishTiem);
            button_audit = itemView.findViewById(R.id.button_audit);

        }
    }
}
