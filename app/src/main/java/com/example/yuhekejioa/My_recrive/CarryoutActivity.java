package com.example.yuhekejioa.My_recrive;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuhekejioa.Adapter.FileAdapter;
import com.example.yuhekejioa.Bean.filebean;
import com.example.yuhekejioa.MainActivity;
import com.example.yuhekejioa.My_Initiated.InitiateActivity;
import com.example.yuhekejioa.R;
import com.example.yuhekejioa.Utils.Constant;
import com.example.yuhekejioa.Utils.LoadingDialog;
import com.example.yuhekejioa.Utils.NetworkUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.huantansheng.easyphotos.utils.file.FileUtils.getPath;

// 我接收的------完成填写
public class CarryoutActivity extends AppCompatActivity implements View.OnClickListener {

    private String taskNo;
    private TextView taskNos;
    private EditText editText;
    private RecyclerView recyclerView;
    private ImageView add_image;
    private Button button_submit;
    private RelativeLayout back;
    public static final int IMPORT_REQUEST_CODE = 10005;

    private File file;
    private String path;

    private String fileSizeString = "0.00";//文件大小
    private String upLoadFileName;//文件名字

    private List<filebean> list_file = new ArrayList<>();//添加获取的文件名称和文件内存大小

    //添加获取的文件路径集合
    private List<String> strings = new ArrayList<>();
    private int id;
    private Dialog loadingDialog;
    private TextView prompt;
    private String extendType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        //Android避免进入一页面后EditText自动弹出软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carryout);
        Intent intent = getIntent();
        taskNo = intent.getStringExtra("taskNo");
        id = intent.getIntExtra("id", 0);
        SharedPreferences tokens = getSharedPreferences("tokens", MODE_PRIVATE);
        extendType = tokens.getString("extendType", "");
        intiview();
    }

    private void intiview() {
        taskNos = findViewById(R.id.taskNos);
        editText = findViewById(R.id.editText);
        recyclerView = findViewById(R.id.nestedlistView);
        add_image = findViewById(R.id.add_image);
        button_submit = findViewById(R.id.button_submit);
        back = findViewById(R.id.back);
        //添加任务单编号
        taskNos.setText(taskNo);
        prompt = findViewById(R.id.prompt);
        prompt.setText(extendType);
        back.setOnClickListener(this);
        add_image.setOnClickListener(this);
        button_submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.add_image:
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("*/*");//设置类型，我这里是任意类型，可以过滤文件类型
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, IMPORT_REQUEST_CODE);
                break;
            case R.id.button_submit:
                initsubmit();
                break;
        }
    }

    //提交任务
    private void initsubmit() {
        String bianhao = taskNos.getText().toString();
        String trim = editText.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            Toast.makeText(this, "请输入任务结果", Toast.LENGTH_SHORT).show();
            return;
        }

        if (loadingDialog == null) {
            loadingDialog = LoadingDialog.createLoadingDialog(CarryoutActivity.this, "正在加载中...");
            loadingDialog.show();
        }
        //提交任务单接口
        HashMap<String, String> hashMap = new HashMap<>();
        //添加文件
        hashMap.put("jobContent ", trim);
        hashMap.put("taskId ", String.valueOf(id));//任务描述
        hashMap.put("taskNo ", bianhao);

        NetworkUtils.uploadImage(Constant.ip + "/app/taskReceive/finish", strings, hashMap, this, new NetworkUtils.HttpCallback() {
            @Override
            public void onSuccess(JSONObject res) {
                try {
                    int code = res.getInt("code");
                    final String msg = res.getString("msg");
                    if (code == 200) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (loadingDialog != null) {
                                    loadingDialog.dismiss();
                                    loadingDialog = null;
                                }
                                Toast.makeText(CarryoutActivity.this, msg, Toast.LENGTH_SHORT).show();
                                CarryoutActivity.this.finish();
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
                                Toast.makeText(CarryoutActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                        Toast.makeText(CarryoutActivity.this, msg, Toast.LENGTH_SHORT).show();
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
                            //创建的集合 每次添加一条文件就往集合中添加一条
                            strings.add(file.getPath());

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
                        initfile();
                    }
                }
            }
            Log.e("导入失败", "");
        }
    }

    //文件管理器
    private void initfile() {
        filebean filebean = new filebean();
        filebean.setFilename(upLoadFileName);
        filebean.setFileram(fileSizeString);
        list_file.add(filebean);

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CarryoutActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //创建适配器
        FileAdapter fileAdapter = new FileAdapter(this, list_file, strings);
        recyclerView.setAdapter(fileAdapter);
    }

    //选择文件的一些方法
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
