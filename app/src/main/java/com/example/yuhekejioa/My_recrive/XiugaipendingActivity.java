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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuhekejioa.Adapter.FileAdapter;
import com.example.yuhekejioa.Adapter.PendingAdapter;
import com.example.yuhekejioa.Bean.DeterminBean;
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

//我接收的-----修改待确认
public class XiugaipendingActivity extends AppCompatActivity {
    private ImageView back;
    private TextView numbering;//任务编号
    private TextView current_time1;//发起时间
    private TextView sponsor_name;//发起人
    private TextView enddate_text;//结束时间
    private TextView editText;//任务描述
    private RecyclerView recyclerView;//附件展示
    private Button button_submit;//提交按钮
    private int id;
    private List<DeterminBean.DataBean.SysFilesSponsorBean> list = new ArrayList<>();
    private String taskNo;
    private int confirmType;
    private Button button_chakan;
    private final String[] MIME_MapTable = {
            //{后缀名，MIME类型}
            ".3gp", "video/3gpp", ".apk", "application/vnd.android.package-archive", ".asf", "video/x-ms-asf",
            ".avi", "video/x-msvideo", ".bin", "application/octet-stream", ".bmp", "image/bmp",
            ".c", "text/plain", ".class", "application/octet-stream", ".conf", "text/plain",
            ".cpp", "text/plain", ".doc", "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            ".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", ".xls", "application/vnd.ms-excel",
            ".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", ".exe", "application/octet-stream",
            ".gif", "image/gif", ".gtar", "application/x-gtar",
            ".gz", "application/x-gzip", ".h", "text/plain", ".htm", "text/html", ".html", "text/html",
            ".jar", "application/java-archive", ".java", "text/plain", ".jpeg", "image/jpeg", ".jpg", "image/jpeg",
            ".js", "application/x-javascript", ".log", "text/plain",
            ".mov", "video/quicktime", ".mpc", "application/vnd.mpohun.certificate",
            ".mpe", "video/mpeg", ".mpeg", "video/mpeg",
            ".mpg", "video/mpeg", ".mpg4", "video/mp4", ".mpga", "audio/mpeg", ".msg", "application/vnd.ms-outlook", ".ogg", "audio/ogg", ".pdf", "application/pdf",
            ".png", "image/png", ".pps", "application/vnd.ms-powerpoint",
            ".ppt", "application/vnd.ms-powerpoint", ".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation",
            ".prop", "text/plain", ".rc", "text/plain", ".rmvb", "audio/x-pn-realaudio", ".rtf", "application/rtf",
            ".sh", "text/plain", ".tar", "application/x-tar", ".tgz", "application/x-compressed", ".txt", "text/plain",
            ".wav", "audio/x-wav", ".wma", "audio/x-ms-wma", ".wmv", "audio/x-ms-wmv", ".wps", "application/vnd.ms-works", ".xml", "text/plain", ".z", "application/x-compress", ".zip", "application/x-zip-compressed",
            "", "*/*"};
    private String url;
    private int taskId;
    private TextView edit_title;
    private Dialog loadingDialog;

    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiugaipending);
        Intent intent = getIntent();
        //获取的id
        id = intent.getIntExtra("id", 0);
        confirmType = intent.getIntExtra("confirmType", 0);
        taskId = intent.getIntExtra("taskId", 0);
        initview();
        initdata();

    }

    private void initview() {
        numbering = findViewById(R.id.numbering);
        current_time1 = findViewById(R.id.current_time1);
        sponsor_name = findViewById(R.id.sponsor_name);
        enddate_text = findViewById(R.id.enddate_text);
        editText = findViewById(R.id.editText);
        button_submit = findViewById(R.id.button_submit);
        back = findViewById(R.id.back);
        recyclerView = findViewById(R.id.accomplish_List);
        button_chakan = findViewById(R.id.button_chakan);

        edit_title = findViewById(R.id.edit_title);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initsubmit();
            }
        });
        //查看每日工作
        button_chakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(XiugaipendingActivity.this, DailyActivity.class);
                intent.putExtra("taskNo", taskNo);
                startActivity(intent);
            }
        });
    }

    //提交
    private void initsubmit() {
        HashMap<String, String> hashMap1 = new HashMap<>();
        hashMap1.put("taskId", String.valueOf(id));
        hashMap1.put("taskNo", taskNo);
        hashMap1.put("confirmType", String.valueOf(confirmType));
        NetworkUtils.sendPost(Constant.ip + "/app/taskReceive/confirm", hashMap1, this, new NetworkUtils.HttpCallback() {
            @Override
            public void onSuccess(JSONObject res) {
                if (res == null || this == null) {
                    return;
                }
                try {
                    int code = res.getInt("code");
                    if (code == 200) {
                        final String msg = res.getString("msg");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(XiugaipendingActivity.this, msg, Toast.LENGTH_SHORT).show();

                                XiugaipendingActivity.this.finish();
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
                        Toast.makeText(XiugaipendingActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    //网络请求
    private void initdata() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("taskId", String.valueOf(id));
        hashMap.put("msgId", String.valueOf(taskId));
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
                        JSONArray sysFilesSponsor = data.getJSONArray("sysFilesSponsor");//获取里面的集合
                        for (int i = 0; i < sysFilesSponsor.length(); i++) {
                            JSONObject jsonObject = sysFilesSponsor.getJSONObject(i);//集合中的实体类
                            String name = jsonObject.getString("name");//文件名称
                            String fileSize = jsonObject.getString("fileSize");//文件大小
                            url = Constant.ip + jsonObject.getString("url");//文件url
                            DeterminBean.DataBean.SysFilesSponsorBean sysFilesSponsorBean = new DeterminBean.DataBean.SysFilesSponsorBean();
                            sysFilesSponsorBean.setName(name);
                            sysFilesSponsorBean.setFileSize(fileSize);
                            sysFilesSponsorBean.setUrl(url);
                            list.add(sysFilesSponsorBean);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                numbering.setText(taskNo);
                                current_time1.setText(createTime);
                                sponsor_name.setText(addNickName);
                                enddate_text.setText(updateTime);
                                editText.setText(taskDescribe);
                                edit_title.setText(title);

                                //设置布局管理器
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(XiugaipendingActivity.this);
                                recyclerView.setLayoutManager(linearLayoutManager);

                                int space = 8;
                                recyclerView.addItemDecoration(new SpacesItemDecoration(space));
                                PendingAdapter adapter = new PendingAdapter(XiugaipendingActivity.this, list);
                                recyclerView.setAdapter(adapter);
                                adapter.setOnItemClickListener(new FileAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(int position) {
                                        initwangluo1(list.get(position));
                                    }
                                });
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
                        new AlertDialog.Builder(XiugaipendingActivity.this)
                                .setMessage(msg)
                                .setPositiveButton("确定", null)
                                .show();
                        Toast.makeText(XiugaipendingActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    //文件下载
    private void initwangluo1(DeterminBean.DataBean.SysFilesSponsorBean sysFilesSponsorBean) {

        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.createLoadingDialog(XiugaipendingActivity.this, "正在加载中...");
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
                Intent intent = new Intent();
                //设置intent的Action属性
                intent.setAction(Intent.ACTION_VIEW);
//                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                try {
                    File out = new File(absolutePath + "/" + sysFilesSponsorBean.getName());
                    Uri fileURI;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        fileURI = FileProvider.getUriForFile(XiugaipendingActivity.this,
                                "com.example.yuhekejioa.provider",
                                out);
                    } else {
                        fileURI = Uri.fromFile(out);
                    }
                    //设置intent的data和Type属性
                    for (int i = 0; i < MIME_MapTable.length; i++) {
                        intent.setDataAndType(fileURI, MIME_MapTable[i]);
                    }
                    //跳转
                    startActivity(intent);
                } catch (Exception e) { //当系统没有携带文件打开软件，提示
                 //   Toast.makeText(XiugaipendingActivity.this, "无法打开该格式文件", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(XiugaipendingActivity.this, "无法打开该格式文件", Toast.LENGTH_SHORT).show();
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
}
