package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.concurrent.CopyOnWriteArrayList;

public class RoomInfo {
    // roomId
    @JsonFormat
    private String roomId;
    // 创建人Id
    @JsonFormat
    private String userId;
    // 房间里的人
    @JsonIgnore
    private CopyOnWriteArrayList<UserBean> userBeans = new CopyOnWriteArrayList<>();
    // 房间大小
    @JsonFormat
    private int maxSize;
    // 现有人数
    @JsonFormat
    private int currentSize;

    @JsonIgnore
    public RoomInfo() {
    }


    @JsonIgnore
    public CopyOnWriteArrayList<UserBean> getUserBeans() {
        return userBeans;
    }

    @JsonIgnore
    public void setUserBeans(CopyOnWriteArrayList<UserBean> userBeans) {
        this.userBeans = userBeans;
        setCurrentSize(this.userBeans.size());
    }

    @JsonIgnore
    public int getMaxSize() {
        return maxSize;
    }

    @JsonIgnore
    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @JsonIgnore
    public String getRoomId() {
        return roomId;
    }

    @JsonIgnore
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @JsonIgnore
    public String getUserId() {
        return userId;
    }

    @JsonIgnore
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCurrentSize() {
        return currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }
}
