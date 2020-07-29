package com.example.yuhekejioa;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.Manifest;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.yuhekejioa.Adapter.MainAdapter;
import com.example.yuhekejioa.Bean.Mainbean;
import com.example.yuhekejioa.Jpush.ExampleUtil;
import com.example.yuhekejioa.Jpush.LocalBroadcastManager;
import com.example.yuhekejioa.My_Initiated.AcceptancefailedActivity;
import com.example.yuhekejioa.My_Initiated.InitiateActivity;
import com.example.yuhekejioa.My_Initiated.LoginActivity;
import com.example.yuhekejioa.My_Initiated.MeInitiateActivity;
import com.example.yuhekejioa.My_audit.MyAuditActivity;
import com.example.yuhekejioa.My_recrive.MyreceiveActivity;
import com.example.yuhekejioa.Utils.Constant;
import com.example.yuhekejioa.Utils.GlideEngine;
import com.example.yuhekejioa.Utils.IsNetwork;
import com.example.yuhekejioa.Utils.LoadingDialog;
import com.example.yuhekejioa.Utils.MyLog;
import com.example.yuhekejioa.Utils.NetworkUtils;
import com.example.yuhekejioa.Utils.SpringProgressView;
import com.example.yuhekejioa.side.Relative_mailboxActivity;
import com.example.yuhekejioa.side.Relative_pasActivity;
import com.example.yuhekejioa.side.Relative_phoneActivity;
import com.huantansheng.easyphotos.EasyPhotos;
import com.huantansheng.easyphotos.models.album.entity.Photo;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.jpush.android.api.JPushInterface;
import de.hdodenhof.circleimageview.CircleImageView;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CircleImageView circle;//圆形头像

    private RelativeLayout Initiated_layout;//发起的  布局
    private RelativeLayout receive_layout;//接收的 布局
    private RelativeLayout Audit_layout;//审核的 布局
    private DrawerLayout drawerLayout;
    private SmartRefreshLayout home_RefreshLayout;
    private RecyclerView accomplish_List;
    // 抽屉菜单对象
    private ActionBarDrawerToggle drawerbar;
    private LinearLayout main_left_drawer_layout;

    private CircleImageView circleImage;//侧边布局圆形头像
    private TextView name;//侧边布局用户名
    private TextView phone;//侧边布局手机号
    private RelativeLayout relative_phone;//侧边手机号布局

    private TextView mailbox;//侧边布局邮箱
    private RelativeLayout relative_mailbox;

    private Button dropout;//侧边退出账号按钮

    private RelativeLayout relative_pas;//侧边修改密码布局

    private RelativeLayout store_details_ll_close;//主页面发送按钮布局
    private Button signout;//侧面布局 退出按钮
    private boolean isoncl = true;
    private SharedPreferences.Editor edit;
    private static Boolean isExit = false;
    private static Boolean hasTask = false;
    public static boolean isForeground = false;
    public static final String MESSAGE_RECEIVED_ACTION = "com.example.yuhekejioa.MESSAGE_RECEIVED_ACTION";
    public static final String KEY_TITLE = "title";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_EXTRAS = "extras";
    Timer tExit = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            isExit = false;
            hasTask = true;
        }
    };
    private MessageReceiver mMessageReceiver;

    private List<Mainbean.DataBean> list = new ArrayList<>();

    private int pageNum = 1;
    private MainAdapter adapter;
    private String avatar;
    private TextView versionnumber;
    private RelativeLayout relative_searchfor;
    private String remark;
    private String nickName;
    //测试
    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;
    private Dialog loadingDialog;

    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
