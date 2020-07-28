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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuhekejioa.Adapter.FileAdapter;
import com.example.yuhekejioa.Adapter.WaitAdapter;
import com.example.yuhekejioa.Adapter.Waitadapterx;
import com.example.yuhekejioa.Bean.WantBean;
import com.example.yuhekejioa.My_Initiated.AcceptancefailedActivity;
import com.example.yuhekejioa.My_Initiated.AcceptingmodificationActivity;
import com.example.yuhekejioa.My_Initiated.DailyActivity;
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

//我接收的-----验收修改中
public class My_AcceptingmodificationActivity extends AppCompatActivity implements View.OnClickListener {
    private int taskId;
    private TextView numbering;//任务编号
    private TextView current_time1;//发起时间
    private TextView sponsor_name;//发起人
    private TextView choosedepartment_text;//接收部门
    private TextView receiver_text;//接收人
    private TextView enddate_text;//结束时间
    private TextView editText;//任务描述
    private TextView editText1;//任务成果
    private TextView text_there;//有无附件
    private RecyclerView recyclerview;//附件列表
    private Button button_view;//查看每日工作
    private RelativeLayout back;
    private List<WantBean.DataBean.SysFilesSponsorBean> list = new ArrayList();
    private String taskNo;
    private HashMap<String, String> map;
    private String url;

    private TextView edit_title;


    // private LinearLayout applyforanextensionLayout;
    private TextView report;//汇报
    private int canDelay;
    private TextView yuheedittext;
    private String updateTime;
    private int id;
    private TextView reedit_time;
    private Button button_submit;
    private Dialog loadingDialog;
    private TextView text_nofile;
    private ImageView image_hurried;
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
//    private int isUrgent;
//    private int isFixed;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive__acceptingmodification);
        Intent intent = getIntent();
        taskId = intent.getIntExtra("taskId", 0);
        canDelay = intent.getIntExtra("canDelay", 0);
        id = intent.getIntExtra("id", 0);
//        isUrgent = intent.getIntExtra("isUrgent", 0);
//        isFixed = intent.getIntExtra("isFixed", 0);
        initview();
        initdata();
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

