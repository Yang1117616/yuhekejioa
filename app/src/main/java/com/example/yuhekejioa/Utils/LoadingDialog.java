package com.example.yuhekejioa.Utils;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.yuhekejioa.R;



public class LoadingDialog {

    /**
     * 得到自定义的progressDialog
     *
     * @param context
     * @param msg
     * @return
     */
    public static Dialog createLoadingDialog(Context context, String msg) {
        View view = null;
        if (context != null) {
            if (LayoutInflater.from(context) != null) {
                view = LayoutInflater.from(context).inflate(R.layout.loading_dialog_view, null);
            }
            LinearLayout layout = (LinearLayout) view.findViewById(R.id.dialog_view);
            ImageView img = (ImageView) view.findViewById(R.id.loading_img);
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.rotate);
            img.startAnimation(animation);
            // 创建自定义样式的Dialog
            Dialog loadingDialog = new Dialog(context, R.style.loading_dialog);
            // 设置返回键无效
//        loadingDialog.setCancelable(false);
            loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT));
            return loadingDialog;
        }
        return null;
    }
}