package com.example.yuhekejioa.My_Initiated;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.yuhekejioa.Adapter.FileAdapter;
import com.example.yuhekejioa.Adapter.WaitAdapter;
import com.example.yuhekejioa.Bean.InitiateBean;
import com.example.yuhekejioa.Bean.WantBean;
import com.example.yuhekejioa.Bean.filebean;
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.PermissionRequest;


//  我发起的-----修改页面
public class ModifyActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout back;
    private TextView sponsor_name;//发起人姓名
    private TextView choosedepartment_text;//请选择接收部门
    private TextView receiver_text;//请选择接收人
    private TextView enddate_text;//请选择日期
    private EditText editText;//输入任务描述输入框
    private ImageView add_image; //上传文件文件按钮
    private Button button_submit;//提交按钮
    private RecyclerView nestedListView;//条目列表
    private List<Integer> list_int = new ArrayList<>();//选择部门传的部门id
    private int deptId;//部门id
    private String deptName;//选择的部门
    private List<String> list_string = new ArrayList<>();//选择接收人id
    private String nickName;//接收人名字
    private String userNo;//员工编号
    private TimePickerView pvTime;//时间类
    public static final int IMPORT_REQUEST_CODE = 10005;
    private String fileSizeString = "0.00";//文件大小
    private String upLoadFileName;//文件名字
    private File file;
    private String path;
    //添加获取的文件路径集合
    private List<String> strings = new ArrayList<>();
    //{后缀名，MIME类型}
    private final String[] MIME_MapTable = {
            ".3gp", "video/3gpp", ".apk", "application/vnd.android.package-archive", ".asf", "video/x-ms-asf",
            ".avi", "video/x-msvideo", ".bin", "application/octet-stream", ".bmp", "image/bmp",
            ".c", "text/plain", ".class", "application/octet-stream", ".conf", "text/plain",
            ".cpp", "text/plain", ".doc", "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            ".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", ".xls", "application/vnd.ms-excel", ".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", ".exe", "application/octet-stream", ".gif", "image/gif",
            ".gtar", "application/x-gtar", ".gz", "application/x-gzip", ".h", "text/plain", ".htm", "text/html", ".html", "text/html", ".jar", "application/java-archive",
            ".java", "text/plain", ".jpeg", "image/jpeg", ".jpg", "image/jpeg",
            ".js", "application/x-javascript", ".log", "text/plain", ".m3u", "audio/x-mpegurl", ".m4a", "audio/mp4a-latm", ".m4b", "audio/mp4a-latm",
            ".m4p", "audio/mp4a-latm", ".m4u", "video/vnd.mpegurl",
            ".m4v", "video/x-m4v", ".mov", "video/quicktime", ".mp2", "audio/x-mpeg", ".mp3", "audio/x-mpeg", ".mp4", "video/mp4",
            ".mpc", "application/vnd.mpohun.certificate", ".mpe", "video/mpeg", ".mpeg", "video/mpeg", ".mpg", "video/mpeg", ".mpg4", "video/mp4", ".mpga", "audio/mpeg", ".msg", "application/vnd.ms-outlook", ".ogg", "audio/ogg",
            ".pdf", "application/pdf", ".png", "image/png",
            ".pps", "application/vnd.ms-powerpoint", ".ppt", "application/vnd.ms-powerpoint",
            ".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation", ".prop", "text/plain", ".rc", "text/plain",
            ".rmvb", "audio/x-pn-realaudio", ".rtf", "application/rtf", ".sh", "text/plain",
            ".tar", "application/x-tar", ".tgz", "application/x-compressed", ".txt", "text/plain", ".wav", "audio/x-wav",
            ".wma", "audio/x-ms-wma", ".wmv", "audio/x-ms-wmv",
            ".wps", "application/vnd.ms-works", ".xml", "text/plain", ".z", "application/x-compress",
            ".zip", "application/x-zip-compressed", "", "*/*"
    };
    private int taskId;
    private String url;
    private String receive;
    private int msgid;
    private List<WantBean.DataBean.SysFilesSponsorBean> list = new ArrayList();
    private List<WantBean.DataBean.SysFilesSponsorBean> list1;
    private EditText edittitle;
    private String[] list_arr;
    private TextView start_time;
    private Dialog loadingDialog;
    private String extendType;
    private TextView prompt;
    private WaitAdapter adapter;
    private WantBean.DataBean.SysFilesSponsorBean sysFilesSponsorBean;
    private String deptIds;


    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //Android避免进入一页面后EditText自动弹出软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        Intent intent = getIntent();
        taskId = intent.getIntExtra("taskId", 0);
        msgid = intent.getIntExtra("id", 0);
        SharedPreferences tokens = getSharedPreferences("tokens", MODE_PRIVATE);
        extendType = tokens.getString("extendType", "");
        initview();
        methodRequiresTwoPermission();
        initwangluo();
    }

    //获取控件id
    private void initview() {
        back = findViewById(R.id.back);
        sponsor_name = findViewById(R.id.sponsor_name);
        choosedepartment_text = findViewById(R.id.choosedepartment_text);
        receiver_text = findViewById(R.id.receiver_text);
        enddate_text = findViewById(R.id.enddate_text);
        editText = findViewById(R.id.editText);
        add_image = findViewById(R.id.add_image);
        button_submit = findViewById(R.id.button_submit);
        nestedListView = findViewById(R.id.nestedlistView);
        start_time = findViewById(R.id.start_time);
        edittitle = findViewById(R.id.edit_title);
        prompt = findViewById(R.id.prompt);
        prompt.setText(extendType);

        back.setOnClickListener(this);
        button_submit.setOnClickListener(this);
        choosedepartment_text.setOnClickListener(this);//接收部门
        receiver_text.setOnClickListener(this);
        enddate_text.setOnClickListener(this);//结束时间
    }

    private void initwangluo() {
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
                        final String addNickName = data.getString("addNickName");//发起人
                        final String receiveDept = data.getString("receiveDept");//接收部门
                        final String receiveNickName = data.getString("receiveNickName");//接收人
                        final String updateTime = data.getString("wantFinishTiem");//结束时间
                        final String taskDescribe = data.getString("taskDescribe");//任务描述
                        String tasktitle = data.getString("title");//任务标题
                        String createTime = data.getString("createTime");//发起时间
                        receive = data.getString("receive");
                        JSONArray sysFilesSponsor = data.getJSONArray("sysFilesSponsor");//文件管理的集合类

                        for (int i = 0; i < sysFilesSponsor.length(); i++) {
                            JSONObject jsonObject = sysFilesSponsor.getJSONObject(i);
                            String name = jsonObject.getString("name");//文件名字
                            String fileSize = jsonObject.getString("fileSize");
                            url = Constant.ip + jsonObject.getString("url");//文件url
                            String id = jsonObject.getString("id");
                            sysFilesSponsorBean = new WantBean.DataBean.SysFilesSponsorBean();
                            sysFilesSponsorBean.setName(name);
                            sysFilesSponsorBean.setFileSize(fileSize);
                            sysFilesSponsorBean.setUrl(ModifyActivity.this.url);//添加文件url
                            sysFilesSponsorBean.setId(id);
                            list.add(sysFilesSponsorBean);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                sponsor_name.setText(addNickName);
                                receiver_text.setText(receiveNickName);
                                enddate_text.setText(updateTime);
                                editText.setText(taskDescribe);
                                choosedepartment_text.setText(receiveDept);
                                edittitle.setText(tasktitle);//添加任务标题
                                start_time.setText(createTime);//发起时间
//                                for (int i = 0; i < list.size(); i++) {
//                                    initwangluo2(list.get(i));
//                                }
                                //设置布局管理器
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ModifyActivity.this);
                                nestedListView.setLayoutManager(linearLayoutManager);
                                int space = 8;
                                nestedListView.addItemDecoration(new SpacesItemDecoration(space));

                                adapter = new WaitAdapter(ModifyActivity.this, list, strings);
                                nestedListView.setAdapter(adapter);
                                adapter.notifyDataSetChanged();
                                adapter.setOnItemClickListener(new FileAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(int position) {
                                        initwangluo1(list.get(position));
                                    }
                                });
                                adapter.setOnStringClickListener(new WaitAdapter.OnItemListenter() {
                                    @Override
                                    public void onItemClick(StringBuilder defile) {
                                        //判断是否为空
                                        if (TextUtils.isEmpty(defile)) {

                                        } else {
                                            deptIds = defile.toString();
                                        }
                                    }
                                });
                            }
                        });
                    } else if (code == 500) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ModifyActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(ModifyActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    //一进来就先下载下文件来
//    private void initwangluo2(WantBean.DataBean.SysFilesSponsorBean sysFilesSponsorBean) {
//        final String absolutePath = getExternalCacheDir().getAbsolutePath();//文件路径
//        Log.e("TAG", "initwangluo1: " + absolutePath);
//        //下载文件
//        NetworkUtils.download(sysFilesSponsorBean.getUrl(), absolutePath, sysFilesSponsorBean.getName(), new NetworkUtils.downloadCallback() {
//            @Override
//            public void onSuccess(String res) {
//
//                File out = new File(absolutePath + "/" + sysFilesSponsorBean.getName());
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        strings.add(out.getPath());
//                        adapter.notifyDataSetChanged();
//                    }
//                });
//            }
//        });
//    }

    //文件下载
    private void initwangluo1(WantBean.DataBean.SysFilesSponsorBean sysFilesSponsorBean) {

        final String absolutePath = getExternalCacheDir().getAbsolutePath();//文件路径
        Log.e("TAG", "initwangluo1: " + absolutePath);
        //下载文件
        NetworkUtils.download(sysFilesSponsorBean.getUrl(), absolutePath, sysFilesSponsorBean.getName(), new NetworkUtils.downloadCallback() {
            @Override
            public void onSuccess(String res) {

                Intent intent = new Intent();
                //设置intent的Action属性
                intent.setAction(Intent.ACTION_VIEW);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                try {
                    File out = new File(absolutePath + "/" + sysFilesSponsorBean.getName());
                    Uri fileURI;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        fileURI = FileProvider.getUriForFile(ModifyActivity.this,
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
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ModifyActivity.this, "无法打开该格式文件", Toast.LENGTH_SHORT).show();
                        }
                    });
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back://返回按钮
                ModifyActivity.this.finish();
                break;
            case R.id.button_submit://提交按钮
                initsubmit();
                break;
            case R.id.choosedepartment_text://选择部门
                showdialog1();
                break;
            case R.id.enddate_text://选择结束时间
                if (pvTime != null) {
                    pvTime.show(view);//弹出时间选择器，传递参数过去，回调的时候则可以绑定此view
                }
                initTimePicker();
                break;
            case R.id.receiver_text:
                String s = choosedepartment_text.getText().toString();//判断接收部门是否有数据
                if (s.equals("请选择")) {
                    Toast.makeText(ModifyActivity.this, "请选择接收部门", Toast.LENGTH_SHORT).show();
                    return;
                }
                showdialog();
                break;
        }
    }

    //提交方法
    private void initsubmit() {
        //接收部门
        String choosed = choosedepartment_text.getText().toString();
        if (choosed.equals("请选择")) {
            Toast.makeText(this, "请选择接收部门", Toast.LENGTH_SHORT).show();
            return;
        }
        //接受人
        final String receiver = receiver_text.getText().toString();
        if (receiver.equals("请选择")) {
            Toast.makeText(this, "请选择接收人", Toast.LENGTH_SHORT).show();
            return;
        }
        //请选择时间
        String enddate = enddate_text.getText().toString();
        if (enddate.equals("请选择")) {
            Toast.makeText(this, "请选择时间", Toast.LENGTH_SHORT).show();
            return;
        }
        String edit = editText.getText().toString();
        if (TextUtils.isEmpty(edit)) {
            Toast.makeText(this, "任务描述不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String title = edittitle.getText().toString();
        if (TextUtils.isEmpty(title)) {
            Toast.makeText(this, "请输入任务标题", Toast.LENGTH_SHORT).show();
            return;
        }
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.createLoadingDialog(ModifyActivity.this, "正在加载中...");
            loadingDialog.show();
        }
        //提交任务单接口
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("receive", receive);//员工编号
        hashMap.put("taskDescribe", edit);//任务描述
        hashMap.put("wantFinishTiem", enddate);//时间
        hashMap.put("taskId", String.valueOf(taskId));//taskId
        hashMap.put("title", title);//任务标题
        hashMap.put("delFiles", deptIds);
        NetworkUtils.uploadImage(Constant.ip + "/app/task/update", strings, hashMap, this, new NetworkUtils.HttpCallback() {
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
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (loadingDialog != null) {
                                    loadingDialog.dismiss();
                                    loadingDialog = null;
                                }
                                Toast.makeText(ModifyActivity.this, msg, Toast.LENGTH_SHORT).show();
                                ModifyActivity.this.finish();
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
                                Toast.makeText(ModifyActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(ModifyActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    //选择文件选择 然后进行上传
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMPORT_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();
                if (uri != null) {
                    path = getPath(this, uri);
                    if (path != null) {

                        //获取到的file文件
                        file = new File(path);
                        if (file.exists()) {
                            //文件路径
                            strings.add(file.getPath());
                            Log.e("TAG", "onActivityResult:---" + strings.size());
                            //文件名字
                            upLoadFileName = file.getName();
                        }
                        //把文件内存大小转换格式
                        long fileS = 0;
                        if (file.exists()) {
                            FileInputStream fis = null;
                            try {
                                fis = new FileInputStream(file);
                                fileS = fis.available();
                                fis.close();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                file.createNewFile();
                                Toast.makeText(this, "文件不存在", Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                        DecimalFormat df = new DecimalFormat("#0.00");
                        if (fileS < 1024) {
                            fileSizeString = df.format((double) fileS) + "B";
                        } else if (fileS < 1048576) {
                            fileSizeString = df.format((double) fileS / 1024) + "K";
                        } else if (fileS < 1073741824) {
                            fileSizeString = df.format((double) fileS / 1048576) + "M";
                        } else {
                            fileSizeString = df.format((double) fileS / 1073741824) + "G";
                        }
                    }
                }
                sysFilesSponsorBean = new WantBean.DataBean.SysFilesSponsorBean();
                sysFilesSponsorBean.setName(upLoadFileName);
                sysFilesSponsorBean.setFileSize(fileSizeString);
                sysFilesSponsorBean.setUrl(Constant.ip + file.getPath());
                list1 = new ArrayList<>();
                list1.add(sysFilesSponsorBean);
                list.addAll(list1);
                adapter.notifyDataSetChanged();
            }
        }
    }

    //选择人
    private void showdialog() {
        //网络请求
        HashMap<String, String> map = new HashMap<>();
        map.put("deptId", String.valueOf(deptId));
        NetworkUtils.sendPost(Constant.ip + "/app/task/getReceiveUser", map, this, new NetworkUtils.HttpCallback() {
            @Override
            public void onSuccess(JSONObject res) {
                if (res == null || this == null) {
                    return;
                }
                try {
                    int code = res.getInt("code");
                    if (code == 200) {
                        JSONArray data = res.getJSONArray("data");
                        list_string.clear();
                        String[] arr1 = new String[data.length()];
                        for (int i = 0; i < data.length(); i++) {
                            JSONObject jsonObject = data.getJSONObject(i);
                            nickName = jsonObject.getString("nickName");
                            list_string.add(jsonObject.getString("userNo"));
                            arr1[i] = jsonObject.getString("nickName");
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                final Bundle bundle = new Bundle();
                                bundle.putBoolean(WheelDialogFragment.DIALOG_BACK, false);
                                bundle.putBoolean(WheelDialogFragment.DIALOG_CANCELABLE, false);
                                bundle.putBoolean(WheelDialogFragment.DIALOG_CANCELABLE_TOUCH_OUT_SIDE, false);
                                bundle.putString(WheelDialogFragment.DIALOG_LEFT, "取消");
                                bundle.putString(WheelDialogFragment.DIALOG_RIGHT, "确定");
                                bundle.putString(WheelDialogFragment.DIALOG_TITLE, "请选择");
                                bundle.putStringArray(WheelDialogFragment.DIALOG_WHEEL, arr1);
                                WheelDialogFragment dialogFragment = WheelDialogFragment.newInstance(WheelDialogFragment.class, bundle);
                                dialogFragment.setWheelDialogListener(new WheelDialogFragment.OnWheelDialogListener() {
                                    @Override
                                    public void onClickLeft(WheelDialogFragment dialog, String value) {
                                        dialog.dismiss();
                                    }

                                    @Override
                                    public void onClickRight(WheelDialogFragment dialog, int value) {
                                        userNo = list_string.get(value);
                                        String s = arr1[value];
                                        receiver_text.setText(s);
                                        receiver_text.setTextColor(Color.parseColor("#ff000000"));
                                        dialog.dismiss();
                                    }

                                    @Override
                                    public void onValueChanged(WheelDialogFragment dialog, String value) {
                                    }
                                });
                                dialogFragment.show(getSupportFragmentManager(), "");

                            }
                        });
                    } else {
                        String msg = res.getString("msg");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ModifyActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(ModifyActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

    //选择部门
    private void showdialog1() {
        HashMap<String, String> hashMap = new HashMap<>();
        NetworkUtils.sendGet(Constant.ip + "/app/task/getDepts", hashMap, ModifyActivity.this, new NetworkUtils.HttpCallback() {
            @Override
            public void onSuccess(final JSONObject res) {
                //如果res为空的话就不进行下面的操作
                if (res == null || this == null) {
                    return;
                }
                try {
                    int code = res.getInt("code");
                    if (code == 200) {
                        list_int.clear();
                        //接口中获取的实体类
                        final JSONObject data = res.getJSONObject("data");
                        //实体类中的集合
                        JSONArray children = data.getJSONArray("children");
                        for (int i = 0; i < children.length(); i++) {
                            JSONObject jsonObject = children.getJSONObject(i);
                            JSONArray children1 = jsonObject.getJSONArray("children");
                            list_arr = new String[children1.length()];
                            for (int j = 0; j < children1.length(); j++) {
                                JSONObject jsonObject1 = children1.getJSONObject(j);
                                list_int.add(jsonObject1.getInt("deptId"));
                                deptName = jsonObject1.getString("deptName");
                                list_arr[i] = deptName;
                            }
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                final Bundle bundle = new Bundle();
                                bundle.putBoolean(WheelDialogFragment.DIALOG_BACK, false);
                                bundle.putBoolean(WheelDialogFragment.DIALOG_CANCELABLE, false);
                                bundle.putBoolean(WheelDialogFragment.DIALOG_CANCELABLE_TOUCH_OUT_SIDE, false);
                                bundle.putString(WheelDialogFragment.DIALOG_LEFT, "取消");
                                bundle.putString(WheelDialogFragment.DIALOG_RIGHT, "确定");
                                bundle.putString(WheelDialogFragment.DIALOG_TITLE, "请选择");
                                bundle.putStringArray(WheelDialogFragment.DIALOG_WHEEL, list_arr);
                                WheelDialogFragment dialogFragment = WheelDialogFragment.newInstance(WheelDialogFragment.class, bundle);
                                dialogFragment.setWheelDialogListener(new WheelDialogFragment.OnWheelDialogListener() {
                                    @Override
                                    public void onClickLeft(WheelDialogFragment dialog, String value) {
                                        dialog.dismiss();
                                    }

                                    @Override
                                    public void onClickRight(WheelDialogFragment dialog, int value) {
                                        deptId = list_int.get(value);
                                        String s = list_arr[value];

                                        choosedepartment_text.setText(s);
                                        choosedepartment_text.setTextColor(Color.parseColor("#ff000000"));
                                        dialog.dismiss();
                                    }

                                    @Override
                                    public void onValueChanged(WheelDialogFragment dialog, String value) {
                                    }
                                });
                                dialogFragment.show(getSupportFragmentManager(), "");
                            }
                        });
                    } else {
                        final String msg = (String) res.get("msg");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ModifyActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(ModifyActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    //时间选择器
    private void initTimePicker() {
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                enddate_text.setText(getTime(date));
                Log.i("pvTime", "onTimeSelect");
            }
        }).setTimeSelectChangeListener(new OnTimeSelectChangeListener() {
            @Override
            public void onTimeSelectChanged(Date date) {
                Log.i("pvTime", "onTimeSelectChanged");
            }
        })
                .setType(new boolean[]{true, true, true, true, true, true})
                .isDialog(true) //默认设置false ，内部实现将DecorView 作为它的父控件。
                .build();

        Dialog mDialog = pvTime.getDialog();
        if (mDialog != null) {

            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.BOTTOM);

            params.leftMargin = 0;
            params.rightMargin = 0;
            pvTime.getDialogContainerLayout().setLayoutParams(params);
            Window dialogWindow = mDialog.getWindow();
            if (dialogWindow != null) {
                dialogWindow.setWindowAnimations(com.bigkoo.pickerview.R.style.picker_view_slide_anim);//修改动画样式
                dialogWindow.setGravity(Gravity.BOTTOM);//改成Bottom,底部显示
            }
        }

    }

    //可根据需要自行截取数据显示
    private String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    //进入系统管理器 选择文件上传
    @AfterPermissionGranted(1)
    private void methodRequiresTwoPermission() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(this, perms)) {//获取到权限
            add_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter.notifyDataSetChanged();
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("*/*");//可以传任意类型文件
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    startActivityForResult(intent, IMPORT_REQUEST_CODE);
                }
            });
        } else {
            EasyPermissions.requestPermissions(
                    new PermissionRequest.Builder(this, 1, perms)
                            .setRationale("若不允许获取权限，你将会上传不了文件")
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

    //打开文件
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;//sdk版本是否大于4.4

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
//            else if (isDownloadsDocument(uri)) {
//                final String id = DocumentsContract.getDocumentId(uri);
//                final Uri contentUri = ContentUris.withAppendedId(
//                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
//
//                return getDataColumn(context, contentUri, null, null);
//            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    public String getDataColumn(Context context, Uri uri, String selection,
                                String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
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