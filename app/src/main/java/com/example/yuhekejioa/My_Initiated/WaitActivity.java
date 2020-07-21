package com.example.yuhekejioa.My_Initiated;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectChangeListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.yuhekejioa.Adapter.FileAdapter;
import com.example.yuhekejioa.Adapter.WaitAdapter;
import com.example.yuhekejioa.Adapter.Waitadapterx;
import com.example.yuhekejioa.Bean.StatusBean;
import com.example.yuhekejioa.Bean.WantBean;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import pub.devrel.easypermissions.PermissionRequest;

// 我发起的---等待验收
public class WaitActivity extends AppCompatActivity implements View.OnClickListener {

    private int taskId;
    private TextView numbering;//任务编号
    private TextView current_time1;//发起时间
    private TextView sponsor_name;//发起人
    private TextView choosedepartment_text;//接收部门
    private TextView receiver_text;//接收人
    private TextView enddate_text;//结束时间
    private TextView editText;//任务描述
    private TextView editText1;//任务成果

    private RecyclerView recyclerview;//附件列表
    private EditText yuheedittext;//补充说明
    private TextView button_view;//查看每日工作
    private Button button_submit;//通过按钮
    private ImageView back;

    private List<WantBean.DataBean.SysFilesSponsorBean> list = new ArrayList();
    private String taskNo;
    private int num;
    private int id;
    private int idd = -1;
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
    private TextView edit_title;
    private Button button_fail;//重新修改

    private TimePickerView pvTime;
    private String endatext;
    private String trim;
    private int inspected;
    private Dialog loadingDialog;

    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }//Android避免进入一页面后EditText自动弹出软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);
        Intent intent = getIntent();
        taskId = intent.getIntExtra("taskId", 0);
        id = intent.getIntExtra("id", 0);
        initview();
        initdata();
    }

    //控件id
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
        recyclerview = findViewById(R.id.recyclerview);
        edit_title = findViewById(R.id.edit_title);

        button_view = findViewById(R.id.button_view);//查看每日工作
        button_fail = findViewById(R.id.button_fail);
        back.setOnClickListener(this);//返回按钮
        button_view.setOnClickListener(this);//查看每日工作
        button_submit.setOnClickListener(this);

    }

    //时间选择器
    private void initTimePicker() {
        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            //点击时间按钮确定按钮
            public void onTimeSelect(Date date, View v) {
                endatext = getTime(date);
                num = 2;
                initsubmit();
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

    private String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    @Override
    //点击事件
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.button_view:
                Intent intent = new Intent(WaitActivity.this, DailyActivity.class);
                intent.putExtra("taskNo", taskNo);
                startActivity(intent);
                break;
            case R.id.button_submit://通过
                num = 0;
                initsubmit();
                break;
        }
    }

