
package com.example.yuhekejioa.My_recrive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuhekejioa.Adapter.FileAdapter;
import com.example.yuhekejioa.Adapter.PendingAdapter;
import com.example.yuhekejioa.Bean.DeterminBean;
import com.example.yuhekejioa.My_Initiated.AcceptancefailedActivity;
import com.example.yuhekejioa.My_Initiated.DailyActivity;
import com.example.yuhekejioa.My_Initiated.TobeconfirmedActivity;
import com.example.yuhekejioa.My_Initiated.YanqideterminedActivity;
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

//我接收的-----延期待确认界面
public class LookingforwardtoconfirmationActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView numbering;//任务编号
    private TextView current_time1;//发起时间
    private TextView sponsor_name;//发起人
    private TextView enddate_text;//结束时间
    private TextView editText;//任务描述
    private TextView editText1;//延期原因
    private Button button_view;//查看每日工作
    private ImageView back;
    private TextView degreeofcompletion;//当前完成度
    private TextView extensiontime;//申请延期时间
    private int taskId;
    private String taskNo;
    private Dialog loadingDialog;
    private TextView edit_title;

    private RecyclerView accomplish_List;
    private TextView text_nofile;
    private List<DeterminBean.DataBean.SysFilesSponsorBean> list = new ArrayList<>();

    private String url;
    private String name;
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
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lookingforwardtoconfirmation);
        Intent intent = getIntent();
        taskId = intent.getIntExtra("taskId", 0);
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

    private void initwangluo() {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("taskId", String.valueOf(taskId));
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

                        JSONObject taskDelay = data.getJSONObject("taskDelay");//申请延期类
                        int progress = taskDelay.getInt("progress");//当前完成进度
                        String newTime = taskDelay.getString("newTime");//申请延期时间
                        String reason = taskDelay.getString("reason");//延期原因

                        JSONArray sysFilesSponsor = data.getJSONArray("sysFilesSponsor");//获取里面的集合

                        for (int i = 0; i < sysFilesSponsor.length(); i++) {
                            JSONObject jsonObject = sysFilesSponsor.getJSONObject(i);//集合中的实体类
                            //文件名称
                            name = jsonObject.getString("name");
                            String fileSize = jsonObject.getString("fileSize");//文件大小
                            url = Constant.ip + jsonObject.getString("url");

                            DeterminBean.DataBean.SysFilesSponsorBean sysFilesSponsorBean = new DeterminBean.DataBean.SysFilesSponsorBean();
                            sysFilesSponsorBean.setUrl(url);
                            sysFilesSponsorBean.setName(name);
                            sysFilesSponsorBean.setFileSize(fileSize);
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
                                degreeofcompletion.setText(progress + "%");
                                extensiontime.setText(newTime);
                                editText1.setText(reason);//延期原因

                                if (sysFilesSponsor.length() > 0) {
                                    text_nofile.setVisibility(View.GONE);
                                } else {
                                    text_nofile.setVisibility(View.VISIBLE);
                                }

                                //设置布局管理器
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(LookingforwardtoconfirmationActivity.this);
                                accomplish_List.setLayoutManager(linearLayoutManager);

                                int space = 8;
                                accomplish_List.addItemDecoration(new SpacesItemDecoration(space));
                                PendingAdapter adapter = new PendingAdapter(LookingforwardtoconfirmationActivity.this, list);
                                accomplish_List.setAdapter(adapter);

                                adapter.setOnItemClickListener(new FileAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(int position) {
                                        initwangluo(list.get(position));
                                    }
                                });
                            }
                        });
                    } else if (code==500){
                        String msg = res.getString("msg");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LookingforwardtoconfirmationActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(LookingforwardtoconfirmationActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


    private void initview() {
        numbering = findViewById(R.id.taskNo);
        current_time1 = findViewById(R.id.current_time);
        sponsor_name = findViewById(R.id.sponsor_name);
        enddate_text = findViewById(R.id.enddate_text);
        editText = findViewById(R.id.editText);
        editText1 = findViewById(R.id.editText1);//延期成果
        back = findViewById(R.id.back);
        button_view = findViewById(R.id.button_view);//查看每日工作
        degreeofcompletion = findViewById(R.id.degreeofcompletion);
        extensiontime = findViewById(R.id.extensiontime);
        edit_title = findViewById(R.id.edit_title);
        accomplish_List = findViewById(R.id.accomplish_List);
        text_nofile = findViewById(R.id.text_nofile);
        back.setOnClickListener(this);
        button_view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                LookingforwardtoconfirmationActivity.this.finish();
                break;
            case R.id.button_view:
                Intent intent = new Intent(LookingforwardtoconfirmationActivity.this, DailyActivity.class);
                intent.putExtra("taskNo", taskNo);
                startActivity(intent);
                LookingforwardtoconfirmationActivity.this.finish();
                break;
        }
    }

    //文件下载
    private void initwangluo(DeterminBean.DataBean.SysFilesSponsorBean sysFilesSponsorBean) {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.createLoadingDialog(LookingforwardtoconfirmationActivity.this, "正在加载中...");
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
                        fileURI = FileProvider.getUriForFile(LookingforwardtoconfirmationActivity.this,
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
                    //   Toast.makeText(TobeconfirmedActivity.this, "无法打开该格式文件", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(LookingforwardtoconfirmationActivity.this, "无法打开该格式文件", Toast.LENGTH_SHORT).show();
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
