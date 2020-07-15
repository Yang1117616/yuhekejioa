package com.example.yuhekejioa.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yuhekejioa.Bean.DailyBean;
import com.example.yuhekejioa.My_Initiated.DailyActivity;
import com.example.yuhekejioa.R;

import java.util.List;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.ViewHolder> {
    Context context;
    List<DailyBean.DataBean> list;

    public DailyAdapter(Context context, List<DailyBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dailyadapter_layout, parent, false);
        ViewHolder holder = new ViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.time.setText(list.get(position).getCreateTime());
        holder.progress.setText(list.get(position).getProgress()+"");
        holder.jobContent.setText(list.get(position).getJobContent());
        holder.pb_progressbar.setProgress(list.get(position).getProgress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView time;
        private TextView progress;
        private TextView jobContent;
        private ProgressBar pb_progressbar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            progress = itemView.findViewById(R.id.progress);
            jobContent = itemView.findViewById(R.id.jobContent);
            pb_progressbar = itemView.findViewById(R.id.pb_progressbar);
        }
    }
}
