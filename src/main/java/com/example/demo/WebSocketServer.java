package com.example.demo;


import com.example.demo.bean.EventData;
import com.example.demo.bean.UserBean;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@ServerEndpoint("/ws/{userId}") // 客户端URI访问的路径
@Component
public class WebSocketServer {


    // 在线房间表
    private static ConcurrentHashMap<String, ConcurrentHashMap<String, UserBean>> rooms = new ConcurrentHashMap<>();

    // 在线用户表
    private static ConcurrentHashMap<String, UserBean> userBeans = new ConcurrentHashMap<>();


    private UserBean userBean;
    private static Gson gson = new Gson();
    private static String avatar = "http://img.xinxic.com/img/abec871cbeac880b.jpg";


    // 用户userId登录进来
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        UserBean userBean = new UserBean(session, userId, avatar);
        this.userBean = userBean;
        // 添加socket
        if (userBeans.get(userId) == null) {
            userBeans.put(userId, userBean);
            System.out.println(session.getId() + ":用户登录:userId" + userId);
        }
        EventData send = new EventData();
        send.setEventName("_login_success");
        Map<String, Object> map = new HashMap<>();
        map.put("userID", userId);
        map.put("avatar", avatar);
        send.setData(map);
        session.getAsyncRemote().sendText(gson.toJson(send));

    }

    // 用户下线
    @OnClose
    public void onClose() {
        // 根据用户名查出房间,
        userBeans.remove(this.userBean.getUserId());
        System.out.println(userBean.getSession().getId() + ":用户离开:" + userBean.getUserId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("receive data:" + message);
        handleMessage(message, session);


    }


    // 发送各种消息
    private void handleMessage(String message, Session session) {
        EventData data;
        try {
            data = gson.fromJson(message, EventData.class);
        } catch (JsonSyntaxException e) {
            System.out.println("json解析错误：" + message);
            return;
        }
        switch (data.getEventName()) {
            case "__invite":
                invite(message, data.getData(), session);
                break;
            case "__join":
                join(data.getData(), session);
                break;
            case "__ice_candidate":
                iceCandidate(data.getData(), session);
                break;
            case "__offer":
                offer(data.getData(), session);
                break;
            case "__answer":
                answer(data.getData(), session);
                break;
            default:
                break;
        }

    }

    private void invite(String message, Map<String, Object> data, Session session) {
        String userList = (String) data.get("userList");
        String[] users = userList.split(",");
        for (String user : users) {
            // 根据userId获取sessionId
            UserBean userBean = userBeans.get(user);
            // 给需要发送的人发送消息
            userBean.getSession().getAsyncRemote().sendText(message);
        }

    }


    private void join(Map<String, Object> data, Session socket) {
        //获得房间号
//        String room = data.get("room") == null ? "__default" : data.get("room").toString();
//        CopyOnWriteArrayList<String> ids = new CopyOnWriteArrayList<>();//存储sessionId
//        CopyOnWriteArrayList<Session> curRoom = rooms.get(room);//获取对应房间的列表
//        if (curRoom == null) {
//            curRoom = new CopyOnWriteArrayList<>();
//        }
//        Session curSocket;
//        //当前房间是否有加入的socket
//        for (int i = 0; i < curRoom.size(); i++) {
//            curSocket = curRoom.get(i);
//            if (socket.getId().equals(curSocket.getId())) {
//                continue;
//            }
//            ids.add(curSocket.getId());
//            EventData send = new EventData();
//            send.setEventName("_new_peer");
//            Map<String, Object> map = new HashMap<>();
//            map.put("socketId", socket.getId());
//            send.setData(map);
//            curSocket.getAsyncRemote().sendText(gson.toJson(send));
//        }
//
//        curRoom.add(socket);
//        roomList.put(session.getId(), room);
//        rooms.put(room, curRoom);
//
//        EventData send = new EventData();
//        send.setEventName("_peers");
//        Map<String, Object> map = new HashMap<>();
//        String[] connections = new String[ids.size()];
//        ids.toArray(connections);
//        map.put("connections", connections);
//        map.put("you", socket.getId());
//        send.setData(map);
//        socket.getAsyncRemote().sendText(gson.toJson(send));
//
//        System.out.println("新用户" + socket.getId() + "加入房间" + room);
    }

    private void iceCandidate(Map<String, Object> data, Session socket) {
//        //soc=this
//        Session session = getSocket(data.get("socketId").toString());
//        if (session == null) {
//            return;
//        }
//        EventData send = new EventData();
//        send.setEventName("_ice_candidate");
//        data.put("id", data.get("id"));
//        data.put("label", data.get("label"));
//        data.put("candidate", data.get("candidate"));
//        data.put("socketId", socket.getId());
//        send.setData(data);
//        session.getAsyncRemote().sendText(gson.toJson(send));
//
//        System.out.println("接收到来自" + socket.getId() + "的ICE Candidate");
    }

    private void offer(Map<String, Object> data, Session socket) {
//        Session session = getSocket(data.get("socketId").toString());
//        if (session == null) {
//            return;
//        }
//        EventData send = new EventData();
//        send.setEventName("_offer");
//
//        Map<String, Object> map = data;
//        map.put("sdp", data.get("sdp"));
//        map.put("socketId", socket.getId());
//        send.setData(map);
//        session.getAsyncRemote().sendText(gson.toJson(send));
//
//        System.out.println("接收到来自" + socket.getId() + "的Offer");

    }

    private void answer(Map<String, Object> data, Session socket) {
//        Session session = getSocket(data.get("socketId").toString());
//        if (session == null) {
//            return;
//        }
//        EventData send = new EventData();
//        send.setEventName("_answer");
//        data.put("sdp", data.get("sdp"));
//        data.put("socketId", socket.getId());
//        send.setData(data);
//        session.getAsyncRemote().sendText(gson.toJson(send));
//
//        System.out.println("接收到来自" + socket.getId() + "的Answer");

    }


}