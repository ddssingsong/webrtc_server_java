package com.example.demo.bean;

import java.util.concurrent.CopyOnWriteArrayList;

public class RoomInfo {
    private CopyOnWriteArrayList<UserBean> userBeans;
    private int maxSize;
    private int mediaType;

    public RoomInfo() {
    }

    public RoomInfo(CopyOnWriteArrayList<UserBean> userBeans, int maxSize, int mediaType) {
        this.userBeans = userBeans;
        this.maxSize = maxSize;
        this.mediaType = mediaType;
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

    public int getMediaType() {
        return mediaType;
    }

    public void setMediaType(int mediaType) {
        this.mediaType = mediaType;
    }
}
