package com.example.demo;


import com.example.demo.bean.Data;
import com.google.gson.Gson;
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

    private static ConcurrentHashMap<String, CopyOnWriteArrayList<Session>> rooms = new ConcurrentHashMap<>();

    private static ConcurrentHashMap<String, String> roomList = new ConcurrentHashMap<>();

    private static CopyOnWriteArrayList<Session> sockets = new CopyOnWriteArrayList<>();

    private Session session;

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;

        System.out.println(session.getId() + ":建立连接:" + userId);
        // 添加socket
        addSocket(session);
    }

    @OnClose
    public void onClose() {
        Session session = this.session;
        String room = roomList.get(session.getId());
        CopyOnWriteArrayList<Session> curRoom;
        if (room != null) {
            curRoom = rooms.get(room);//获取对应房间的列表
            for (Session aCurRoom : curRoom) {
                if (aCurRoom.getId().equals(session.getId())) {
                    continue;
                }
                Data send = new Data();
                send.setEventName("_remove_peer");
                Map<String, Object> map = new HashMap<>();
                map.put("socketId", session.getId());
                send.setData(map);
                aCurRoom.getAsyncRemote().sendText(new Gson().toJson(send));
            }

        }
        removeSocket(session);
        System.out.println(session.getId() + "用户离开");
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        if (message.contains("eventName")) {
            handleMessage(message, session);
        } else {
            System.out.println("接收到来自" + session.getId() + "的新消息：" + message);
        }

    }

    public void sendMessage(String message) {
        this.session.getAsyncRemote().sendText(message);
    }


    private void handleMessage(String message, Session session) {
        Gson gson = new Gson();
        Data data = gson.fromJson(message, Data.class);
        switch (data.getEventName()) {
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
//        // 发起邀请
//        this.on('__invite', function (data) {
//
//        });
//        // 回应数据
//        this.on('__ack', function (data) {
//
//        });
    }

    private void removeSocket(Session session) {

        String room = roomList.get(session.getId());
        //删除session
        sockets.remove(session);
        //从房间列表删除
        rooms.get(room).remove(session);
        //从sessionid 对应房间的列表删除
        roomList.remove(session.getId());

    }

    private void addSocket(Session session) {
        if (sockets.indexOf(session) == -1) {
            sockets.add(session);
        }
    }

    private Session getSocket(String socketId) {
        if (sockets.size() > 0) {
            for (int i = 0; i < sockets.size(); i++) {
                if (socketId.equals(sockets.get(i).getId())) {
                    return sockets.get(i);
                }
            }
        }
        return null;
    }


    private void join(Map<String, Object> data, Session socket) {
        //获得房间号
        String room = data.get("room") == null ? "__default" : data.get("room").toString();
        CopyOnWriteArrayList<String> ids = new CopyOnWriteArrayList<>();//存储sessionId
        CopyOnWriteArrayList<Session> curRoom = rooms.get(room);//获取对应房间的列表
        if (curRoom == null) {
            curRoom = new CopyOnWriteArrayList<>();
        }
        Session curSocket;
        //当前房间是否有加入的socket
        for (int i = 0; i < curRoom.size(); i++) {
            curSocket = curRoom.get(i);
            if (socket.getId().equals(curSocket.getId())) {
                continue;
            }
            ids.add(curSocket.getId());
            Data send = new Data();
            send.setEventName("_new_peer");
            Map<String, Object> map = new HashMap<>();
            map.put("socketId", socket.getId());
            send.setData(map);
            curSocket.getAsyncRemote().sendText(new Gson().toJson(send));
        }

        curRoom.add(socket);
        roomList.put(session.getId(), room);
        rooms.put(room, curRoom);

        Data send = new Data();
        send.setEventName("_peers");
        Map<String, Object> map = new HashMap<>();
        String[] connections = new String[ids.size()];
        ids.toArray(connections);
        map.put("connections", connections);
        map.put("you", socket.getId());
        send.setData(map);
        socket.getAsyncRemote().sendText(new Gson().toJson(send));

        System.out.println("新用户" + socket.getId() + "加入房间" + room);
    }

    private void iceCandidate(Map<String, Object> data, Session socket) {
        //soc=this
        Session session = getSocket(data.get("socketId").toString());
        if (session == null) {
            return;
        }

        Data send = new Data();
        send.setEventName("_ice_candidate");
        Map<String, Object> map = data;
        map.put("id", data.get("id"));
        map.put("label", data.get("label"));
        map.put("candidate", data.get("candidate"));
        map.put("socketId", socket.getId());
        send.setData(map);
        session.getAsyncRemote().sendText(new Gson().toJson(send));

        System.out.println("接收到来自" + socket.getId() + "的ICE Candidate");
    }

    private void offer(Map<String, Object> data, Session socket) {
        Session session = getSocket(data.get("socketId").toString());
        if (session == null) {
            return;
        }
        Data send = new Data();
        send.setEventName("_offer");

        Map<String, Object> map = data;
        map.put("sdp", data.get("sdp"));
        map.put("socketId", socket.getId());
        send.setData(map);
        session.getAsyncRemote().sendText(new Gson().toJson(send));

        System.out.println("接收到来自" + socket.getId() + "的Offer");

    }

    private void answer(Map<String, Object> data, Session socket) {
        Session session = getSocket(data.get("socketId").toString());
        if (session == null) {
            return;
        }

        Data send = new Data();
        send.setEventName("_answer");

        Map<String, Object> map = data;
        map.put("sdp", data.get("sdp"));
        map.put("socketId", socket.getId());
        send.setData(map);
        session.getAsyncRemote().sendText(new Gson().toJson(send));

        System.out.println("接收到来自" + socket.getId() + "的Answer");

    }


}