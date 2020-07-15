package com.example.yuhekejioa.Utils;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.core.content.ContextCompat;


import com.example.yuhekejioa.R;
import com.huantansheng.easyphotos.models.album.entity.Photo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.example.yuhekejioa.Utils.Constant.catchError;


public class NetworkUtils {

    private static Dialog dialog;

    //下载文件
    public synchronized static void download(final String url, final String filePath, final String fileName, final downloadCallback callback) {
        Request request = new Request.Builder().url(url).build();
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onErrorD();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                String savePath = isExistDir(filePath);
                Log.e("TAG", "存储下载目录：" + savePath);
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    File file = new File(savePath, getNameFromUrl(fileName));
                    Log.e("TAG", "最终路径：" + file);
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                    }
                    fos.flush();
                    callback.onSuccess(file.getAbsolutePath());
                } catch (Exception e) {
                    callback.onError(e.toString());
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                        Log.e("TAG", "onResponse: ");
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                        Log.e("TAG", "onResponse: ");
                    }
                }
            }
        });
    }

    private static String isExistDir(String filePath) throws IOException {
        // 下载位置
        File downloadFile = new File(filePath);
        if (!downloadFile.mkdirs()) {
            downloadFile.createNewFile();
        }
        String savePath = downloadFile.getAbsolutePath();
        Log.e("TAG", "下载目录：" + savePath);
        return savePath;
    }

    public static String getNameFromUrl(String url) {
        return url;
    }

    //post请求
    public synchronized static void sendPost(final String urlPath, HashMap<String, String> paramsMap, final Context context, final HttpCallback callback) {

        if (urlPath == null) {
            return;
        }
        if (!urlPath.contains(".")) {
            return;
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        //Form表单格式的参数传递
        FormBody.Builder builder = new FormBody.Builder();
        if (paramsMap != null) {
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                String key = entry.getKey();
                String v = entry.getValue();
                if (key != null && v != null) {
                    builder.add(key, v);//设置参数名称和参数值
                    MyLog.e("键值", "key=" + key + "-----value=" + v);
                }
            }
        }
        FormBody formBody = builder.build();
        Request.Builder reqBuild = new Request.Builder();
        Request.Builder post = reqBuild.post(formBody);
        Request.Builder url = post.url(urlPath);
        if (context != null) {
            if (context.getSharedPreferences("tokens", MODE_PRIVATE) != null) {
                SharedPreferences pref2 = context.getSharedPreferences("tokens", MODE_PRIVATE);
                String token = pref2.getString("token", "");
                String name = pref2.getString("name", "");
                if (token.length() > 0) {
                    url.addHeader("token", token);
                    url.addHeader("name", name);
                    MyLog.e("传递token", "=" + token);
                }
            }
        }

        Request request = url.build();
        MyLog.e("开始请求", "开始url=" + urlPath);
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyLog.e("fail", "onFailure=" + e.getCause());
                MyLog.e("fail", "urlPath=" + urlPath + "信息=" + e.getMessage());
                callback.onErrorD();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //此方法运行在子线程中，不能在此方法中进行UI操作。
                String result = response.body().string();
                String getToken = response.header("token");
                if (getToken != null && getToken.length() > 0) {
                    SharedPreferences.Editor editor = context.getSharedPreferences("tokens", MODE_PRIVATE).edit();
                    editor.putString("token", getToken);
                    MyLog.e("保存token=", "==" + getToken);
                    editor.commit();
                }

                JSONObject jsonResult = null;
                try {
                    Log.e(Constant.TAG, "Post请求链接" + "=" + urlPath + "----" + "onResponse: " + result);
                    jsonResult = new JSONObject(result);
                    int code = jsonResult.getInt("code");
                    if (code == -8) {
                        final String msg = jsonResult.getString("msg");
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                dialog_two(context, msg).show();
                            }
                        });
                    } else if (code == -5) {
                        final String msg = jsonResult.getString("msg");
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                if (dialog == null) {
                                    dialog = dialog(context, msg);
                                    dialog.show();
                                }
                            }
                        });
                    }
                    callback.onSuccess(jsonResult);
                } catch (JSONException e) {
                    callback.onError(catchError);
                }
                response.body().close();
            }
        });
    }

    public static Dialog dialog(final Context context, String text) {
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
                dialog.dismiss();
                dialog.cancel();
                SharedPreferences pre = context.getSharedPreferences("transition", MODE_PRIVATE);
                SharedPreferences.Editor edit = pre.edit();
                edit.remove("phone");
                edit.remove("ps");
                edit.remove("userId");
                edit.remove("code");
                edit.commit();
                SharedPreferences.Editor editor = context.getSharedPreferences("tokens", MODE_PRIVATE).edit();
                editor.remove("token");
                editor.commit();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        TextView customText = (TextView) dialog.findViewById(R.id.customText);
        customText.setText(text);
        return dialog;
    }

    public static Dialog dialog_two(final Context context, final String text) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Drawable d = new ColorDrawable(ContextCompat.getColor(context, R.color.line_font));
        d.setAlpha(200);
        dialog.setContentView(R.layout.dialog_two);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.getWindow().setBackgroundDrawable(d);
        dialog.findViewById(R.id.confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                dialog.cancel();
                Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + text));
                context.startActivity(intent2);
            }
        });
        dialog.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                dialog.cancel();
            }
        });
        TextView customText = (TextView) dialog.findViewById(R.id.customText);
        customText.setText("账户已被冻结,请联系客服" + text);
        return dialog;
    }

    public synchronized static void sendGet(final String urlPath, HashMap<String, String> paramsMap, final Context context, final HttpCallback callback) {
        MyLog.e("get请求链接", "=" + urlPath);
        if (urlPath == null) {
            return;
        }
        if (!urlPath.contains(".")) {
            return;
        }
        HttpUrl.Builder urlBuilder = HttpUrl.parse(urlPath).newBuilder();
        if (paramsMap != null && paramsMap.size() > 0) {
            for (String key : paramsMap.keySet()) {
                if ((paramsMap.get(key)).isEmpty()) {
                    urlBuilder.addQueryParameter(key, "");
                } else {
                    String value = paramsMap.get(key);
                    urlBuilder.addQueryParameter(key, value);
                    MyLog.e("键值", "key=" + key + "-----value=" + value);
                }
            }
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request.Builder reqBuild = new Request.Builder();
        final Request.Builder url = reqBuild.url(urlBuilder.build());

        SharedPreferences pref2 = context.getSharedPreferences("tokens", MODE_PRIVATE);
        String token = pref2.getString("token", "");
        String name = pref2.getString("name", "");
        if (token.length() > 0) {
            url.addHeader("token", token);
            url.addHeader("name", name);
            MyLog.e("传递token", "=" + token);
        }

        final Request request = url.build();
        MyLog.e("开始请求", "开始url=" + urlPath);
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyLog.e("fail", "onFailure=" + e.getCause());
                MyLog.e("fail", "urlPathEnd=" + urlPath + "信息=" + e.getMessage());
                callback.onErrorD();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //此方法运行在子线程中，不能在此方法中进行UI操作。
                String result = response.body().string();
                MyLog.e("请求成功", "urlPathEnd=" + urlPath + "返回=" + result);

                String getToken = response.header("token");
                if (getToken != null && getToken.length() > 0) {
                    SharedPreferences.Editor editor = context.getSharedPreferences("tokens", MODE_PRIVATE).edit();
                    editor.putString("token", getToken);

                    MyLog.e("保存token=", "==" + getToken);
                    editor.commit();
                }


                JSONObject jsonResult = null;
                try {
                    jsonResult = new JSONObject(result);
                    callback.onSuccess(jsonResult);
                } catch (JSONException e) {
                    callback.onError("get中解析返回数据异常");
                }
                response.body().close();
            }

        });
    }

    //这个是把文件变成二进制流
    public static byte[] readStream(String imagepath) throws Exception {
        FileInputStream fs = new FileInputStream(imagepath);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while (-1 != (len = fs.read(buffer))) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        fs.close();
        return outStream.toByteArray();
    }

    //上传文件 支持上传一个和多个
    public synchronized static void uploadImage(final String url, List<String> picturePath, HashMap<String, String> paramsMap, final Context context, final HttpCallback callback) {
        if (url == null) {
            return;
        }
        if (picturePath == null) {
            return;
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
        //创建MultipartBody,给RequestBody进行设置
        MultipartBody.Builder multipartBody = new MultipartBody.Builder();
        multipartBody.setType(MultipartBody.FORM);
        //创建上传文件对象
        for (int i = 0; i < picturePath.size(); i++) {
            File file = new File(picturePath.get(i));
            //创建RequestBody封装参数
            RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
            multipartBody.addFormDataPart("file", file.getName(), fileBody);
        }
        if (paramsMap != null) {
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                String key = entry.getKey();
                String v = entry.getValue();
                if (key != null && v != null) {
                    multipartBody.addFormDataPart(key, v);//设置参数名称和参数值
                    MyLog.e("键值", "key=" + key + "-----value=" + v);
                }
            }
        }
        //放在添加数据之后
        MultipartBody build = multipartBody.build();
        Request.Builder builder = new Request.Builder();
        Request.Builder post = builder.post(build);
        Request.Builder url1 = post.url(url);

        SharedPreferences pref2 = context.getSharedPreferences("tokens", MODE_PRIVATE);
        String token = pref2.getString("token", "");
        String name = pref2.getString("name", "");
        if (token.length() > 0) {
            url1.addHeader("token", token);
            url1.addHeader("name",name);
            MyLog.e("传递token", "=" + token);
        }
        //创建Request
//        Request request = new Request.Builder()
//                .url(url)
//                .post(multipartBody)
//                .build();
        //创建okhttp对象

        Request request = url1.build();
        //上传完图片,得到服务器反馈数据
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyLog.e("fail", "onFailure=" + e.getCause());
                MyLog.e("fail", "urlPathEnd=" + url + "信息=" + e.getMessage());
                callback.onErrorD();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                MyLog.e("请求成功", "urlPathEnd=" + url + "返回=" + result);

                String getToken = response.header("token");
                if (getToken != null && getToken.length() > 0) {
                    SharedPreferences.Editor editor = context.getSharedPreferences("tokens", MODE_PRIVATE).edit();
                    editor.putString("token", getToken);
                    MyLog.e("保存token=", "==" + getToken);
                    editor.commit();
                }


                JSONObject jsonResult = null;
                try {
                    jsonResult = new JSONObject(result);
                    callback.onSuccess(jsonResult);
                } catch (JSONException e) {
                    callback.onError("get中解析返回数据异常");
                }
                response.body().close();
            }
        });
    }

    //下载图片
    public synchronized static void uploadImage(final String url, ArrayList<Photo> picturePath, HashMap<String, String> paramsMap, final Context context, final HttpCallback callback) {
        if (url == null) {
            return;
        }
        if (picturePath == null) {
            return;
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();

        MultipartBody.Builder multipartBody = new MultipartBody.Builder();
        multipartBody.setType(MultipartBody.FORM);
        for (int i = 0; i < picturePath.size(); i++) {
            File file = new File(picturePath.get(i).path);
            //创建RequestBody封装参数
            RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
            multipartBody.addFormDataPart("image", file.getName(), fileBody);
        }
        if (paramsMap != null) {
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                String key = entry.getKey();
                String v = entry.getValue();
                if (key != null && v != null) {
                    multipartBody.addFormDataPart(key, v);//设置参数名称和参数值
                    MyLog.e("键值", "key=" + key + "-----value=" + v);
                }
            }
        }
        //放在添加数据之后
        MultipartBody build = multipartBody.build();
        Request.Builder builder = new Request.Builder();
        Request.Builder post = builder.post(build);
        Request.Builder url1 = post.url(url);

        SharedPreferences pref2 = context.getSharedPreferences("tokens", MODE_PRIVATE);
        String token = pref2.getString("token", "");
        String name = pref2.getString("name", "");
        if (token.length() > 0) {
            url1.addHeader("token", token);
            url1.addHeader("name", name);
            MyLog.e("传递token", "=" + token);
        }

        Request request = url1.build();
        //上传完图片,得到服务器反馈数据
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyLog.e("fail", "onFailure=" + e.getCause());
                MyLog.e("fail", "urlPathEnd=" + url + "信息=" + e.getMessage());
                callback.onErrorD();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                MyLog.e("请求成功", "urlPathEnd=" + url + "返回=" + result);

                String getToken = response.header("token");
                if (getToken != null && getToken.length() > 0) {
                    SharedPreferences.Editor editor = context.getSharedPreferences("tokens", MODE_PRIVATE).edit();
                    editor.putString("token", getToken);
                    MyLog.e("保存token=", "==" + getToken);
                    editor.commit();
                }


                JSONObject jsonResult = null;
                try {
                    jsonResult = new JSONObject(result);
                    callback.onSuccess(jsonResult);
                } catch (JSONException e) {
                    callback.onError("get中解析返回数据异常");
                }
                response.body().close();
            }
        });
    }


    public synchronized static void uploadImagex(final String url, String picturePath, HashMap<String, String> paramsMap, final Context context, final HttpCallback callback) {
        if (url == null) {
            return;
        }
        if (picturePath == null) {
            return;
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
        //创建上传文件对象
        File file = new File(picturePath);
        //创建RequestBody封装参数
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        //创建MultipartBody,给RequestBody进行设置
        MultipartBody.Builder multipartBody = new MultipartBody.Builder();
        multipartBody.setType(MultipartBody.FORM);
        multipartBody.addFormDataPart("image", file.getName(), fileBody);
        if (paramsMap != null) {
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                String key = entry.getKey();
                String v = entry.getValue();
                if (key != null && v != null) {
                    multipartBody.addFormDataPart(key, v);//设置参数名称和参数值
                    MyLog.e("键值", "key=" + key + "-----value=" + v);
                }
            }
        }
        //放在添加数据之后
        MultipartBody build = multipartBody.build();
        Request.Builder builder = new Request.Builder();
        Request.Builder post = builder.post(build);
        Request.Builder url1 = post.url(url);

        SharedPreferences pref2 = context.getSharedPreferences("tokens", MODE_PRIVATE);
        String token = pref2.getString("token", "");
        String name = pref2.getString("name", "");
        if (token.length() > 0) {
            url1.addHeader("token", token);
            url1.addHeader("name", name);
            MyLog.e("传递token", "=" + token);
        }
        //创建Request
//        Request request = new Request.Builder()
//                .url(url)
//                .post(multipartBody)
//                .build();
        //创建okhttp对象

        Request request = url1.build();
        //上传完图片,得到服务器反馈数据
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyLog.e("fail", "onFailure=" + e.getCause());
                MyLog.e("fail", "urlPathEnd=" + url + "信息=" + e.getMessage());
                callback.onErrorD();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                MyLog.e("请求成功", "urlPathEnd=" + url + "返回=" + result);

                String getToken = response.header("token");
                if (getToken != null && getToken.length() > 0) {
                    SharedPreferences.Editor editor = context.getSharedPreferences("tokens", MODE_PRIVATE).edit();
                    editor.putString("token", getToken);
                    MyLog.e("保存token=", "==" + getToken);
                    editor.commit();
                }


                JSONObject jsonResult = null;
                try {
                    jsonResult = new JSONObject(result);
                    callback.onSuccess(jsonResult);
                } catch (JSONException e) {
                    callback.onError("get中解析返回数据异常");
                }
                response.body().close();
            }
        });
    }


    //上传单个图片
    public synchronized static void upload(final String url, String picturePath, HashMap<String, String> paramsMap, final Context context, final HttpCallback callback) {
        if (url == null) {
            return;
        }
        if (picturePath == null) {
            return;
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
        //创建上传文件对象
        File file = new File(picturePath);
        //创建RequestBody封装参数
        RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        //创建MultipartBody,给RequestBody进行设置
        MultipartBody.Builder multipartBody = new MultipartBody.Builder();
        multipartBody.setType(MultipartBody.FORM);
        multipartBody.addFormDataPart("image", file.getName(), fileBody);
        if (paramsMap != null) {
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                String key = entry.getKey();
                String v = entry.getValue();
                if (key != null && v != null) {
                    multipartBody.addFormDataPart(key, v);//设置参数名称和参数值
                    MyLog.e("键值", "key=" + key + "-----value=" + v);
                }
            }
        }
        //放在添加数据之后
        MultipartBody build = multipartBody.build();
        Request.Builder builder = new Request.Builder();
        Request.Builder post = builder.post(build);
        Request.Builder url1 = post.url(url);

        SharedPreferences pref2 = context.getSharedPreferences("tokens", MODE_PRIVATE);
        String token = pref2.getString("token", "");
        String name = pref2.getString("name", "");
        if (token.length() > 0) {
            url1.addHeader("token", token);
            url1.addHeader("name",name);
            MyLog.e("传递token", "=" + token);
        }
        //创建Request
//        Request request = new Request.Builder()
//                .url(url)
//                .post(multipartBody)
//                .build();
        //创建okhttp对象

        Request request = url1.build();
        //上传完图片,得到服务器反馈数据
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyLog.e("fail", "onFailure=" + e.getCause());
                MyLog.e("fail", "urlPathEnd=" + url + "信息=" + e.getMessage());
                callback.onErrorD();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                MyLog.e("请求成功", "urlPathEnd=" + url + "返回=" + result);

                String getToken = response.header("token");
                String name1 = response.header("name");
                if (getToken != null && getToken.length() > 0) {
                    SharedPreferences.Editor editor = context.getSharedPreferences("tokens", MODE_PRIVATE).edit();
                    editor.putString("token", getToken);
                    editor.putString("name",name1);
                    MyLog.e("保存token=", "==" + getToken);
                    editor.commit();
                }


                JSONObject jsonResult = null;
                try {
                    jsonResult = new JSONObject(result);
                    callback.onSuccess(jsonResult);
                } catch (JSONException e) {
                    callback.onError("get中解析返回数据异常");
                }
                response.body().close();
            }
        });
    }


    public synchronized static void sendPut(final String urlPath, HashMap<String, String> paramsMap, final Context context, final HttpCallback callback) {
        MyLog.e("Post请求链接", "=" + urlPath);
        if (!urlPath.contains(".")) {
            return;
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        //Form表单格式的参数传递
        FormBody.Builder builder = new FormBody.Builder();
        if (paramsMap != null) {
            for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
                String key = entry.getKey();
                String v = entry.getValue();
                if (key != null && v != null) {
                    builder.add(key, v);//设置参数名称和参数值
                    MyLog.e("键值", "key=" + key + "-----value=" + v);
                }
            }
        }
        FormBody formBody = builder.build();
        Request.Builder reqBuild = new Request.Builder();
        Request.Builder post = reqBuild.put(formBody);
        Request.Builder url = post.url(urlPath);
        Request request = url.build();
        MyLog.e("开始请求", "开始url=" + urlPath);
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MyLog.e("fail", "onFailure=" + e.getCause());
                MyLog.e("fail", "urlPath=" + urlPath + "信息=" + e.getMessage());
                callback.onErrorD();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //此方法运行在子线程中，不能在此方法中进行UI操作。
                String result = response.body().string();
                MyLog.e("请求成功", "urlPath=" + urlPath + "返回=" + result);
                JSONObject jsonResult = null;
                MyLog.e("onSuccess=", "=" + "==callback.onSuccess=" + jsonResult);
                try {
                    jsonResult = new JSONObject(result);
                    callback.onSuccess(jsonResult);
                } catch (JSONException e) {
                    callback.onError(catchError);
                }
                response.body().close();
            }
        });
    }

    /**
     * http请求回调
     */
    public static abstract class HttpCallback {
        // 成功回调
        public abstract void onSuccess(JSONObject res);

        public void onSuccessString(String request) {
        }

        // 失败回调
        public void onError(String msg) {
        }

        // 失败回调
        public void onErrorD() {
        }

        public void onBoolean(Boolean b) {
        }
    }

    /**
     * http请求回调
     */
    public static abstract class downloadCallback {
        // 成功回调

        public abstract void onSuccess(String res);

        // 失败回调
        public void onError(String msg) {
        }

        // 失败回调
        public void onErrorD() {
        }

    }
}
