package com.bwie.dongyushan.view;

import com.bwie.dongyushan.bean.UserBean;

public interface MyView {
    void success(UserBean userBean);
    void failure(String msg);
}
