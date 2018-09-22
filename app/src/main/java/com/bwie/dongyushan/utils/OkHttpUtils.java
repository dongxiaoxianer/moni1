package com.bwie.dongyushan.utils;

import com.bwie.dongyushan.event.RequestCallBack;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtils {
    private static OkHttpUtils okHttpUtils;
    private OkHttpClient okHttpClient;

    private OkHttpUtils(){
        okHttpClient=new OkHttpClient.Builder()
                .writeTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpUtils getInstance(){
        if (okHttpUtils==null){
            synchronized (OkHttpUtils.class){
                if (okHttpUtils==null){
                    okHttpUtils=new OkHttpUtils();
                }
}
        }
        return okHttpUtils;
    }

    public void postData(String url, HashMap<String,String> prams, final RequestCallBack requestCallBack){
        FormBody.Builder formBodyBuilder=new FormBody.Builder();
        if (prams!=null&&prams.size()>0){
            for (Map.Entry<String,String> stringStringEntry:prams.entrySet()){
                formBodyBuilder.add(stringStringEntry.getKey(),stringStringEntry.getValue());
            }
        }
        final Request request=new Request.Builder()
                .url(url).post(formBodyBuilder.build()).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (requestCallBack!=null){
                    requestCallBack.failure(call,e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (requestCallBack!=null){
                    String string=response.body().string();
                    requestCallBack.success(call,string);

                }
            }
        });
    }
}
