package com.example.yuhekejioa.side;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.yuhekejioa.R;
import com.example.yuhekejioa.Utils.Constant;
import com.example.yuhekejioa.Utils.NetworkUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//修改邮箱页面
public class Relative_mailboxActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout relative_back;//返回按钮
    private EditText new_mailbo;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_mailbox);
        initView();
    }

    private void initView() {
        relative_back = findViewById(R.id.relative_back);
        new_mailbo = findViewById(R.id.new_mailbo);
        submit = findViewById(R.id.submit);

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

    private void initsubmit() {
        String s = new_mailbo.getText().toString();
        if (TextUtils.isEmpty(s)) {
            Toast.makeText(this, "请输入新的邮箱", Toast.LENGTH_SHORT).show();
            return;
        }
        //判断邮箱格式
//        if (!isMatcherFinded("^([a-z0-9A-Z]+[-|_|\\\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\\\.)+[a-zA-Z]{2,}$",s)) {
//            Toast.makeText(this, "请输入正确格式的邮箱", Toast.LENGTH_SHORT).show();
//            return;
//        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("newEmail", s);
        NetworkUtils.sendPost(Constant.ip + "/app/user/updateEmail", hashMap, Relative_mailboxActivity.this, new NetworkUtils.HttpCallback() {
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
                                Toast.makeText(Relative_mailboxActivity.this, msg, Toast.LENGTH_SHORT).show();
                                Relative_mailboxActivity.this.finish();
                            }
                        });
                    } else if (code==500){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(Relative_mailboxActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(Relative_mailboxActivity.this, msg, Toast.LENGTH_SHORT).show();
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
