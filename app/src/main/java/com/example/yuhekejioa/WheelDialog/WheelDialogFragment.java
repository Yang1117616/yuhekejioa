package com.example.yuhekejioa.WheelDialog;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.yuhekejioa.R;


/**
 * Created by Administrator on 2020/5/7.
 */

public class WheelDialogFragment extends BaseDialogFragment implements WheelView.OnValueChangeListener {
    public static final String DIALOG_LEFT = "dialog_left";
    public static final String DIALOG_RIGHT = "dialog_right";
    public static final String DIALOG_WHEEL = "dialog_wheel";
    public static final String DIALOG_TITLE = "dialog_title";

    private WheelView wheelView, wheelView_two, wheelView_three;
    private TextView tvLeft, tvRight, tv_title;

    private String[] dialogWheel;
    private String dialogLeft, dialogRight, dialogTitle;
    private OnWheelDialogListener onWheelDialogListener;

    @Override
    public void onStart() {
        super.onStart();
        //设置对话框显示在底部
        getDialog().getWindow().setGravity(Gravity.BOTTOM);
        //设置对话框弹出动画，从底部滑入，从底部滑出
        getDialog().getWindow().getAttributes().windowAnimations = R.style.Dialog_Animation;
        //设置让对话框宽度充满屏幕
        getDialog().getWindow().setLayout(ScreenUtil.getScreenWidth(activity), getDialog().getWindow().getAttributes().height);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialogWheel = bundle.getStringArray(DIALOG_WHEEL);
        dialogLeft = bundle.getString(DIALOG_LEFT);
        dialogRight = bundle.getString(DIALOG_RIGHT);
        dialogTitle = bundle.getString(DIALOG_TITLE);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.view_dialog_wheel;
    }

    @Override
    protected void initView(View view) {
        tvLeft = (TextView) view.findViewById(R.id.tv_wheel_dialog_left);
        tvRight = (TextView) view.findViewById(R.id.tv_wheel_dialog_right);
        wheelView = (WheelView) view.findViewById(R.id.WheelView_dialog);
        wheelView_two = (WheelView) view.findViewById(R.id.WheelView_dialog_two);
        wheelView_three = (WheelView) view.findViewById(R.id.WheelView_dialog_three);
        wheelView_two.setVisibility(View.GONE);
        wheelView_three.setVisibility(View.GONE);
        tv_title = view.findViewById(R.id.tv_title);
    }

    @Override
    protected void setSubView() {
        tvLeft.setText(dialogLeft);
        tvRight.setText(dialogRight);
        tv_title.setText(dialogTitle);

        wheelView.refreshByNewDisplayedValues(dialogWheel);
        //设置是否可以上下无限滑动
        wheelView.setWrapSelectorWheel(false);
        wheelView.setDividerColor(ResUtil.getColor(R.color.white));
        wheelView.setSelectedTextColor(ResUtil.getColor(R.color.theme_black));
        wheelView.setNormalTextColor(ResUtil.getColor(R.color.theme_gray));
    }

    @Override
    protected void initEvent() {
        //左边按钮
        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onWheelDialogListener != null) {
                    onWheelDialogListener.onClickLeft(WheelDialogFragment.this, getWheelValue());
                }
            }
        });

        //右边按钮
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onWheelDialogListener != null) {
                    onWheelDialogListener.onClickRight(WheelDialogFragment.this, getWheelValues());
                }
            }
        });
    }

    @Override
    protected void onCancel() {

    }

    /**
     * 获取当前值
     *
     * @return
     */
    private String getWheelValue() {
        String[] content = wheelView.getDisplayedValues();
        return content == null ? null : content[wheelView.getValue() - wheelView.getMinValue()];
    }

    private int getWheelValues() {
        String[] content = wheelView.getDisplayedValues();
        return content == null ? null : wheelView.getValue() - wheelView.getMinValue();
    }

    @Override
    public void onValueChange(WheelView picker, int oldVal, int newVal) {
        String[] content = wheelView.getDisplayedValues();
        if (content != null && onWheelDialogListener != null) {
            onWheelDialogListener.onValueChanged(WheelDialogFragment.this, content[newVal - wheelView.getMinValue()]);
        }
    }

    public interface OnWheelDialogListener {
        /**
         * 左边按钮单击事件回调
         *
         * @param dialog
         * @param value
         */
        void onClickLeft(WheelDialogFragment dialog, String value);

        /**
         * 右边按钮单击事件回调
         *
         * @param dialog
         * @param value
         */
        void onClickRight(WheelDialogFragment dialog, int value);

        /**
         * 滑动停止时的回调
         *
         * @param dialog
         * @param value
         */
        void onValueChanged(WheelDialogFragment dialog, String value);
    }

    /**
     * 对外开放的方法
     *
     * @param onWheelDialogListener
     */
    public void setWheelDialogListener(OnWheelDialogListener onWheelDialogListener) {
        this.onWheelDialogListener = onWheelDialogListener;
    }
}