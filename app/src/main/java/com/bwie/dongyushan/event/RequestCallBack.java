package com.bwie.dongyushan.event;

import java.io.IOException;

import okhttp3.Call;

public interface RequestCallBack {
    void success(Call call,String s);
    void failure(Call call, IOException e);
}
