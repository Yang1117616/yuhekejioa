package com.example.yuhekejioa.My_recrive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuhekejioa.Adapter.FileAdapter;
import com.example.yuhekejioa.Adapter.WaitAdapter;
import com.example.yuhekejioa.Adapter.Waitadapterx;
import com.example.yuhekejioa.Bean.WantBean;
import com.example.yuhekejioa.My_Initiated.AcceptancefailedActivity;
import com.example.yuhekejioa.My_Initiated.DailyActivity;
import com.example.yuhekejioa.My_Initiated.MyExtensioninprogressActivity;
import com.example.yuhekejioa.R;
import com.example.yuhekejioa.Utils.Constant;
import com.example.yuhekejioa.Utils.LoadingDialog;
import com.example.yuhekejioa.Utils.NetworkUtils;
import com.example.yuhekejioa.Utils.SpacesItemDecoration;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

// 我接收的--------延期不通过
public class FailedtopostponeActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView back;//返回按钮
    private TextView numbering;//任务编号
    private TextView current_time1;//发起时间
    private TextView sponsor_name;//发起人
    private TextView enddate_text;//结束时间
    private TextView editText;//任务描述
    private RecyclerView recyclerView;//附件展示
    private Button button_submit;//提交按钮
    private TextView report;//汇报按钮
    private Button button_view;//查看每日工作按钮
    private TextView extensiontime;//延期时间
    private TextView reasonforextension;//申请延期原因
    private int taskId;
    private List<WantBean.DataBean.SysFilesSponsorBean> list = new ArrayList();
    private String taskNo;
    private int id;

    private String url;
    private TextView edit_title;
    private Dialog loadingDialog;
    private TextView text_nofile;
    private HashMap<String, String> map;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failedtopostpone);
        Intent intent = getIntent();
        taskId = intent.getIntExtra("taskId", 0);
        id = intent.getIntExtra("id", 0);
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
        map.put(".mpg", "video/mpeg");   map.put(".text","text/plain");
    }

    private void initview() {
        numbering = findViewById(R.id.numbering);
        current_time1 = findViewById(R.id.current_time1);
        sponsor_name = findViewById(R.id.sponsor_name);
        enddate_text = findViewById(R.id.enddate_text);//结束时间
        editText = findViewById(R.id.editText);
        button_submit = findViewById(R.id.button_submit);// 完成按钮
        back = findViewById(R.id.back);//返回按钮
        recyclerView = findViewById(R.id.accomplish_List);//Recyclerview
        button_view = findViewById(R.id.button_view);//查看每日工作按钮
        report = findViewById(R.id.report);//汇报
        extensiontime = findViewById(R.id.extensiontime);//申请延期时间
        reasonforextension = findViewById(R.id.reasonforextension);//延期原因
        text_nofile = findViewById(R.id.text_nofile);
        edit_title = findViewById(R.id.edit_title);

        back.setOnClickListener(this);
        report.setOnClickListener(this);
        button_view.setOnClickListener(this);
        button_submit.setOnClickListener(this);
    }

    //网络请求
    private void initwangluo() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.createLoadingDialog(FailedtopostponeActivity.this, "正在加载中...");
            loadingDialog.show();
        }
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("taskId", String.valueOf(taskId));
        hashMap.put("msgId", String.valueOf(id));
        NetworkUtils.sendPost(Constant.ip + "/app/task/getTask", hashMap, this, new NetworkUtils.HttpCallback() {

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
                        //任务编号
                        taskNo = data.getString("taskNo");
                        final String createTime = data.getString("createTime");//发起时间
                        final String addNickName = data.getString("addNickName");//发起人
                        final String updateTime = data.getString("wantFinishTiem");//结束时间
                        final String taskDescribe = data.getString("taskDescribe");//任务描述
                        final String title = data.getString("title");
                        String inspectedState = data.getString("delayReason");//补充说明

                        JSONObject taskDelay = data.getJSONObject("taskDelay");//申请延期类

                        String newTime = taskDelay.getString("newTime");//申请延期时间

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
                                enddate_text.setText(updateTime);
                                editText.setText(taskDescribe);
                                extensiontime.setText(newTime);//延期时间
                                reasonforextension.setText(inspectedState);//补充说明
                                edit_title.setText(title);
                                if (sysFilesSponsor.length() > 0) {
                                    text_nofile.setVisibility(View.GONE);
                                } else {
                                    text_nofile.setVisibility(View.VISIBLE);
                                }
                                //设置布局管理器
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FailedtopostponeActivity.this);
                                recyclerView.setLayoutManager(linearLayoutManager);
                                int space = 8;
                                recyclerView.addItemDecoration(new SpacesItemDecoration(space));
                                Waitadapterx adapter = new Waitadapterx(FailedtopostponeActivity.this, list);
                                recyclerView.setAdapter(adapter);
                                adapter.setOnItemClickListener(new FileAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(int position) {
                                        initwangluo1(list.get(position));
                                    }
                                });
                            }
                        });

                    } else if(code==500){

                        String msg = res.getString("msg");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (loadingDialog != null) {
                                    loadingDialog.dismiss();
                                    loadingDialog = null;
                                }
                                Toast.makeText(FailedtopostponeActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                        new AlertDialog.Builder(FailedtopostponeActivity.this)
                                .setMessage(msg)
                                .setPositiveButton("确定", null)
                                .show();
                        Toast.makeText(FailedtopostponeActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void initwangluo1(WantBean.DataBean.SysFilesSponsorBean sysFilesSponsorBean) {

        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.createLoadingDialog(FailedtopostponeActivity.this, "正在加载中...");
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
                        fileURI = FileProvider.getUriForFile(FailedtopostponeActivity.this,
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
                    //Toast.makeText(FailedtopostponeActivity.this, "无法打开该格式文件", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(FailedtopostponeActivity.this, "无法打开该格式文件", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                FailedtopostponeActivity.this.finish();
                break;
            case R.id.report://汇报界面
                Intent intent1 = new Intent(FailedtopostponeActivity.this, ReportActivity.class);
                intent1.putExtra("id", taskId);
                intent1.putExtra("taskNo", taskNo);
                startActivity(intent1);

                break;
            case R.id.button_view:
                Intent intent = new Intent(FailedtopostponeActivity.this, DailyActivity.class);
                intent.putExtra("taskNo", taskNo);
                startActivity(intent);

               break;
            case R.id.button_submit://跳转到完成填写页面
                Intent intent2 = new Intent(FailedtopostponeActivity.this, CarryoutActivity.class);
                intent2.putExtra("taskNo", taskNo);
                intent2.putExtra("id", taskId);
                startActivity(intent2);
                break;
        }
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
