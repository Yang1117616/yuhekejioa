package com.example.yuhekejioa.side;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.yuhekejioa.My_Initiated.AcceptancefailedActivity;
import com.example.yuhekejioa.R;
import com.example.yuhekejioa.Utils.Constant;
import com.example.yuhekejioa.Utils.LoadingDialog;
import com.example.yuhekejioa.Utils.NetworkUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//修改密码页面
public class Relative_pasActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText old_password;
    private EditText new_password;
    private EditText new_passwordx;
    private RelativeLayout relative_back;
    private Button submit;
    private SharedPreferences tokens;
    private String password;
    private SharedPreferences.Editor edit;
    private Dialog loadingDialog;


    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_pas);
        tokens = getSharedPreferences("tokens", MODE_PRIVATE);
        password = tokens.getString("password", "");
        edit = tokens.edit();
        initView();
    }

    private void initView() {

        old_password = findViewById(R.id.old_password);
        new_password = findViewById(R.id.new_password);
        new_passwordx = findViewById(R.id.new_passwordx);

        relative_back = findViewById(R.id.relative_back);
        submit = findViewById(R.id.submit);

        relative_back.setOnClickListener(this);
        submit.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relative_back:
                Relative_pasActivity.this.finish();
                break;
            case R.id.submit:
                initsubmit();
                break;
        }
    }

    //密码提交
    private void initsubmit() {
        String old_mima = old_password.getText().toString();
        if (TextUtils.isEmpty(old_mima)) {
            Toast.makeText(this, "原密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        //获取保存到本地的登录的时候密码做判断看在输入框里面输入的密码个登录时候的密码是否一致
        if (!old_mima.equals(password)) {
            Toast toast = Toast.makeText(this, "请输入正确密码", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }

        // 新密码
        String new_mima = new_password.getText().toString();
        if (TextUtils.isEmpty(new_mima)) {
            Toast.makeText(this, "新密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        edit.putString("password", new_mima);//把新的密码保存起来
        edit.commit();

        String new_mimax = new_passwordx.getText().toString();

        if (TextUtils.isEmpty(new_mimax)) {
            Toast.makeText(this, "再次输入的密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }


        //判断新密码和第二次新密码输入是否一直
        if (!new_mima.equals(new_mimax)) {
            Toast toast = Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }

        //判断新密码格式
        if (!isMatcherFinded("^(?![^a-zA-Z0-9]+$)(?!\\\\D+$).{6,26}$", new_mima)) {
            // Toast.makeText(this, "请输入包括数字和字母、长度6到18位的密码组合", Toast.LENGTH_SHORT).show();
            Toast toast = Toast.makeText(this, "请输入包括数字和字母、长度6到18位的密码组合", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }

        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.createLoadingDialog(Relative_pasActivity.this, "正在加载中...");
            loadingDialog.show();
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("newPassword", new_mima);
        hashMap.put("oldPassword", old_mima);

        NetworkUtils.sendPost(Constant.ip + "/app/user/updatePassword", hashMap, this, new NetworkUtils.HttpCallback() {
            @Override
            public void onSuccess(JSONObject res) {
                if (res == null || this == null) {
                    return;
                }
                try {
                    int code = res.getInt("code");
                    String msg = res.getString("msg");
                    if (code == 200) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (loadingDialog != null) {
                                    loadingDialog.dismiss();
                                    loadingDialog = null;
                                }
                                Toast.makeText(Relative_pasActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                                Relative_pasActivity.this.finish();
                            }
                        });
                    } else if (code == 500) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (loadingDialog != null) {
                                    loadingDialog.dismiss();
                                    loadingDialog = null;
                                }
                                Toast.makeText(Relative_pasActivity.this, msg, Toast.LENGTH_SHORT).show();
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
            public void onError(String msg) {
                super.onError(msg);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (loadingDialog != null) {
                            loadingDialog.dismiss();
                            loadingDialog = null;
                        }
                        Toast.makeText(Relative_pasActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private boolean isMatcherFinded(String patternStr, CharSequence input) {
        Pattern pattern = Pattern.compile(patternStr);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return true;
        }
        return false;
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
