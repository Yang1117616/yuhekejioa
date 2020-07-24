package com.example.yuhekejioa.side;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuhekejioa.R;
import com.example.yuhekejioa.Utils.Constant;
import com.example.yuhekejioa.Utils.NetworkUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//修改手机号页面
public class Relative_phoneActivity extends AppCompatActivity implements View.OnClickListener {

    private Button submit;
    private RelativeLayout relative_back;//返回按钮
    private EditText new_phone;

    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_phone);
        initView();
    }

    private void initView() {
        relative_back = findViewById(R.id.relative_back);
        submit = findViewById(R.id.submit);
        new_phone = findViewById(R.id.new_phone);

        relative_back.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.relative_back:
                finish();
                break;
            case R.id.submit:
                initsubmit();
                break;
        }
    }

    //修改手机号
    private void initsubmit() {
        String phone = new_phone.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isMatcherFinded("^[1][3578][0-9]{9}$", phone)) {
            Toast.makeText(this, "请输入正确格式的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("newPhone", phone);
        NetworkUtils.sendPost(Constant.ip + "/app/user/updatePhone", hashMap, Relative_phoneActivity.this, new NetworkUtils.HttpCallback() {
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
                                Toast.makeText(Relative_phoneActivity.this, msg, Toast.LENGTH_SHORT).show();
                                Relative_phoneActivity.this.finish();
                            }
                        });
                    } else if (code==500){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Relative_phoneActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String msg) {
                super.onError(msg);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Relative_phoneActivity.this, msg, Toast.LENGTH_SHORT).show();
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
