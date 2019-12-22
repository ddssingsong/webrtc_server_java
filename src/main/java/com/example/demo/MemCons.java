package com.example.demo;

import com.example.demo.bean.RoomInfo;
import com.example.demo.bean.UserBean;

import java.util.concurrent.ConcurrentHashMap;

public class MemCons {


    // 在线用户表
    public static ConcurrentHashMap<String, UserBean> userBeans = new ConcurrentHashMap<>();

    // 在线房间表
    public static ConcurrentHashMap<String, RoomInfo> rooms = new ConcurrentHashMap<>();

}
