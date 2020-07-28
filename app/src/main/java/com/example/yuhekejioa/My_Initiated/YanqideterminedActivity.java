package com.example.yuhekejioa.My_Initiated;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yuhekejioa.Adapter.FileAdapter;
import com.example.yuhekejioa.Adapter.WaitAdapter;
import com.example.yuhekejioa.Adapter.Waitadapterx;
import com.example.yuhekejioa.Bean.WantBean;
import com.example.yuhekejioa.My_recrive.ExtensioninprogressActivity;
import com.example.yuhekejioa.My_recrive.WaitingforacceptanceActivity;
import com.example.yuhekejioa.R;
import com.example.yuhekejioa.Utils.Constant;
import com.example.yuhekejioa.Utils.LoadingDialog;
import com.example.yuhekejioa.Utils.NetworkUtils;
import com.example.yuhekejioa.Utils.SpacesItemDecoration;
import com.example.yuhekejioa.WheelDialog.WheelDialogFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


//我发起的——----延期待确定
public class YanqideterminedActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView numbering;//任务编号
    private TextView current_time1;//发起时间
    private TextView sponsor_name;//发起人
    private TextView choosedepartment_text;//接收部门
    private TextView receiver_text;//接收人
    private TextView enddate_text;//结束时间
    private TextView editText;//任务描述
    private TextView editText1;//延期原因
    private TextView opinionselection;//请选择验收结果
    private EditText yuheedittext;//补充说明
    private Button button_view;//查看每日工作
    private Button button_submit;//提交按钮
    private RelativeLayout back;//返回按钮
    private TextView degreeofcompletion;//当前完成度
    private TextView extensiontime;//申请延期时间
    private int taskId;
    private String[] strings;//确认结果状态
    private String taskNo;

    private int num;//确认结果
    private int id;
    private String createTime;
    private String addNickName;
    private String receiveDept;
    private String receiveNickName;
    private String wantFinishTiem;
    private String taskDescribe;
    private String delayReason;
    private int progress;
    private String newTime;
    private int msgid;
    private TextView edit_title;
    private RecyclerView recyclerView;
    private List<WantBean.DataBean.SysFilesSponsorBean> list = new ArrayList();

    private String url;
    private HashMap<String, String> map;
    private String text;
    private Dialog loadingDialog;
    private TextView text_nofile;
    private final String[] MIME_MapTable = {
            //{后缀名，MIME类型}
            ".3gp", "video/3gpp",
            ".apk", "application/vnd.android.package-archive", ".asf", "video/x-ms-asf",
            ".avi", "video/x-msvideo", ".bin", "application/octet-stream", ".bmp", "image/bmp",
            ".c", "text/plain", ".class", "application/octet-stream",
            ".conf", "text/plain", ".cpp", "text/plain",
            ".doc", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", ".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            ".xls", "application/vnd.ms-excel", ".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
            ".exe", "application/octet-stream", ".gif", "image/gif", ".gtar", "application/x-gtar",
            ".gz", "application/x-gzip", ".h", "text/plain", ".htm", "text/html", ".html", "text/html",
            ".jar", "application/java-archive", ".java", "text/plain",
            ".jpeg", "image/jpeg", ".jpg", "image/jpeg", ".js", "application/x-javascript", ".log", "text/plain", ".mpc", "application/vnd.mpohun.certificate",
            ".mpe", "video/mpeg", ".mpeg", "video/mpeg",
            ".mpg", "video/mpeg", ".mpg4", "video/mp4", ".mpga", "audio/mpeg", ".msg", "application/vnd.ms-outlook", ".ogg", "audio/ogg", ".pdf", "application/pdf",
            ".png", "image/png", ".pps", "application/vnd.ms-powerpoint",
            ".ppt", "application/vnd.ms-powerpoint", ".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation", ".prop", "text/plain",
            ".rc", "text/plain", ".rmvb", "audio/x-pn-realaudio",
            ".rtf", "application/rtf", ".sh", "text/plain",
            ".tar", "application/x-tar", ".tgz", "application/x-compressed", ".txt", "text/plain", ".wav", "audio/x-wav",
            ".wma", "audio/x-ms-wma", ".wmv", "audio/x-ms-wmv", ".wps", "application/vnd.ms-works", ".xml", "text/plain", ".z", "application/x-compress",
            ".zip", "application/x-zip-compressed",
            "", "*/*"
    };

    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yanqidetermined);
        Intent intent = getIntent();
        taskId = intent.getIntExtra("taskId", 0);
        msgid = intent.getIntExtra("id", 0);
        initview();
        initwangluo();
        map = new HashMap<>();
        map.put(".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        map.put(".txt", "text/plain");
        map.put(".doc", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        map.put(".3gp", "video/3gpp");
        map.put(".apk", "application/vnd.android.package-archive");
        map.put(".asf", "video/x-ms-asf");
        map.put(".avi", "video/x-msvideo");
        map.put(".bin", "application/octet-stream");
        map.put(".bmp", "image/bmp");
        map.put(".xls", "application/vnd.ms-excel");
        map.put(".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        map.put(".exe", "application/octet-stream");
        map.put(".gif", "image/gif");
        map.put(".gz", "application/x-gzip");
        map.put(".jpeg", "image/jpeg");
        map.put(".jpg", "image/jpeg");
        map.put(".pdf", "application/pdf");
        map.put(".ppt", "application/vnd.ms-powerpoint");
        map.put(".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
        map.put(".xml", "text/plain");
        map.put(".zip", "application/x-zip-compressed");
        map.put(".mpe", "video/mpeg");
        map.put(".mpeg", "video/mpeg");
        map.put(".mpg", "video/mpeg");
        map.put(".text", "text/plain");
    }

    private void initview() {
        numbering = findViewById(R.id.taskNo);
        current_time1 = findViewById(R.id.current_time);
        sponsor_name = findViewById(R.id.sponsor_name);
        choosedepartment_text = findViewById(R.id.choosedepartment_text);
        receiver_text = findViewById(R.id.receiver_text);
        enddate_text = findViewById(R.id.enddate_text);
        editText = findViewById(R.id.editText);
        editText1 = findViewById(R.id.editText1);//任务 成果
        yuheedittext = findViewById(R.id.yuheedittext);
        button_submit = findViewById(R.id.button_submit);
        back = findViewById(R.id.back);
        opinionselection = findViewById(R.id.opinionselection);//请选择验收结果
        button_view = findViewById(R.id.button_view);//查看每日工作
        degreeofcompletion = findViewById(R.id.degreeofcompletion);//当前完成时间
        extensiontime = findViewById(R.id.extensiontime);//申请延期时间
        edit_title = findViewById(R.id.edit_title);
        recyclerView = findViewById(R.id.accomplish_List);//附件
        text_nofile = findViewById(R.id.text_nofile);
        back.setOnClickListener(this);//返回按钮
        opinionselection.setOnClickListener(this);//选择验收结果
        button_view.setOnClickListener(this);
        button_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                YanqideterminedActivity.this.finish();
                break;
            case R.id.opinionselection://选择验收结果
                showdialog();
                break;
            case R.id.button_view:
                Intent intent = new Intent(YanqideterminedActivity.this, DailyActivity.class);
                intent.putExtra("taskNo", taskNo);
                startActivity(intent);
                break;
            case R.id.button_submit:
                initsubmit();
                break;
        }
    }


    //提交
    private void initsubmit() {
        String s = opinionselection.getText().toString();
        if (s.equals("请选择")) {
            Toast.makeText(this, "请选择确认结果", Toast.LENGTH_SHORT).show();
            return;
        }
        String opinionselection_text = opinionselection.getText().toString();
        if (opinionselection_text.equals("通过")) {
            num = 0;
        } else if (opinionselection_text.equals("未通过")) {
            num = 1;
            text = yuheedittext.getText().toString();
            if (TextUtils.isEmpty(text)) {
                Toast.makeText(this, "请输入补充说明", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        HashMap<String, String> hashMap1 = new HashMap<>();
        hashMap1.put("taskId", String.valueOf(taskId));
        hashMap1.put("notReason", text);
        hashMap1.put("isAdopt", String.valueOf(num));
        hashMap1.put("delayId", String.valueOf(id));

        NetworkUtils.sendPost(Constant.ip + "/app/task/delayConfirm", hashMap1, this, new NetworkUtils.HttpCallback() {
            @Override
            public void onSuccess(JSONObject res) {
                if (res == null || this == null) {
                    return;
                }
                try {
                    int code = res.getInt("code");
                    final String msg = res.getString("msg");
                    if (code == 200) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(YanqideterminedActivity.this, msg, Toast.LENGTH_SHORT).show();
                                YanqideterminedActivity.this.finish();
                            }
                        });
                    } else if (code == 500) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(YanqideterminedActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(YanqideterminedActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

    // 确认结果
    private void showdialog() {
        final Bundle bundle = new Bundle();
        bundle.putBoolean(WheelDialogFragment.DIALOG_BACK, false);
        bundle.putBoolean(WheelDialogFragment.DIALOG_CANCELABLE, false);
        bundle.putBoolean(WheelDialogFragment.DIALOG_CANCELABLE_TOUCH_OUT_SIDE, false);
        bundle.putString(WheelDialogFragment.DIALOG_LEFT, "取消");
        bundle.putString(WheelDialogFragment.DIALOG_RIGHT, "确定");
        bundle.putString(WheelDialogFragment.DIALOG_TITLE, "请选择");
        strings = new String[2];
        strings[0] = "通过";
        strings[1] = "未通过";
        bundle.putStringArray(WheelDialogFragment.DIALOG_WHEEL, strings);
        WheelDialogFragment dialogFragment = WheelDialogFragment.newInstance(WheelDialogFragment.class, bundle);
        dialogFragment.setWheelDialogListener(new WheelDialogFragment.OnWheelDialogListener() {
            @Override
            public void onClickLeft(WheelDialogFragment dialog, String value) {
                dialog.dismiss();
            }

            @Override
            public void onClickRight(WheelDialogFragment dialog, int value) {
                String s = strings[value];
                opinionselection.setText(s);
                opinionselection.setTextColor(Color.parseColor("#ff000000"));
                dialog.dismiss();
            }

            @Override
            public void onValueChanged(WheelDialogFragment dialog, String value) {
            }
        });
        dialogFragment.show(getSupportFragmentManager(), "");
    }

    //网络请求
    private void initwangluo() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.createLoadingDialog(YanqideterminedActivity.this, "正在加载中...");
            loadingDialog.show();
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("taskId", String.valueOf(taskId));
        hashMap.put("msgId", String.valueOf(msgid));
        NetworkUtils.sendPost(Constant.ip + "/app/task/getTask", hashMap, this, new NetworkUtils.HttpCallback() {

            @Override
            public void onSuccess(JSONObject res) {
                if (res == null || this == null) {
                    return;
                }
                try {
                    int code = res.getInt("code");
                    String msg = res.getString("msg");
                    if (code == 200) {
                        JSONObject data = res.getJSONObject("data");
                        taskNo = data.getString("taskNo");//任务编号
                        //发起时间
                        createTime = data.getString("createTime");
                        //发起人
                        addNickName = data.getString("addNickName");
                        //接收部门
                        receiveDept = data.getString("receiveDept");
                        //接收人
                        receiveNickName = data.getString("receiveNickName");
                        //结束时间
                        wantFinishTiem = data.getString("wantFinishTiem");
                        //任务描述
                        taskDescribe = data.getString("taskDescribe");
                        //延期原因
                        delayReason = data.getString("delayReason");

                        final String title = data.getString("title");


                        JSONObject taskDelay = data.getJSONObject("taskDelay");//延期类
                        //当前延期完成度
                        progress = taskDelay.getInt("progress");
                        //延期时间
                        newTime = taskDelay.getString("newTime");
                        id = taskDelay.getInt("id");//延期类id
                        JSONArray sysFilesSponsor = data.getJSONArray("sysFilesSponsor");//文件管理的集合类


                        //如果集合等于0的时候
                        for (int i = 0; i < sysFilesSponsor.length(); i++) {
                            JSONObject jsonObject = sysFilesSponsor.getJSONObject(i);
                            String name = jsonObject.getString("name");//文件名字
                            String fileSize = jsonObject.getString("fileSize");
                            url = Constant.ip + jsonObject.getString("url");//文件url
                            WantBean.DataBean.SysFilesSponsorBean sysFilesSponsorBean = new WantBean.DataBean.SysFilesSponsorBean();
                            sysFilesSponsorBean.setName(name);
                            sysFilesSponsorBean.setFileSize(fileSize);
                            sysFilesSponsorBean.setUrl(url);//添加文件url
                            list.add(sysFilesSponsorBean);
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (loadingDialog != null) {
                                    loadingDialog.dismiss();
                                    loadingDialog = null;
                                }
                                numbering.setText(taskNo);
                                current_time1.setText(createTime);
                                sponsor_name.setText(addNickName);
                                receiver_text.setText(receiveNickName);
                                choosedepartment_text.setText(receiveDept);
                                editText.setText(taskDescribe);
                                enddate_text.setText(wantFinishTiem);
                                editText1.setText(delayReason);
                                degreeofcompletion.setText(progress + "%" + "");
                                extensiontime.setText(newTime);
                                edit_title.setText(title);

                                //设置布局管理器
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(YanqideterminedActivity.this);
                                recyclerView.setLayoutManager(linearLayoutManager);
                                int space = 8;
                                recyclerView.addItemDecoration(new SpacesItemDecoration(space));
                                Waitadapterx adapter = new Waitadapterx(YanqideterminedActivity.this, list);
                                recyclerView.setAdapter(adapter);
                                if (sysFilesSponsor.length() > 0) {
                                    text_nofile.setVisibility(View.GONE);
                                } else {
                                    text_nofile.setVisibility(View.VISIBLE);
                                }
                                adapter.setOnItemClickListener(new FileAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(int position) {
                                        initwangluo1(list.get(position));
                                    }
                                });

                            }
                        });
                    } else if(code==500) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (loadingDialog != null) {
                                    loadingDialog.dismiss();
                                    loadingDialog = null;
                                }
                                Toast.makeText(YanqideterminedActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                        new AlertDialog.Builder(YanqideterminedActivity.this)
                                .setMessage(msg)
                                .setPositiveButton("确定", null)
                                .show();
                        Toast.makeText(YanqideterminedActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    //文件下载
    private void initwangluo1(WantBean.DataBean.SysFilesSponsorBean sysFilesSponsorBean) {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.createLoadingDialog(YanqideterminedActivity.this, "正在加载中...");
            loadingDialog.show();
        }

        final String absolutePath = getExternalCacheDir().getAbsolutePath();//文件路径
        //下载文件
        NetworkUtils.download(sysFilesSponsorBean.getUrl(), absolutePath, sysFilesSponsorBean.getName(), new NetworkUtils.downloadCallback() {
            @Override
            public void onSuccess(String res) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (loadingDialog != null) {
                            loadingDialog.dismiss();
                            loadingDialog = null;
                        }
                    }
                });
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                try {
                    File out = new File(absolutePath + "/" + sysFilesSponsorBean.getName());
                    Uri fileURI;
                    String substring = sysFilesSponsorBean.getName().substring(sysFilesSponsorBean.getName().lastIndexOf("."));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        fileURI = FileProvider.getUriForFile(YanqideterminedActivity.this,
                                "com.example.yuhekejioa.provider",
                                out);
                        intent.setDataAndType(fileURI, map.get(substring));
                        if (loadingDialog != null) {
                            loadingDialog.dismiss();
                            loadingDialog = null;
                        }
                    } else {
                        fileURI = Uri.fromFile(out);
                        //设置intent的data和Type属性
                        for (int i = 0; i < MIME_MapTable.length; i++) {
                            intent.setDataAndType(fileURI, MIME_MapTable[i]);
                        }
                        if (loadingDialog != null) {
                            loadingDialog.dismiss();
                            loadingDialog = null;
                        }
                    }
                    //跳转
                    startActivity(intent);
                } catch (Exception e) { //当系统没有携带文件打开软件，提示
                    //    Toast.makeText(YanqideterminedActivity.this, "无法打开该格式文件", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(YanqideterminedActivity.this, "无法打开该格式文件", Toast.LENGTH_SHORT).show();
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
                    }
                });
            }
        });
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
