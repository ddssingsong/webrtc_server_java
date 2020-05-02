package com.example.demo;


import com.example.demo.bean.RoomInfo;
import com.example.demo.bean.UserBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@RestController
public class UserControl {

    @RequestMapping("/")
    public String index() {
        return "welcome to my webRTC demo";
    }


    @RequestMapping("/roomList")
    public List<RoomInfo> roomList() {
        ConcurrentHashMap<String, RoomInfo> rooms = MemCons.rooms;
        Collection<RoomInfo> values = rooms.values();
        ArrayList<RoomInfo> objects = new ArrayList<>();
        values.forEach(roomInfo -> {
            if (roomInfo.getMaxSize() > 2) {
                objects.add(roomInfo);
            }
        });
        return objects;
    }

    @RequestMapping("/userList")
    public List<UserBean> userList() {
        ConcurrentHashMap<String, UserBean> userBeans = MemCons.userBeans;
        Collection<UserBean> values = userBeans.values();
        return new ArrayList<>(values);
    }

}
