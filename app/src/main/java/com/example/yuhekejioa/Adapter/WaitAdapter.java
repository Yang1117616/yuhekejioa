package com.example.yuhekejioa.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yuhekejioa.Bean.WantBean;
import com.example.yuhekejioa.My_Initiated.ModifyActivity;
import com.example.yuhekejioa.My_Initiated.WaitActivity;
import com.example.yuhekejioa.R;

import java.util.List;

public class WaitAdapter extends RecyclerView.Adapter<WaitAdapter.ViewHolder> {
    Context context;
    List<WantBean.DataBean.SysFilesSponsorBean> list;
    List<String> strings;
    //私有属性
    private FileAdapter.OnItemClickListener onItemClickListener = null;

    private StringBuilder defile = new StringBuilder();
    private WaitAdapter.OnItemListenter mItemClickListener = null;


    public WaitAdapter(Context context, List<WantBean.DataBean.SysFilesSponsorBean> list, List<String> strings) {
        this.context = context;
        this.list = list;
        this.strings = strings;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.pendingadapter_layout, parent, false);
        ViewHolder holder = new ViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.filename.setText(list.get(position).getName());
        holder.filearm.setText(list.get(position).getFileSize() + "");
        String id = list.get(position).getId();
        //实现点击效果
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
        holder.image_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                defile.append(id).append(",");
                mItemClickListener.onItemClick(defile);
                //如果集合的长度大于0就删除点击的条目
                if (strings.size() > 0) {
                    for (int i = 1; i < strings.size(); i++) {
                        strings.remove(i);
                    }
                }
                list.remove(position);
                WaitAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView filename;
        private TextView filearm;
        private ImageView image_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            filename = itemView.findViewById(R.id.filename);
            filearm = itemView.findViewById(R.id.filearm);
            image_delete = itemView.findViewById(R.id.image_delete);
        }
    }

    //setter方法
    public void setOnItemClickListener(FileAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //回调接口
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    //接口回调
    public interface OnItemListenter {
        void onItemClick(StringBuilder s);
    }

    public void setOnStringClickListener(WaitAdapter.OnItemListenter mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

}
