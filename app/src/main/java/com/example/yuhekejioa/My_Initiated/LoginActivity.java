package com.example.yuhekejioa.My_Initiated;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.yuhekejioa.MainActivity;
import com.example.yuhekejioa.R;
import com.example.yuhekejioa.Utils.Constant;
import com.example.yuhekejioa.Utils.IsNetwork;
import com.example.yuhekejioa.Utils.LoadingDialog;
import com.example.yuhekejioa.Utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import cn.jpush.android.api.JPushInterface;

//登录界面
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText login_phone;
    private EditText login_password;
    private ImageView image_yincang;
    private ImageView image_xianshi;
    private Button login;
    private SharedPreferences.Editor edit;
    private SharedPreferences sharedPreferences;
    private Dialog loadingDialog;


    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedPreferences = getSharedPreferences("tokens", MODE_PRIVATE);
        edit = sharedPreferences.edit();

        //判断是否第一次登录 如果不是直接进入首页
        String username = sharedPreferences.getString("username", "");
        Log.e("TAG", "onCreate: " + username);
        //用户名不为空 不为null 就跳转到首页
        if (username != null && !username.equals("")) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        initView();
    }

    private void initView() {
        login_phone = findViewById(R.id.login_phone);
        login_password = findViewById(R.id.login_password);
        image_yincang = findViewById(R.id.image_yincang);
        image_xianshi = findViewById(R.id.image_xianshi);

        login_phone.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        login_password.setLayerType(View.LAYER_TYPE_HARDWARE, null);

        login = findViewById(R.id.login);
        login.setOnClickListener(this);
        image_xianshi.setOnClickListener(this);
        image_yincang.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login:
                initlogin();
                break;
            case R.id.image_xianshi:
                //点击按钮edittext显示密码
                login_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                //点击显示密码后光标自动在密码的最后一位
                login_password.setSelection(login_password.getText().length());

                break;
            case R.id.image_yincang:
                //点击按钮edittext密码隐藏
                login_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                login_password.setSelection(login_password.getText().length());

                break;
        }
    }

    private void initlogin() {
        //判断当前网络是否可用
        if (!new IsNetwork().isNetworkAvailable(LoginActivity.this)) {
            Toast toast = Toast.makeText(LoginActivity.this, "当前没有可用网络", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        // 判断手机号是否为空
        final String edit_phone = login_phone.getText().toString().trim();
        if (TextUtils.isEmpty(edit_phone)) {
            Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        if (edit_phone.length() > 20 || edit_phone.length() < 4) {
            Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (isPassword(edit_phone) == false) {
                Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        //判断密码是否为空
        final String edit_password = login_password.getText().toString().trim();
        if (TextUtils.isEmpty(edit_password)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edit_password.length() > 20 || edit_password.length() < 6) {
            Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (isPassword(edit_password) == false) {
                Toast.makeText(this, "账号或密码错误", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.createLoadingDialog(LoginActivity.this, "正在加载中...");
            loadingDialog.show();
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("username", edit_phone);
        map.put("password", edit_password);
        NetworkUtils.sendPost(Constant.ip + "/login", map, LoginActivity.this, new NetworkUtils.HttpCallback() {
            @Override
            public void onSuccess(JSONObject res) {
                //如果res为空的话就不进行下面的操作
                if (res == null || this == null) {
                    return;
                }
                try {
                    int code = res.getInt("code");
                    if (code == 200) {

                        JSONObject data = res.getJSONObject("data");
                        String userNo = data.getString("userNo");
                        String token = data.getString("token");
                        String name = data.getString("name");
                        Log.e("TAG", "onSuccess: " + userNo);
                        JPushInterface.setAlias(LoginActivity.this, 0, userNo);
                        edit.putString("token", token);
                        edit.putString("userNo", userNo);
                        edit.putString("username", edit_phone);
                        edit.putString("password", edit_password);
                        edit.putString("name", name);
                        edit.commit();
                        //登录成功
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (loadingDialog != null) {
                                    loadingDialog.dismiss();
                                    loadingDialog = null;
                                }
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                    } else if (code == 500) {
                        final String msg = (String) res.get("msg");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (loadingDialog != null) {
                                    loadingDialog.dismiss();
                                    loadingDialog = null;
                                }
                                Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (loadingDialog != null) {
                                loadingDialog.dismiss();
                                loadingDialog = null;
                            }
                        }
                    });
                }
            }

            @Override
            public void onError(final String msg) {
                super.onError(msg);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (loadingDialog != null) {
                            loadingDialog.dismiss();
                            loadingDialog = null;
                        }

                        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    private boolean isPassword(String str)
            throws PatternSyntaxException {
        String regExp = "^[a-zA-Z0-9]+$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    //防止快速点击出现多个相同页面的问题
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (isFastDoubleClick()) {
                return true;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private long lastClickTime = System.currentTimeMillis();

    private boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (timeD >= 0 && timeD <= 1000) {
            return true;
        } else {
            lastClickTime = time;
            return false;
        }
    }
}
