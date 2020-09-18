package com.example.yuhekejioa.WheelDialog;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.yuhekejioa.R;
import com.example.yuhekejioa.Utils.JsonBean;
import com.google.gson.Gson;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/5/7.
 */

public class CityDialogFragment extends BaseDialogFragment implements WheelView.OnValueChangeListener {
    public static final String DIALOG_LEFT = "dialog_left";
    public static final String DIALOG_RIGHT = "dialog_right";
    public static final String DIALOG_WHEEL = "dialog_wheel";
    public static final String DIALOG_TITLE = "dialog_title";
    public static final String DIALOG_TEXT = "dialog_text";

    private WheelView wheelView, wheelView_two, wheelView_three;
    private TextView tvLeft, tvRight, tv_title;

    private String[] dialogWheel, dialogWheel_two, dialogWheel_three;
    private String dialogLeft, dialogRight, dialogTitle;
    private OnWheelDialogListener onWheelDialogListener;
    private String dialogText;
    private ArrayList<JsonBean> jsonBeans;
    private List<JsonBean.CityBean> cityList;
    private List<JsonBean.City> area;
    private String provice, city, county;
    private String proviceCode, cityCode, countyCode;

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
        dialogText = bundle.getString(DIALOG_TEXT);
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
        tv_title = view.findViewById(R.id.tv_title);
        jsonBeans = parseData(dialogText);
        dialogWheel = new String[jsonBeans.size()];
        for (int i = 0; i < jsonBeans.size(); i++) {
            String name = jsonBeans.get(i).getName();
            dialogWheel[i] = name;
        }
        provice = jsonBeans.get(0).getName();
        proviceCode = jsonBeans.get(0).getCode();
        List<JsonBean.CityBean> cityList = jsonBeans.get(0).getCityList();
        dialogWheel_two = new String[cityList.size()];
        for (int i = 0; i < cityList.size(); i++) {
            String name = cityList.get(i).getName();
            dialogWheel_two[i] = name;
        }
        city = jsonBeans.get(0).getCityList().get(0).getName();
        cityCode = jsonBeans.get(0).getCityList().get(0).getCode();
        List<JsonBean.City> area = jsonBeans.get(0).getCityList().get(0).getArea();
        dialogWheel_three = new String[area.size()];
        for (int i = 0; i < area.size(); i++) {
            String name = area.get(i).getName();
            dialogWheel_three[i] = name;
        }
        county = jsonBeans.get(0).getCityList().get(0).getArea().get(0).getName();
        countyCode = jsonBeans.get(0).getCityList().get(0).getArea().get(0).getCode();
    }

    @Override
    protected void setSubView() {
        tvLeft.setText(dialogLeft);
        tvRight.setText(dialogRight);
        tv_title.setText(dialogTitle);
        wheelView.refreshByNewDisplayedValues(dialogWheel);
        wheelView_two.refreshByNewDisplayedValues(dialogWheel_two);
        wheelView_three.refreshByNewDisplayedValues(dialogWheel_three);
        //设置是否可以上下无限滑动
        wheelView.setWrapSelectorWheel(false);
        wheelView.setDividerColor(ResUtil.getColor(R.color.white));
        wheelView.setSelectedTextColor(ResUtil.getColor(R.color.theme));
        wheelView.setNormalTextColor(ResUtil.getColor(R.color.theme_black));
        wheelView_two.setWrapSelectorWheel(false);
        wheelView_two.setDividerColor(ResUtil.getColor(R.color.white));
        wheelView_two.setSelectedTextColor(ResUtil.getColor(R.color.theme));
        wheelView_two.setNormalTextColor(ResUtil.getColor(R.color.theme_black));
        wheelView_three.setWrapSelectorWheel(false);
        wheelView_three.setDividerColor(ResUtil.getColor(R.color.white));
        wheelView_three.setSelectedTextColor(ResUtil.getColor(R.color.theme));
        wheelView_three.setNormalTextColor(ResUtil.getColor(R.color.theme_black));
        wheelView.setOnValueChangedListener(new WheelView.OnValueChangeListener() {
            @Override
            public void onValueChange(WheelView picker, int oldVal, int newVal) {
                cityList = jsonBeans.get(newVal).getCityList();
                dialogWheel_two = new String[cityList.size()];
                for (int i = 0; i < cityList.size(); i++) {
                    String name = cityList.get(i).getName();
                    dialogWheel_two[i] = name;
                }
                wheelView_two.refreshByNewDisplayedValues(dialogWheel_two);
                area = cityList.get(0).getArea();
                dialogWheel_three = new String[area.size()];
                for (int i = 0; i < area.size(); i++) {
                    String name = area.get(i).getName();
                    dialogWheel_three[i] = name;
                }
                wheelView_three.refreshByNewDisplayedValues(dialogWheel_three);
                provice = jsonBeans.get(newVal).getName();
                proviceCode = jsonBeans.get(newVal).getCode();
                city = jsonBeans.get(newVal).getCityList().get(0).getName();
                cityCode = jsonBeans.get(newVal).getCityList().get(0).getCode();
                county = jsonBeans.get(newVal).getCityList().get(0).getArea().get(0).getName();
                countyCode = jsonBeans.get(newVal).getCityList().get(0).getArea().get(0).getCode();
            }
        });
        wheelView_two.setOnValueChangedListener(new WheelView.OnValueChangeListener() {
            @Override
            public void onValueChange(WheelView picker, int oldVal, int newVal) {
                area = cityList.get(newVal).getArea();
                dialogWheel_three = new String[area.size()];
                for (int i = 0; i < area.size(); i++) {
                    String name = area.get(i).getName();
                    dialogWheel_three[i] = name;
                }
                wheelView_three.refreshByNewDisplayedValues(dialogWheel_three);
                city = cityList.get(newVal).getName();
                cityCode = cityList.get(newVal).getCode();
                county = cityList.get(newVal).getArea().get(0).getName();
                countyCode = cityList.get(newVal).getArea().get(0).getCode();
            }
        });
        wheelView_three.setOnValueChangedListener(new WheelView.OnValueChangeListener() {
            @Override
            public void onValueChange(WheelView picker, int oldVal, int newVal) {
                JsonBean.City city = area.get(newVal);
                county = city.getName();
                countyCode = city.getCode();
            }
        });
    }

    public ArrayList<JsonBean> parseData(String result) {
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

    @Override
    protected void initEvent() {
        //左边按钮
        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onWheelDialogListener != null) {
                    onWheelDialogListener.onClickLeft(CityDialogFragment.this, getWheelValue());
                }
            }
        });

        //右边按钮
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onWheelDialogListener != null) {
                    onWheelDialogListener.onClickRight(CityDialogFragment.this, provice + city + county, proviceCode, cityCode, countyCode);
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
            onWheelDialogListener.onValueChanged(CityDialogFragment.this, content[newVal - wheelView.getMinValue()]);
        }
    }

    public interface OnWheelDialogListener {
        /**
         * 左边按钮单击事件回调
         *
         * @param dialog
         * @param value
         */
        void onClickLeft(CityDialogFragment dialog, String value);

        /**
         * 滑动停止时的回调
         *
         * @param dialog
         * @param value
         */
        void onValueChanged(CityDialogFragment dialog, String value);
        /**
         * 右边按钮单击事件回调
         *
         */
        void onClickRight(CityDialogFragment wheelDialogFragment, String s, String proviceCode, String cityCode, String countyCode);
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