    private void initdata() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.createLoadingDialog(My_AcceptingmodificationActivity.this, "正在加载中...");
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
                    String msg = res.getString("msg");
                    if (code == 200) {
                        JSONObject data = res.getJSONObject("data");
                        //任务编号
                        taskNo = data.getString("taskNo");
                        final String createTime = data.getString("createTime");//发起时间
                        final String addNickName = data.getString("addNickName");//发起人
                        final String receiveDept = data.getString("receiveDept");//接收部门
                        final String receiveNickName = data.getString("receiveNickName");//接收人
                        //结束时间
                        updateTime = data.getString("wantFinishTiem");
                        final String taskDescribe = data.getString("taskDescribe");//任务描述
                        final String result = data.getString("result");//任务成果
                        final String title = data.getString("title");//任务标题
                        int isUrgent = data.getInt("isUrgent");
                        final String inspectedState = data.getString("inspectedState");
                        String inspectedUpdateTime = data.getString("inspectedUpdateTime");
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
                                enddate_text.setText(updateTime);
                                editText.setText(taskDescribe);
                                choosedepartment_text.setText(receiveDept);
                                editText1.setText(result);
                                edit_title.setText(title);//添加任务标题
                                yuheedittext.setText(inspectedState);
                                reedit_time.setText(inspectedUpdateTime);//重新修改时间

                                if (sysFilesSponsor.length() > 0) {
                                    text_nofile.setVisibility(View.GONE);
                                } else {
                                    text_nofile.setVisibility(View.VISIBLE);
                                }


                                //设置布局管理器
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(My_AcceptingmodificationActivity.this);
                                recyclerview.setLayoutManager(linearLayoutManager);
                                int space = 8;
                                recyclerview.addItemDecoration(new SpacesItemDecoration(space));
                                Waitadapterx adapter = new Waitadapterx(My_AcceptingmodificationActivity.this, list);
                                recyclerview.setAdapter(adapter);
                                adapter.setOnItemClickListener(new FileAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(int position) {
                                        initwangluo(list.get(position));
                                    }
                                });
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
                                Toast.makeText(My_AcceptingmodificationActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                        new AlertDialog.Builder(My_AcceptingmodificationActivity.this)
                                .setMessage(msg)
                                .setPositiveButton("确定", null)
                                .show();
                        Toast.makeText(My_AcceptingmodificationActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
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
        back = findViewById(R.id.back);
        recyclerview = findViewById(R.id.recyclerview);
        text_nofile = findViewById(R.id.text_nofile);
        yuheedittext = findViewById(R.id.yuheedittext);//补充说明
        button_view = findViewById(R.id.button_view);//查看每日工作
        image_hurried = findViewById(R.id.image_hurried);
        edit_title = findViewById(R.id.edit_title);
        reedit_time = findViewById(R.id.reedit_time);
        report = findViewById(R.id.report);//汇报按钮
        //  applyforanextensionLayout = findViewById(R.id.applyforanextensionLayout);//延期布局
        button_submit = findViewById(R.id.button_submit);

//        if (canDelay == 0) {
//            applyforanextensionLayout.setVisibility(View.GONE);
//        } else if (canDelay == 1) {
//            applyforanextensionLayout.setVisibility(View.VISIBLE);
//        }
        //判断是否是加急任务单
//        if (isUrgent == 0) {
//            image_hurried.setVisibility(View.GONE);
//        } else if (isUrgent == 1) {
//            image_hurried.setVisibility(View.VISIBLE);
//        }
//        //判断是否是固定任务单
//        if (isFixed == 0) {
//        } else if (isFixed == 1) {
//            button_view.setVisibility(View.VISIBLE);
//            button_submit.setVisibility(View.VISIBLE);
//            report.setVisibility(View.VISIBLE);
//        }
        back.setOnClickListener(this);//返回按钮
        button_view.setOnClickListener(this);//查看每日工作
        button_submit.setOnClickListener(this);
        report.setOnClickListener(this);//汇报
        // applyforanextensionLayout.setOnClickListener(this);//申请延期布局
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                My_AcceptingmodificationActivity.this.finish();
                break;
            case R.id.report://汇报
                Intent intent1 = new Intent(My_AcceptingmodificationActivity.this, ReportActivity.class);
                intent1.putExtra("taskNo", taskNo);
                intent1.putExtra("id", taskId);
                startActivity(intent1);

                break;
            case R.id.button_view://查看每日工作
                Intent intent = new Intent(My_AcceptingmodificationActivity.this, DailyActivity.class);
                intent.putExtra("taskNo", taskNo);
                startActivity(intent);
                break;
            case R.id.button_submit://完成填写
                Intent intent2 = new Intent(My_AcceptingmodificationActivity.this, CarryoutActivity.class);
                intent2.putExtra("taskNo", taskNo);
                intent2.putExtra("id", taskId);
                startActivity(intent2);
               My_AcceptingmodificationActivity.this.finish();
                break;
//            case R.id.applyforanextensionLayout://申请延期页面
//                //跳转到申请延期界面
//                Intent intent3 = new Intent(My_AcceptingmodificationActivity.this, ApplyforanextensionActivity.class);
//                intent3.putExtra("taskNo", taskNo);
//                intent3.putExtra("id", taskId);
//                intent3.putExtra("wantFinishTiem", updateTime);
//                startActivity(intent3);
//                My_AcceptingmodificationActivity.this.finish();
//                break;
        }

    }

    //文件下载
    private void initwangluo(WantBean.DataBean.SysFilesSponsorBean sysFilesSponsorBean) {

        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.createLoadingDialog(My_AcceptingmodificationActivity.this, "正在加载中...");
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
                        fileURI = FileProvider.getUriForFile(My_AcceptingmodificationActivity.this,
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
                    // Toast.makeText(My_AcceptingmodificationActivity.this, "无法打开该格式文件", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(My_AcceptingmodificationActivity.this, "无法打开该格式文件", Toast.LENGTH_SHORT).show();
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
