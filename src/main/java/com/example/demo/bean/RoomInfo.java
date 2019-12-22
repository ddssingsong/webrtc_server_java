package com.example.demo.bean;

import java.util.concurrent.CopyOnWriteArrayList;

public class RoomInfo {
    // 房间里的人
    private CopyOnWriteArrayList<UserBean> userBeans;
    // 房间大小
    private int maxSize;

    public RoomInfo() {
    }

    public RoomInfo(CopyOnWriteArrayList<UserBean> userBeans, int maxSize, int mediaType) {
        this.userBeans = userBeans;
        this.maxSize = maxSize;
    }

    public CopyOnWriteArrayList<UserBean> getUserBeans() {
        return userBeans;
    }

    public void setUserBeans(CopyOnWriteArrayList<UserBean> userBeans) {
        this.userBeans = userBeans;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }


}
