package com.bwie.dongyushan.model;

import com.bwie.dongyushan.event.RequestCallBack;
import com.bwie.dongyushan.utils.OkHttpUtils;

import java.util.HashMap;

public class MyModel {
    private HashMap<String,String> parms;
    public void show(RequestCallBack requestCallBack){
        parms=new HashMap<>();
        parms.put("uid","71");
        OkHttpUtils.getInstance().postData("https://www.zhaoapi.cn/product/getCarts",parms,requestCallBack);
    }
}
