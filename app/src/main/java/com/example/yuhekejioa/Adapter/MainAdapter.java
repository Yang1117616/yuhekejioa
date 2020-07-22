package com.example.yuhekejioa.Adapter;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yuhekejioa.Bean.Mainbean;
import com.example.yuhekejioa.My_Initiated.AcceptancefailedActivity;
import com.example.yuhekejioa.My_Initiated.AcceptingmodificationActivity;
import com.example.yuhekejioa.My_Initiated.ModificationinprogressActivity;
import com.example.yuhekejioa.My_Initiated.ModificationpendingActivity;
import com.example.yuhekejioa.My_Initiated.MyprocessingActivity;

import com.example.yuhekejioa.My_Initiated.TerminatedActivity;
import com.example.yuhekejioa.My_Initiated.TobeconfirmedActivity;
import com.example.yuhekejioa.My_Initiated.WaitActivity;
import com.example.yuhekejioa.My_Initiated.YanqideterminedActivity;
import com.example.yuhekejioa.My_recrive.CompletedActivity;

import com.example.yuhekejioa.My_recrive.DeterminedActivity;
import com.example.yuhekejioa.My_recrive.ExtensioninprogressActivity;
import com.example.yuhekejioa.My_recrive.FailedtopostponeActivity;
import com.example.yuhekejioa.My_recrive.My_AcceptingmodificationActivity;
import com.example.yuhekejioa.My_recrive.XiugaipendingActivity;
import com.example.yuhekejioa.R;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    Context context;
    List<Mainbean.DataBean> list;

    public MainAdapter(Context context, List<Mainbean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.recycler_layout, parent, false);
        ViewHolder holder = new ViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.status.setText(list.get(position).getType());
        holder.status_news.setText(list.get(position).getTaskTitle());
        holder.time.setText(list.get(position).getCreateTime());

        int taskStatus = list.get(position).getTaskStatus();
        int isRead = list.get(position).getIsRead();//红点状态

        int id = list.get(position).getId();//id
        String taskNo = list.get(position).getTaskNo();
        int taskId = list.get(position).getTaskId();
        int isUrgent = list.get(position).getIsUrgent();//是否是加急任务单
        if (taskStatus == 0) {
            holder.image_logo.setImageResource(R.drawable.image0);
            //跳转到我接收的--------已完成页面
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, CompletedActivity.class);
                    intent.putExtra("taskId", taskId);
                    intent.putExtra("id", id);
                    context.startActivity(intent);
                }
            });
        } else if (taskStatus == 1) {
            holder.image_logo.setImageResource(R.drawable.image1);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MyprocessingActivity.class);
                    intent.putExtra("taskId", taskId);
                    intent.putExtra("id", id);
                    context.startActivity(intent);
                }
            });
        } else if (taskStatus == 2) {
            //判断是否是加急任务单
            if (isUrgent == 0) {
                holder.image_logo.setImageResource(R.drawable.image2);
            } else if (isUrgent == 1) {
                holder.image_logo.setImageResource(R.drawable.image_isurgent);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //跳转到我接收的--------等待确定页面
                    Intent intent = new Intent(context, DeterminedActivity.class);
                    intent.putExtra("idx", id);
                    intent.putExtra("id", taskId);
                    intent.putExtra("confirmType", 1);
                    intent.putExtra("isUrgent",isUrgent);
                    context.startActivity(intent);
                }
            });

        } else if (taskStatus == 7) {
            holder.image_logo.setImageResource(R.drawable.image7);
            //跳转到 我发起的-------等待验收界面
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, WaitActivity.class);
                    intent.putExtra("taskId", taskId);
                    intent.putExtra("id", id);
                    context.startActivity(intent);
                }
            });
        } else if (taskStatus == 13) {
            holder.image_logo.setImageResource(R.drawable.image13);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                //跳转到和 我发起的 我接收的 详情页面一致
                public void onClick(View view) {
                    Intent intent = new Intent(context, AcceptancefailedActivity.class);
                    intent.putExtra("taskId", taskId);
                    intent.putExtra("id", id);
                    context.startActivity(intent);
                }
            });
        } else if (taskStatus == 14) {
            //我接收的-----延期不通过
            holder.image_logo.setImageResource(R.drawable.image13);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, FailedtopostponeActivity.class);
                    intent.putExtra("taskId", taskId);
                    intent.putExtra("id", id);
                    context.startActivity(intent);
                }
            });
        } else if (taskStatus == 12) {
            //跳转到我接收的----延期进行中
            holder.image_logo.setImageResource(R.drawable.image12);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ExtensioninprogressActivity.class);
                    intent.putExtra("taskId", taskId);
                    intent.putExtra("id", id);
                    context.startActivity(intent);
                }
            });
        } else if (taskStatus == 21) {
            //跳转到我发起的=====修改进行中
            holder.image_logo.setImageResource(R.drawable.image21);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ModificationinprogressActivity.class);
                    intent.putExtra("taskId", taskId);
                    intent.putExtra("id", id);
                    context.startActivity(intent);
                }
            });
        } else if (taskStatus == 11) {
            //跳转到 我发起的--------延期待确定
            holder.image_logo.setImageResource(R.drawable.image11);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, YanqideterminedActivity.class);
                    intent.putExtra("taskId", taskId);
                    intent.putExtra("id", id);
                    context.startActivity(intent);
                }
            });
        } else if (taskStatus == 18) {
            //跳转到我发起的------修改待确认
            holder.image_logo.setImageResource(R.drawable.image21);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, XiugaipendingActivity.class);
                    intent.putExtra("taskId", id);
                    intent.putExtra("id", taskId);
                    intent.putExtra("confirmType", 2);
                    context.startActivity(intent);
                }
            });
        } else if (taskStatus == 6) {
            //跳转到我发起的-----已终止页面
            holder.image_logo.setImageResource(R.drawable.image6x);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, TerminatedActivity.class);
                    intent.putExtra("taskId", taskId);
                    intent.putExtra("id", id);
                    context.startActivity(intent);
                }
            });
        } else if (taskStatus == 20) {
            //跳转到接收的--------验收修改中
            holder.image_logo.setImageResource(R.drawable.image21);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, My_AcceptingmodificationActivity.class);
                    intent.putExtra("taskId", taskId);
                    intent.putExtra("id", id);
                    context.startActivity(intent);
                }
            });
        }

        //红点状态
        if (isRead == 0) {
            holder.image_red.setVisibility(View.VISIBLE);
        } else if (isRead == 1) {
            holder.image_red.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView status;
        private TextView status_news;
        private TextView time;
        private ImageView image_logo;
        private ImageView image_red;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            status = itemView.findViewById(R.id.status);
            status_news = itemView.findViewById(R.id.status_news);
            time = itemView.findViewById(R.id.time);
            image_logo = itemView.findViewById(R.id.image_logo);
            image_red = itemView.findViewById(R.id.image_red);
        }
    }
}