//        //安卓按下Home键至手机桌面后，重新点开应用时无法进入退出时的页面
//        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
//            finish();
//            return;
//        }
        if (!isTaskRoot()) {
            finish();
            return;
        }
        setContentView(R.layout.activity_main);
        registerMessageReceiver();  // used for receive msg
        SharedPreferences tokens = getSharedPreferences("tokens", MODE_PRIVATE);
        String userNo = tokens.getString("userNo", "");
        MyLog.e("保存userNo=", "==" + userNo);
        edit = tokens.edit();
        initView();
        initEvent();
        methodRequiresTwoPermission();

    }

    //强制更新版本网络请求、
    private void download() {
        String versionName = null;
        try {
            //获取当前项目版本号
            versionName = MainActivity.this.getPackageManager().getPackageInfo(MainActivity.this.getPackageName(), 0).versionName;
            versionnumber.setText("版本" + versionName);//添加版本
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("osType", String.valueOf(1));
        map.put("version", versionName);//版本号
        NetworkUtils.sendPost(Constant.ip + "/app/versionCheck", map, this, new NetworkUtils.HttpCallback() {
            @Override
            public void onSuccess(JSONObject res) {
                try {
                    int code = res.getInt("code");
                    if (code == 200) {
                        JSONObject data = res.getJSONObject("data");
                        String download = data.getString("download");//下载路径
                        int isNewest = data.getInt("isNewest");//版本是否强制更新
                        int isForce = data.getInt("isForced");//是否强制更新
                        //更新说明
                        remark = data.getString("remark");

                        String absolutePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download";
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (isNewest == 0) {
                                    if (isForce == 1) {//强制
                                        //  dialog_update(MainActivity.this, download, absolutePath, remark).show();
                                        dialog_update(MainActivity.this, download, absolutePath, remark).show();
                                    } else {
                                        dialog_update_two(MainActivity.this, download, absolutePath, remark).show();
                                    }
                                }
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //不强制更新弹框
    private Dialog dialog_update_two(MainActivity mainActivity, String download, String absolutePath, String remark) {
        final Dialog dialog = new Dialog(mainActivity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        Drawable d = new ColorDrawable(ContextCompat.getColor(mainActivity, R.color.line_font));
        d.setAlpha(200);
        dialog.setContentView(R.layout.dialog_update_two);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(d);
        TextView dialog_version = dialog.findViewById(R.id.dialog_version);
        dialog_version.setText(remark);
        dialog.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                dialog.cancel();
                NetworkUtils.download(download, absolutePath, Constant.name, new NetworkUtils.downloadCallback() {
                    @Override
                    public void onSuccess(String res) {
                        getApkFile(Constant.name);
                    }
                });
            }
        });
        dialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                dialog.cancel();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        return dialog;
    }

    /*
    强制更新
    MainActivity mainActivity：上下文
    String download：下载apk地址
    String absolutePath：apk下载链接
    String remark：更新版本提示语
     */
    public Dialog dialog_update(MainActivity mainActivity, String download, String absolutePath, String remark) {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        Drawable d = new ColorDrawable(ContextCompat.getColor(MainActivity.this, R.color.line_font));
        d.setAlpha(200);
        dialog.setContentView(R.layout.dialog_update);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(d);
        TextView dialog_version = dialog.findViewById(R.id.dialog_version);
        TextView progress_tv = dialog.findViewById(R.id.progress_tv);
        SpringProgressView progresss = dialog.findViewById(R.id.progress);
        progresss.setCurrentCount(0);
        progresss.setMaxCount(100);
        TextView wait = dialog.findViewById(R.id.wait);

        dialog_version.setText(remark);
        Button confirm = dialog.findViewById(R.id.confirm);
        dialog.setCanceledOnTouchOutside(false);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progresss.setVisibility(View.VISIBLE);
                wait.setVisibility(View.VISIBLE);
                confirm.setVisibility(View.GONE);
                progresss.setVisibility(View.VISIBLE);
                progress_tv.setVisibility(View.VISIBLE);
                NetworkUtils.download(download, absolutePath, Constant.name, new NetworkUtils.downloadCallback() {
                    @Override
                    public void onSuccess(String res) {
                        getApkFile(Constant.name);
                    }

                    @Override
                    public void onSuccess(int progress) {
                        super.onSuccess(progress);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progresss.setCurrentCount(progress);
                                progress_tv.setText(progress + "%");
                            }
                        });
                    }
                });
            }
        });
        return dialog;
    }

    //获取apk文件方法
    private void getApkFile(String name) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            uri = FileProvider.getUriForFile(MainActivity.this, MainActivity.this.getPackageName() + ".provider", new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/" + name));
            intent.setDataAndType(uri, "application/vnd.android.package-archive");
        } else {
            uri = Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/" + name));
            intent.setDataAndType(uri, "application/vnd.android.package-archive");

        }
        startActivity(intent);
    }


    private void initView() {
        circle = findViewById(R.id.circle);
        Initiated_layout = findViewById(R.id.Initiated_layout);
        receive_layout = findViewById(R.id.receive_layout);
        Audit_layout = findViewById(R.id.Audit_layout);
        home_RefreshLayout = findViewById(R.id.home_RefreshLayout);
        accomplish_List = findViewById(R.id.accomplish_List);
        circleImage = findViewById(R.id.circleImage);
        relative_phone = findViewById(R.id.relative_phone);
        mailbox = findViewById(R.id.mailbox);
        relative_mailbox = findViewById(R.id.Relative_mailbox);
        dropout = findViewById(R.id.dropout);
        relative_pas = findViewById(R.id.relative_pas);
        store_details_ll_close = findViewById(R.id.store_details_ll_close);
        drawerLayout = findViewById(R.id.main_drawer_layout);
        main_left_drawer_layout = findViewById(R.id.main_left_drawer_layout);
        signout = findViewById(R.id.button);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);

        versionnumber = findViewById(R.id.versionnumber);//版本号textview

        relative_searchfor = findViewById(R.id.relative_searchfor);

        //设置菜单内容之外其他区域的背景色 灰色
        drawerLayout.setScrimColor(Color.GRAY);
        circle.setOnClickListener(this);
        Initiated_layout.setOnClickListener(this);
        receive_layout.setOnClickListener(this);
        Audit_layout.setOnClickListener(this);
        store_details_ll_close.setOnClickListener(this);
        relative_phone.setOnClickListener(this);
        relative_mailbox.setOnClickListener(this);
        relative_pas.setOnClickListener(this);
        signout.setOnClickListener(this);
        //  circleImage.setOnClickListener(this);

        home_RefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                MyLog.e("刷新");
                pageNum = 1;
                initData();
            }
        });
        home_RefreshLayout.setRefreshHeader(new ClassicsHeader(MainActivity.this));
        //跳转到搜索标题页面
        relative_searchfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SearchforActivity.class));
            }
        });

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        accomplish_List.setLayoutManager(linearLayoutManager);
        //加载分割线
        adapter = new MainAdapter(MainActivity.this, list);
        accomplish_List.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //判断有无网络
        if (!new IsNetwork().isNetworkAvailable(MainActivity.this)) {
            Toast toast = Toast.makeText(MainActivity.this, "当前没有可用网络", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            return;
        }
        download();
        initData();
        //个人信息界面玩咯请求
        NetworkUtils.sendPost(Constant.ip + "/app/user/getUserInfo", null, MainActivity.this, new NetworkUtils.HttpCallback() {
            @Override
            public void onSuccess(JSONObject res) {
                //如果res为空的话就不进行下面的操作
                if (res == null || this == null) {
                    return;
                }
                try {
                    int code = res.getInt("code");
                    String msg = res.getString("msg");
                    if (code == 200) {
                        JSONObject data = res.getJSONObject("data");
                        //判断字符串是否为空
                        // boolean updateBy = data.isNull("updateBy");
                        String phonenumber = data.getString("phonenumber");//手机号
                        String email = data.getString("email");//邮箱号
                        //员工姓名
                        nickName = data.getString("nickName");
                        //图片url
                        avatar = data.getString("avatar");
                        String extendType = data.getString("extendType");

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mailbox.setText(email);
                                phone.setText(phonenumber);
                                name.setText(nickName);
                                edit.putString("extendType", extendType);
                                edit.commit();
                                RequestOptions options = new RequestOptions()
                                        .placeholder(R.drawable.avatarlogo)
                                        .fallback(R.drawable.avatarlogo)
                                        .error(R.drawable.avatarlogo);
                                Glide.with(MainActivity.this).load(Constant.ip + avatar).apply(options).into(circleImage);
                                Glide.with(MainActivity.this).load(Constant.ip + avatar).apply(options).into(circle);

                            }
                        });
                    } else if (code == 500) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(final String msg) {
                super.onError(msg);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    //获取读写权限
    @AfterPermissionGranted(1)
    private void methodRequiresTwoPermission() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {//获取到权限

        } else {
            EasyPermissions.requestPermissions(
                    new PermissionRequest.Builder(this, 1, perms)
                            .setRationale("若不允许获取权限，你就会上传不了文件")
                            .setPositiveButtonText("确定")
                            .setNegativeButtonText("取消")
                            .setTheme(R.style.Dialog)
                            .build());
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
        Log.e("TAG", "onRequestPermissionsResult: ");
    }

    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        Log.e("TAG", "onPermissionsGranted: ");
    }

    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {//拒绝
        Log.e("TAG", "onPermissionsDenied: ");
    }


    //极光推送
    public void registerMessageReceiver() {
        mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        filter.addAction(MESSAGE_RECEIVED_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    //极光推送广播
    public class MessageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            try {
                if (MESSAGE_RECEIVED_ACTION.equals(intent.getAction())) {
                    String messge = intent.getStringExtra(KEY_MESSAGE);
                    String extras = intent.getStringExtra(KEY_EXTRAS);
                    StringBuilder showMsg = new StringBuilder();
                    showMsg.append(KEY_MESSAGE + " : " + messge + "\n");
                    if (!ExampleUtil.isEmpty(extras)) {
                        showMsg.append(KEY_EXTRAS + " : " + extras + "\n");
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    // 首页条目网络请求
    private void initData() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.createLoadingDialog(MainActivity.this, "正在加载中...");
            loadingDialog.show();
        }
        NetworkUtils.sendPost(Constant.ip + "/app/index/getMsgList", null, this, new NetworkUtils.HttpCallback() {
            @Override
            public void onSuccess(JSONObject res) {
                //如果res为空的话就不进行下面的操作
                if (res == null || this == null) {
                    return;
                }
                try {
                    int code = res.getInt("code");
                    if (code == 200) {
                        JSONArray data = res.getJSONArray("data");

                        //判断等于1的时候集合清除
                        if (String.valueOf(pageNum).equals("1")) {
                            list.clear();
                        }
                        for (int i = 0; i < data.length(); i++) {
                            //获取集合中的实体类
                            JSONObject jsonObject = data.getJSONObject(i);
                            Mainbean.DataBean dataBean = new Mainbean.DataBean();
                            dataBean.setCreateTime(jsonObject.getString("createTime"));//时间
                            dataBean.setType(jsonObject.getString("type"));//验收状态
                            dataBean.setContent(jsonObject.getString("content"));//验收内容
                            dataBean.setTaskStatus(jsonObject.getInt("taskStatus"));//状态
                            dataBean.setIsRead(jsonObject.getInt("isRead"));
                            dataBean.setId(jsonObject.getInt("id"));//添加id
                            dataBean.setTaskNo(jsonObject.getString("taskNo"));//添加taskno
                            dataBean.setTaskId(jsonObject.getInt("taskId"));
                            dataBean.setTaskTitle(jsonObject.getString("taskTitle"));//标题
                            dataBean.setIsUrgent(jsonObject.getInt("isUrgent"));
                            dataBean.setIsFixed(jsonObject.getInt("isFixed"));
                            list.add(dataBean);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (loadingDialog != null) {
                                    loadingDialog.dismiss();
                                    loadingDialog = null;
                                }
                                //适配器刷新
                                if (adapter != null) {
                                    adapter.notifyDataSetChanged();
                                }
                                home_RefreshLayout.closeHeaderOrFooter();
                            }
                        });

                    } else if (code == 403) {
                        String msg = res.getString("msg");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (loadingDialog != null) {
                                    loadingDialog.dismiss();
                                    loadingDialog = null;
                                }
                                dialog_one(MainActivity.this, msg).show();
                            }
                        });
                    } else if (code == 500) {
                        String msg = res.getString("msg");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (loadingDialog != null) {
                                    loadingDialog.dismiss();
                                    loadingDialog = null;
                                }
                                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

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
                            home_RefreshLayout.closeHeaderOrFooter();
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
                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                        home_RefreshLayout.closeHeaderOrFooter();
                    }
                });
            }

            @Override
            public void onErrorD() {
                super.onErrorD();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (loadingDialog != null) {
                            loadingDialog.dismiss();
                            loadingDialog = null;
                        }
                        home_RefreshLayout.closeHeaderOrFooter();
                    }
                });
            }
        });

    }

    //403的时候显示账号在另一台设备更新
    public Dialog dialog_one(Context context, String text) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Drawable d = new ColorDrawable(ContextCompat.getColor(context, R.color.line_font));
        d.setAlpha(200);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(d);
        dialog.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.remove("staffPhone");
                edit.remove("userNo");
                edit.remove("username");
                edit.remove("password");
                edit.remove("token");
                edit.remove("name");
                edit.clear();//数据清空
                edit.commit();
                dialog.dismiss();
                dialog.cancel();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        TextView customText = (TextView) dialog.findViewById(R.id.customText);//提示文字
        customText.setText(text);
        return dialog;
    }

    //设置侧面布局开关监听
    private void initEvent() {
        drawerbar = new ActionBarDrawerToggle(this, drawerLayout, null, R.string.app_name, R.string.app_name) {
            //菜单打开
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            // 菜单关闭
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerbar);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.circle:
                if (drawerLayout.isDrawerOpen(main_left_drawer_layout)) {
                    drawerLayout.closeDrawer(main_left_drawer_layout);
                } else {
                    drawerLayout.openDrawer(main_left_drawer_layout);
                }
                //判断有无网络
                if (!new IsNetwork().isNetworkAvailable(MainActivity.this)) {
                    Toast toast = Toast.makeText(MainActivity.this, "当前没有可用网络", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    return;
                }
                break;
            case R.id.Initiated_layout:
                startActivity(new Intent(MainActivity.this, MeInitiateActivity.class));
                break;
            case R.id.receive_layout:
                startActivity(new Intent(MainActivity.this, MyreceiveActivity.class));
                break;
            case R.id.Audit_layout:
                startActivity(new Intent(MainActivity.this, MyAuditActivity.class));
                break;
            case R.id.store_details_ll_close://发起
                Intent intent = new Intent(MainActivity.this, InitiateActivity.class);
                intent.putExtra("nickName", nickName);
                startActivity(intent);
                break;
            case R.id.relative_phone://修改手机号侧面布局
                startActivity(new Intent(MainActivity.this, Relative_phoneActivity.class));
                break;
            case R.id.Relative_mailbox://修改邮箱侧面布局
                startActivity(new Intent(MainActivity.this, Relative_mailboxActivity.class));
                break;
            case R.id.relative_pas://修改密码侧面布局
                startActivity(new Intent(MainActivity.this, Relative_pasActivity.class));
                break;
            case R.id.button://退出账号侧面布局
                edit.remove("staffPhone");
                edit.remove("userNo");
                edit.remove("username");
                edit.remove("password");
                edit.remove("token");
                edit.clear();//数据清空
                edit.commit();
                JPushInterface.deleteAlias(this, 0);
                //然后跳转到登录界面
                startActivity(new Intent(MainActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                Toast.makeText(MainActivity.this, "退出成功", Toast.LENGTH_SHORT).show();
                MainActivity.this.finish();
                break;
//            case R.id.circleImage://切换头像
//                EasyPhotos.createAlbum(this, true, GlideEngine.getInstance())
//                        .setFileProviderAuthority("com.example.yuhekejioa.provider")//com.huantansheng.easyphotos.demo.fileprovider
//                        .start(101);
//                break;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                //弹出提示，可以有多种方式
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
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
    // @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (RESULT_OK == resultCode) {
//            //相机或相册回调
//            if (requestCode == 101) {
//                ArrayList<Photo> resultPhotos = data.getParcelableArrayListExtra(EasyPhotos.RESULT_PHOTOS);
//                Log.e("TAG", "onActivityResult: "+ resultPhotos.get(0).path);
//                HashMap<String, String> maps = new HashMap<>();
//                NetworkUtils.upload(Constant.ip + "/app/user/updateAvatar", resultPhotos.get(0).path, maps, MainActivity.this, new NetworkUtils.HttpCallback() {
//                    @Override
//                    public void onSuccess(JSONObject res) {
//                        if (res == null || this == null) {
//                            return;
//                        }
//                        try {
//                            int code = res.getInt("code");
//                            if (code == 200) {
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        RequestOptions options = new RequestOptions()
//                                                .placeholder(R.drawable.avatarlogo)
//                                                .fallback(R.drawable.avatarlogo)
//                                                .error(R.drawable.avatarlogo);
//                                        //接口请求下来的图片
//                                        Glide.with(MainActivity.this).load(Constant.ip + avatar).apply(options).into(circleImage);
//                                    }
//                                });
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    @Override
//                    public void onError(String msg) {
//                        super.onError(msg);
//                        Log.e(Constant.TAG, "onError: " + msg);
//                    }
//                });
//                return;
//            }
//        }
//    }
    /*
      进首页根据登录保存的手机号来判断手机号是不是为空
      如果为空就不显示数据 弹个提示框 提示跳转到登录界面
        private boolean islogin ;
        islogin=islogin;
        public boolean islogin() {
            SharedPreferences pre = getActivity().getSharedPreferences("transition", MODE_PRIVATE);
            String phone = pre.getString("phone", null);
            if (phone == null) {
                return false;
            } else {
                return true;
           }
        }
     */

}