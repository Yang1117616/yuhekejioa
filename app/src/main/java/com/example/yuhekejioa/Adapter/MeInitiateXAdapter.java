package com.example.yuhekejioa.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yuhekejioa.Bean.MeInitiateBean;
import com.example.yuhekejioa.My_Initiated.AcceptancefailedActivity;
import com.example.yuhekejioa.My_Initiated.AcceptingmodificationActivity;
import com.example.yuhekejioa.My_Initiated.DailyActivity;
import com.example.yuhekejioa.My_Initiated.ModificationinprogressActivity;
import com.example.yuhekejioa.My_Initiated.ModificationpendingActivity;
import com.example.yuhekejioa.My_Initiated.ModifyActivity;
import com.example.yuhekejioa.My_Initiated.MyExtensioninprogressActivity;
import com.example.yuhekejioa.My_Initiated.MycompletedActivity;
import com.example.yuhekejioa.My_Initiated.MyprocessingActivity;
import com.example.yuhekejioa.My_Initiated.NotreceivedActivity;
import com.example.yuhekejioa.My_Initiated.PostponementActivity;
import com.example.yuhekejioa.My_Initiated.TerminatedActivity;
import com.example.yuhekejioa.My_Initiated.TerminationActivity;
import com.example.yuhekejioa.My_Initiated.TobeconfirmedActivity;
import com.example.yuhekejioa.My_Initiated.WaitActivity;
import com.example.yuhekejioa.My_Initiated.YanqideterminedActivity;
import com.example.yuhekejioa.My_recrive.FailedtopostponeActivity;
import com.example.yuhekejioa.R;

import java.util.List;

public class MeInitiateXAdapter extends RecyclerView.Adapter<MeInitiateXAdapter.ViewHolder> {
    Context context;
    List<MeInitiateBean.DataBean> list;


