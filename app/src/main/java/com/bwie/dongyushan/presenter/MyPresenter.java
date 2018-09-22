package com.bwie.dongyushan.presenter;



import com.bwie.dongyushan.bean.UserBean;
import com.bwie.dongyushan.event.RequestCallBack;
import com.bwie.dongyushan.model.MyModel;
import com.bwie.dongyushan.view.MyView;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;

public class MyPresenter {
    private MyView myView;
    private MyModel myModel;

    public MyPresenter(MyView myView) {
        this.myView = myView;
        myModel=new MyModel();
    }
    public void show(){
        myModel.show(new RequestCallBack() {
            @Override
            public void success(Call call, String s) {
                Gson gson=new Gson();
                UserBean userBean=gson.fromJson(s,UserBean.class);
                myView.success(userBean);
            }

            @Override
            public void failure(Call call, IOException e) {
                myView.failure("失败");
            }
        });
    }
}