//    private void showdialog() {
//        final Bundle bundle = new Bundle();
//        bundle.putBoolean(WheelDialogFragment.DIALOG_BACK, false);
//        bundle.putBoolean(WheelDialogFragment.DIALOG_CANCELABLE, false);
//        bundle.putBoolean(WheelDialogFragment.DIALOG_CANCELABLE_TOUCH_OUT_SIDE, false);
//        bundle.putString(WheelDialogFragment.DIALOG_LEFT, "取消");
//        bundle.putString(WheelDialogFragment.DIALOG_RIGHT, "确定");
//        bundle.putString(WheelDialogFragment.DIALOG_TITLE, "请选择");
//        String[]  strings = new String[2];
//        strings[0] = "通过";
//        // strings[1] = "未通过";
//        strings[1] = "重新修改";
//        bundle.putStringArray(WheelDialogFragment.DIALOG_WHEEL, strings);
//        WheelDialogFragment dialogFragment = WheelDialogFragment.newInstance(WheelDialogFragment.class, bundle);
//        dialogFragment.setWheelDialogListener(new WheelDialogFragment.OnWheelDialogListener() {
//            @Override
//            public void onClickLeft(WheelDialogFragment dialog, String value) {
//                dialog.dismiss();
//            }
//
//            @Override
//            public void onClickRight(WheelDialogFragment dialog, int value) {
//                String s = strings[value];
//                opinionselection.setText(s);
//                opinionselection.setTextColor(Color.parseColor("#ff000000"));
//
//                String s1 = opinionselection.getText().toString();
//
//                /*
//                opinionselection获取出来的字段 如果是通过 选择时间布局隐藏 如果是不通过重新修改时间隐藏 如果是重修修改 选择时间布局显示
//                 */
//                if (s1.equals("通过")) {
//                    linear_selectionperiod.setVisibility(View.GONE);
//                } else if (s1.equals("重新修改")) {
//                    linear_selectionperiod.setVisibility(View.VISIBLE);
//                }
//                dialog.dismiss();
//            }
//
//            @Override
//            public void onValueChanged(WheelDialogFragment dialog, String value) {
//            }
//        });
//        dialogFragment.show(getSupportFragmentManager(), "");
//    }

    //提交
    private void initsubmit() {

        HashMap<String, String> hashMap1 = new HashMap<>();
        hashMap1.put("inspected", String.valueOf(num));
        hashMap1.put("inspectedState", trim);
        hashMap1.put("taskId", String.valueOf(taskId));
        hashMap1.put("inspectedUpdateTime", endatext);//验收修改时间
        NetworkUtils.sendPost(Constant.ip + "/app/task/inspect", hashMap1, this, new NetworkUtils.HttpCallback() {
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
                                Toast.makeText(WaitActivity.this, msg, Toast.LENGTH_SHORT).show();
                                WaitActivity.this.finish();
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(WaitActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                        new AlertDialog.Builder(WaitActivity.this)
                                .setMessage(msg)
                                .setPositiveButton("确定", null)
                                .show();
                        Toast.makeText(WaitActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    //网络请求
    private void initdata() {
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
                        final String receiveDept = data.getString("receiveDept");//接收部门
                        final String receiveNickName = data.getString("receiveNickName");//接收人
                        final String updateTime = data.getString("wantFinishTiem");//结束时间
                        final String taskDescribe = data.getString("taskDescribe");//任务描述
                        final String result = data.getString("result");//任务成果
                        final String title = data.getString("title");
                        //等待验收按钮显示与隐藏
                        inspected = data.getInt("inspected");

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
                                numbering.setText(taskNo);
                                current_time1.setText(createTime);
                                sponsor_name.setText(addNickName);
                                receiver_text.setText(receiveNickName);
                                enddate_text.setText(updateTime);
                                editText.setText(taskDescribe);
                                choosedepartment_text.setText(receiveDept);
                                editText1.setText(result);
                                edit_title.setText(title);
                                //设置布局管理器
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WaitActivity.this);
                                recyclerview.setLayoutManager(linearLayoutManager);
                                int space = 8;
                                recyclerview.addItemDecoration(new SpacesItemDecoration(space));
                                Waitadapterx adapter = new Waitadapterx(WaitActivity.this, list);
                                recyclerview.setAdapter(adapter);


                                //重新修改按钮判断
                                if (inspected == -1) {
                                    button_fail.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            trim = yuheedittext.getText().toString().trim();
                                            if (TextUtils.isEmpty(trim)) {
                                                Toast toast = Toast.makeText(getApplicationContext(), "请输入补充说明", Toast.LENGTH_SHORT);
                                                toast.setGravity(Gravity.CENTER, 0, 0);
                                                toast.show();
                                                return;
                                            } else {
                                                if (pvTime != null) {
                                                    pvTime.show(view);//弹出时间选择器，传递参数过去，回调的时候则可以绑定此view
                                                }
                                                initTimePicker();
                                            }
                                        }
                                    });
                                } else {
                                    button_fail.setText("不通过");
                                    button_fail.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            num = 1;
                                            trim = yuheedittext.getText().toString().trim();
                                            if (TextUtils.isEmpty(trim)) {
                                                Toast toast = Toast.makeText(getApplicationContext(), "请输入补充说明", Toast.LENGTH_SHORT);
                                                toast.setGravity(Gravity.CENTER, 0, 0);
                                                toast.show();
                                                return;
                                            } else {
                                                //弹框提示是否显示不通过
                                                AlertDialog dialog = null;
                                                AlertDialog.Builder builder = new AlertDialog.Builder(WaitActivity.this);
                                                builder.setMessage("是否确认不通过？");
                                                builder.setCancelable(true);
                                                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        //取消dialog
                                                        dialog.dismiss();
                                                    }
                                                });
                                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        initsubmit();//点击按钮确定
                                                        dialog.dismiss();//弹框取消
                                                    }
                                                });
                                                dialog = builder.create();
                                                dialog.show();
                                                //设置dialog按钮颜色
                                                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#ff000000"));
                                                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#ff006bff"));
                                            }
                                        }
                                    });
                                }
                                adapter.setOnItemClickListener(new FileAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(int position) {
                                        initwangluo1(list.get(position));
                                    }
                                });
                            }
                        });

                    } else {
                        String msg = res.getString("msg");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(WaitActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                        new AlertDialog.Builder(WaitActivity.this)
                                .setMessage(msg)
                                .setPositiveButton("确定", null)
                                .show();
                        Toast.makeText(WaitActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    //文件下载
    private void initwangluo1(WantBean.DataBean.SysFilesSponsorBean sysFilesSponsorBean) {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.createLoadingDialog(WaitActivity.this, "正在加载中...");
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
                        fileURI = FileProvider.getUriForFile(WaitActivity.this,
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
                    //  Toast.makeText(WaitActivity.this, "无法打开该格式文件", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(WaitActivity.this, "无法打开该格式文件", Toast.LENGTH_SHORT).show();
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