    public MeInitiateXAdapter(Context context, List<MeInitiateBean.DataBean> list) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.meinitiate_layout, parent, false);
        ViewHolder holder = new ViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.taskNo.setText(list.get(position).getTitle());//任务编号
        holder.addNickName.setText(list.get(position).getReceiveNickName());//接收人
        holder.wantFinishTiem.setText(list.get(position).getWantFinishTiem());//结束时间
        int taskStatus = list.get(position).getTaskStatus();//状态码
        final int id = list.get(position).getId();//任务单id
        final String taskNo = list.get(position).getTaskNo();//任务单编号
        int isUrgent = list.get(position).getIsUrgent();//是否是加急任务单
        String statusStr = list.get(position).getStatusStr();
        int canUpdate = list.get(position).getCanUpdate();//是否可以修改
        int isFixed = list.get(position).getIsFixed();//是否是固定任务单

        if (taskStatus == 2) {
            holder.taskStatus.setImageResource(R.drawable.imageview2);
            holder.modify.setText("修改");
            holder.termination.setText("终止");
            holder.button_examine.setVisibility(View.GONE);
            holder.modify.setVisibility(View.VISIBLE);
            holder.termination.setVisibility(View.VISIBLE);
            //判断是否是加急任务单
            if (isUrgent == 0) {
                holder.image_expedited.setVisibility(View.GONE);
            } else if (isUrgent == 1) {
                holder.image_expedited.setVisibility(View.VISIBLE);
                //如果是加急状态下修改和终止按钮隐藏
                holder.modify.setVisibility(View.GONE);
                holder.termination.setVisibility(View.GONE);
            }
            //判断是否是固定任务单
            if (isFixed == 0) {
            } else if (isFixed == 1) {
                holder.modify.setVisibility(View.GONE);
                holder.termination.setVisibility(View.GONE);
            }
            //跳转到我发起的 修改页面
            holder.modify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ModifyActivity.class);
                    intent.putExtra("taskId", id);
                    context.startActivity(intent);
                }
            });
            //跳转到我发起的终止页面
            holder.termination.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, TerminationActivity.class);
                    intent.putExtra("taskId", id);
                    intent.putExtra("taskNo", taskNo);
                    intent.putExtra("inspected", 0);
                    intent.putExtra("statusStr", statusStr);
                    context.startActivity(intent);
                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, TobeconfirmedActivity.class);
                    intent.putExtra("taskId", id);
                    intent.putExtra("isUrgent", isUrgent);
                    intent.putExtra("isFixed", isFixed);
                    context.startActivity(intent);
                }
            });
        } else if (taskStatus == 1) {
            holder.taskStatus.setImageResource(R.drawable.imageview1);
            holder.modify.setText("修改");
            holder.termination.setText("终止");
            holder.button_examine.setText("查看每日工作");
            holder.modify.setVisibility(View.VISIBLE);
            holder.termination.setVisibility(View.VISIBLE);
            holder.button_examine.setVisibility(View.VISIBLE);
            //判断是否是加急任务单
            if (isUrgent == 0) {
                holder.image_expedited.setVisibility(View.GONE);
                //跳转到修改页面
                holder.modify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, ModifyActivity.class);
                        intent.putExtra("taskId", id);
                        context.startActivity(intent);
                    }
                });
                //跳转到查看每日工作界面
                holder.button_examine.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, DailyActivity.class);
                        intent.putExtra("taskNo", taskNo);
                        context.startActivity(intent);
                    }
                });
                // 跳转到终止页面
                holder.termination.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, TerminationActivity.class);
                        intent.putExtra("taskId", id);
                        intent.putExtra("taskNo", taskNo);
                        intent.putExtra("inspected", 0);
                        intent.putExtra("statusStr", statusStr);
                        context.startActivity(intent);
                    }
                });
            } else if (isUrgent == 1) {
                holder.image_expedited.setVisibility(View.VISIBLE);
                holder.modify.setVisibility(View.VISIBLE);
                holder.termination.setVisibility(View.GONE);
                holder.button_examine.setVisibility(View.GONE);
                holder.modify.setText("查看每日工作");
                holder.modify.setBackgroundResource(R.drawable.button_backgroud_blue);
                holder.modify.setTextColor(Color.parseColor("#ff006bff"));
                holder.modify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, DailyActivity.class);
                        intent.putExtra("taskNo", taskNo);
                        context.startActivity(intent);
                    }
                });
            }
            if (isFixed == 0) {
            } else if (isFixed == 1) {
                holder.modify.setVisibility(View.VISIBLE);
                holder.termination.setVisibility(View.GONE);
                holder.button_examine.setVisibility(View.GONE);
                holder.modify.setText("查看每日工作");
                holder.modify.setBackgroundResource(R.drawable.button_backgroud_blue);
                holder.modify.setTextColor(Color.parseColor("#ff006bff"));
                holder.modify.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(context, DailyActivity.class);
                        intent.putExtra("taskNo", taskNo);
                        context.startActivity(intent);
                    }
                });
            }
            //跳转到我发起的进行中
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MyprocessingActivity.class);
                    intent.putExtra("taskId", id);
                    intent.putExtra("statusStr", statusStr);
                    intent.putExtra("isUrgent", isUrgent);
                    intent.putExtra("isFixed", isFixed);
                    context.startActivity(intent);
                }
            });
        } else if (taskStatus == 7) {
            holder.taskStatus.setImageResource(R.drawable.imageview7);
            holder.modify.setText("验收");
            holder.modify.setBackgroundResource(R.drawable.button_backgroud_blue);
            holder.modify.setTextColor(Color.parseColor("#ff006bff"));
            holder.termination.setText("查看每日工作");
            holder.termination.setBackgroundResource(R.drawable.button_backgroud_blue);
            holder.termination.setTextColor(Color.parseColor("#ff006bff"));
            holder.button_examine.setVisibility(View.GONE);
            holder.modify.setVisibility(View.VISIBLE);
            holder.termination.setVisibility(View.VISIBLE);

            //判断是否是加急任务单
            if (isUrgent == 0) {
                holder.image_expedited.setVisibility(View.GONE);
            } else if (isUrgent == 1) {
                holder.image_expedited.setVisibility(View.VISIBLE);
            }
            //判断是否是固定任务单
            if (isFixed == 0) {
            } else if (isFixed == 1) {
                holder.button_examine.setVisibility(View.GONE);
                holder.modify.setVisibility(View.VISIBLE);
                holder.termination.setVisibility(View.VISIBLE);
            }
            //跳转到查看每日工作页面
            holder.termination.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DailyActivity.class);
                    intent.putExtra("taskNo", taskNo);
                    context.startActivity(intent);
                }
            });
            //跳转到等待验收页面
            holder.modify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, WaitActivity.class);
                    intent.putExtra("taskId", id);
                    context.startActivity(intent);
                }
            });
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, WaitActivity.class);
                    intent.putExtra("taskId", id);
                    intent.putExtra("isUrgent", isUrgent);
                    intent.putExtra("isFixed", isFixed);
                    context.startActivity(intent);
                }
            });
        } else if (taskStatus == 0) {
            holder.taskStatus.setImageResource(R.drawable.imageview0);
            holder.termination.setVisibility(View.GONE);
            holder.button_examine.setVisibility(View.GONE);
            holder.modify.setText("查看每日工作");
            holder.modify.setBackgroundResource(R.drawable.button_backgroud_blue);
            holder.modify.setTextColor(Color.parseColor("#ff006bff"));
            holder.modify.setVisibility(View.VISIBLE);
            //判断是否是加急任务单
            if (isUrgent == 0) {
                holder.image_expedited.setVisibility(View.GONE);
            } else if (isUrgent == 1) {
                holder.image_expedited.setVisibility(View.VISIBLE);
            }
            //判断是否是固定任务单
            if (isFixed == 0) {
            } else if (isFixed == 1) {
                holder.termination.setVisibility(View.GONE);
                holder.button_examine.setVisibility(View.GONE);
                holder.modify.setVisibility(View.VISIBLE);
            }
            //跳转到查看每日工作界面
            holder.modify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DailyActivity.class);
                    intent.putExtra("taskNo", taskNo);
                    context.startActivity(intent);
                }
            });
            //跳转到我发送的  已完成
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MycompletedActivity.class);
                    intent.putExtra("taskId", id);
                    intent.putExtra("isUrgent", isUrgent);
                    intent.putExtra("isFixed", isFixed);
                    context.startActivity(intent);
                }
            });
        } else if (taskStatus == 13) {
            holder.taskStatus.setImageResource(R.drawable.imageview13);
            holder.modify.setText("查看每日工作");
            holder.modify.setBackgroundResource(R.drawable.button_backgroud_blue);
            holder.modify.setTextColor(Color.parseColor("#ff006bff"));
            holder.modify.setVisibility(View.VISIBLE);
            holder.termination.setVisibility(View.GONE);
            holder.button_examine.setVisibility(View.GONE);
            holder.modify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DailyActivity.class);
                    intent.putExtra("taskNo", taskNo);
                    context.startActivity(intent);
                }
            });

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, AcceptancefailedActivity.class);
                    intent.putExtra("taskId", id);
                    context.startActivity(intent);
                }
            });
        } else if (taskStatus == 11) {
            holder.taskStatus.setImageResource(R.drawable.imageview11);
            holder.modify.setText("查看每日工作");
            holder.modify.setBackgroundResource(R.drawable.button_backgroud_blue);
            holder.modify.setTextColor(Color.parseColor("#ff006bff"));
            holder.modify.setVisibility(View.VISIBLE);
            holder.button_examine.setVisibility(View.GONE);
            holder.termination.setVisibility(View.GONE);
            //跳转到查看每日工作
            holder.modify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DailyActivity.class);
                    intent.putExtra("taskNo", taskNo);
                    context.startActivity(intent);
                }
            });
            //跳转到我发起的-----延期待确认页面
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, YanqideterminedActivity.class);
                    intent.putExtra("taskId", id);
                    context.startActivity(intent);
                }
            });
        } else if (taskStatus == 12) {
            //我发起的  延期进行中
            holder.taskStatus.setImageResource(R.drawable.imageview12);
            holder.termination.setVisibility(View.VISIBLE);
            holder.button_examine.setVisibility(View.VISIBLE);
            holder.modify.setVisibility(View.VISIBLE);
            holder.modify.setText("修改");
            holder.termination.setText("终止");
            holder.button_examine.setText("查看每日工作");
            holder.button_examine.setBackgroundResource(R.drawable.button_backgroud_blue);
            holder.button_examine.setTextColor(Color.parseColor("#ff006bff"));
            holder.modify.setBackgroundResource(R.drawable.button_backgroud_red);
            holder.modify.setTextColor(Color.parseColor("#ffff4949"));
            if (canUpdate == 0) {
                holder.modify.setVisibility(View.GONE);
            } else if (canUpdate == 1) {
                holder.modify.setVisibility(View.VISIBLE);
            }
            //跳转到我发起的 --------修改页面
            holder.modify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //把接收部门 发起人 接收人 结束时间 任务描述都传过去
                    Intent intent = new Intent(context, ModifyActivity.class);
                    intent.putExtra("taskId", id);
                    context.startActivity(intent);
                }
            });
            //跳转到我发起的 ------终止页面
            holder.termination.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, TerminationActivity.class);
                    intent.putExtra("taskId", id);
                    intent.putExtra("taskNo", taskNo);
                    intent.putExtra("inspected", 0);
                    intent.putExtra("statusStr", statusStr);
                    context.startActivity(intent);
                }
            });
            //跳转到我发起的---------查看每日工作页面
            holder.button_examine.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DailyActivity.class);
                    intent.putExtra("taskNo", taskNo);
                    context.startActivity(intent);
                }
            });
            //跳转到我发起的--------延期进行中页面
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MyExtensioninprogressActivity.class);
                    intent.putExtra("taskId", id);
                    context.startActivity(intent);
                }
            });
        } else if (taskStatus == 21) {
            holder.taskStatus.setImageResource(R.drawable.imageview21);
            holder.modify.setText("终止");
            holder.termination.setText("查看每日工作");
            holder.termination.setBackgroundResource(R.drawable.button_backgroud_blue);
            holder.termination.setTextColor(Color.parseColor("#ff006bff"));
            holder.button_examine.setVisibility(View.GONE);
            holder.modify.setVisibility(View.VISIBLE);
            holder.termination.setVisibility(View.VISIBLE);

            //跳转到我发起的--------终止页面
            holder.modify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, TerminationActivity.class);
                    intent.putExtra("taskId", id);
                    intent.putExtra("taskNo", taskNo);
                    intent.putExtra("inspected", 0);
                    intent.putExtra("statusStr", statusStr);
                    context.startActivity(intent);
                }
            });
            //跳转到我发起的----查看每日工作页面
            holder.termination.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DailyActivity.class);
                    intent.putExtra("taskNo", taskNo);
                    context.startActivity(intent);
                }
            });
            //跳转到我发起的-----修改进行中
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ModificationinprogressActivity.class);
                    intent.putExtra("taskId", id);
                    //   intent.putExtra("isUrgent", isUrgent);
                    context.startActivity(intent);
                }
            });
        } else if (taskStatus == 6) {
            holder.taskStatus.setImageResource(R.drawable.imageview6);
            holder.modify.setText("查看每日工作");
            holder.modify.setBackgroundResource(R.drawable.button_backgroud_blue);
            holder.modify.setTextColor(Color.parseColor("#ff006bff"));
            holder.termination.setVisibility(View.GONE);
            holder.button_examine.setVisibility(View.GONE);
            holder.modify.setVisibility(View.VISIBLE);

            //跳转到我发起的--------查看每日工作页面
            holder.modify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DailyActivity.class);
                    intent.putExtra("taskNo", taskNo);
                    context.startActivity(intent);
                }
            });
            //跳转到我发起的-----已终止页面
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, TerminatedActivity.class);
                    intent.putExtra("taskId", id);

                    context.startActivity(intent);
                }
            });
        } else if (taskStatus == 14) {
            //我接收的---延期不通过
            holder.taskStatus.setImageResource(R.drawable.imageview1);
            holder.modify.setText("查看每日任务");
            holder.modify.setBackgroundResource(R.drawable.button_backgroud_blue);
            holder.modify.setTextColor(Color.parseColor("#ff006bff"));

            holder.termination.setVisibility(View.GONE);
            holder.button_examine.setVisibility(View.GONE);
            holder.modify.setVisibility(View.VISIBLE);


            //跳转到查看每日工作页面
            holder.modify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //跳转到查看每日工作
                    Intent intent = new Intent(context, DailyActivity.class);
                    intent.putExtra("taskNo", taskNo);
                    context.startActivity(intent);
                }
            });
            //跳转到我接收的------延期不通过页面
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, PostponementActivity.class);
                    intent.putExtra("taskId", id);
                    context.startActivity(intent);
                }
            });
        } else if (taskStatus == 18) {
            //我发起的------修改待确认
            holder.taskStatus.setImageResource(R.drawable.imageview18);
            holder.image_expedited.setVisibility(View.GONE);
            holder.modify.setText("终止");
            holder.termination.setText("查看每日工作");
            holder.termination.setBackgroundResource(R.drawable.button_backgroud_blue);
            holder.termination.setTextColor(Color.parseColor("#ff006bff"));
            holder.modify.setBackgroundResource(R.drawable.button_backgroud_red);
            holder.modify.setTextColor(Color.parseColor("#ffff4949"));
            holder.button_examine.setVisibility(View.GONE);
            holder.modify.setVisibility(View.VISIBLE);
            holder.termination.setVisibility(View.VISIBLE);
            //判断是否是加急任务单
            //跳转到我发起的-------终止页面
            holder.modify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, TerminationActivity.class);
                    intent.putExtra("taskId", id);
                    intent.putExtra("taskNo", taskNo);
                    intent.putExtra("inspected", 0);
                    intent.putExtra("statusStr", statusStr);
                    context.startActivity(intent);
                }
            });
            //跳转到我发起的--------查看每日工作
            holder.termination.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DailyActivity.class);
                    intent.putExtra("taskNo", taskNo);
                    context.startActivity(intent);
                }
            });
            //跳转到我发起的--------修改待确认页面
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ModificationpendingActivity.class);
                    intent.putExtra("taskId", id);
                    //intent.putExtra("isUrgent", isUrgent);
                    context.startActivity(intent);
                }
            });
        } else if (taskStatus == 20) {
            //跳转到 我发起的 ------验收修改中
            holder.taskStatus.setImageResource(R.drawable.imageview20);
            holder.modify.setText("终止");
            holder.termination.setText("查看每日工作");
            holder.termination.setBackgroundResource(R.drawable.button_backgroud_blue);
            holder.termination.setTextColor(Color.parseColor("#ff006bff"));
            holder.button_examine.setVisibility(View.GONE);
            holder.modify.setVisibility(View.VISIBLE);
            holder.termination.setVisibility(View.VISIBLE);

            //跳转到我发起的-------终止页面
            holder.modify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, TerminationActivity.class);
                    intent.putExtra("taskId", id);
                    intent.putExtra("taskNo", taskNo);
                    intent.putExtra("inspected", 0);
                    intent.putExtra("statusStr", statusStr);
                    context.startActivity(intent);
                }
            });
            //跳转到我发起的--------查看每日工作
            holder.termination.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DailyActivity.class);
                    intent.putExtra("taskNo", taskNo);
                    context.startActivity(intent);
                }
            });
            //跳转到我发起的--------验收修改中
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, AcceptingmodificationActivity.class);
                    intent.putExtra("taskId", id);
