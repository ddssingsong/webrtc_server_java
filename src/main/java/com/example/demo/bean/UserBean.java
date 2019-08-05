package com.example.demo.bean;

import javax.websocket.Session;

public class UserBean {
    private DeviceSession[] sessions = new DeviceSession[2];
    private String userId;
    private String avatar;

    public UserBean(String userId, String avatar) {
        this.userId = userId;
        this.avatar = avatar;
    }

    public void setPhoneSession(Session session, int device) {
        this.sessions[0] = new DeviceSession(session, device);
    }

    public void setPcSession(Session session, int device) {
        this.sessions[1] = new DeviceSession(session, device);
    }

    public Session getPhoneSession() {
        return sessions[0].getSession();
    }

    public Session getPcSession() {
        return sessions[1].getSession();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (null == obj) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        UserBean user = (UserBean) obj;
        return this.userId.equals(user.getUserId());
    }


    public DeviceSession[] getSessions() {
        return sessions;
    }

    public void setSessions(DeviceSession[] sessions) {
        this.sessions = sessions;
    }
}
