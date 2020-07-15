package com.example.yuhekejioa.Adapter;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yuhekejioa.Bean.filebean;
import com.example.yuhekejioa.My_Initiated.InitiateActivity;
import com.example.yuhekejioa.R;

import java.util.List;


public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder> {
    Context context;
    List<filebean> list_file;
    //私有属性
    private OnItemClickListener onItemClickListener = null;

    public FileAdapter(Context context, List<filebean> list_file) {
        this.context = context;
        this.list_file = list_file;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.file_layout, null);
        ViewHolder holder = new ViewHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.filename.setText(list_file.get(position).getFilename());
        holder.filearm.setText(list_file.get(position).getFileram());
        holder.image_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list_file.remove(position);
                FileAdapter.this.notifyDataSetChanged();
            }
        });
        //实现点击效果
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list_file.size();
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
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    //回调接口
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}