//                    intent.putExtra("isUrgent", isUrgent);
                    context.startActivity(intent);
                }
            });
        } else if (taskStatus == 19) {
            //跳转到我发起的 未接收界面
            holder.taskStatus.setImageResource(R.drawable.imageview19);
            holder.modify.setVisibility(View.GONE);
            holder.termination.setVisibility(View.GONE);
            holder.button_examine.setVisibility(View.GONE);
            //判断是否是加急任务单
            if (isUrgent == 0) {
                holder.image_expedited.setVisibility(View.GONE);
            } else if (isUrgent == 1) {
                holder.image_expedited.setVisibility(View.VISIBLE);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, NotreceivedActivity.class);
                    intent.putExtra("taskId", id);
                    intent.putExtra("isUrgent", isUrgent);
                    context.startActivity(intent);
                }
            });
        }
    }

    //防止滑动的时候数据错乱
    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView taskNo;//任务编号
        private TextView addNickName;//发起人
        private TextView wantFinishTiem;//结束时间
        private ImageView taskStatus;//状态文字
        private Button button_examine;//汇报每日工作
        private Button termination;//申请延期
        private Button modify;//确认收到
        private ImageView image_expedited;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            taskNo = itemView.findViewById(R.id.taskNo);
            addNickName = itemView.findViewById(R.id.addNickName);
            wantFinishTiem = itemView.findViewById(R.id.wantFinishTiem);
            taskStatus = itemView.findViewById(R.id.taskStatus);
            button_examine = itemView.findViewById(R.id.button_examine);
            termination = itemView.findViewById(R.id.termination);
            modify = itemView.findViewById(R.id.modify);
            image_expedited = itemView.findViewById(R.id.image_expedited);
        }
    }
}