package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.websocket.Session;

public class UserBean {

    @JsonFormat
    private String userId;
    @JsonFormat
    private String avatar;
    @JsonIgnore
    private DeviceSession[] sessions = new DeviceSession[2];

    public UserBean(String userId, String avatar) {
        this.userId = userId;
        this.avatar = avatar;
    }

    @JsonIgnore
    public void setPhoneSession(Session session, int device) {
        this.sessions[0] = new DeviceSession(session, device);
    }

    @JsonIgnore
    public void setPcSession(Session session, int device) {
        this.sessions[1] = new DeviceSession(session, device);
    }

    @JsonIgnore
    public Session getPhoneSession() {
        if (sessions[0] == null) return null;
        return sessions[0].getSession();
    }

    @JsonIgnore
    public Session getPcSession() {
        if (sessions[1] == null) return null;
        return sessions[1].getSession();
    }

    @JsonIgnore
    public String getUserId() {
        return userId;
    }

    @JsonIgnore
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonIgnore
    public String getAvatar() {
        return avatar;
    }

    @JsonIgnore
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

    @JsonIgnore
    public DeviceSession[] getSessions() {
        return sessions;
    }

    @JsonIgnore
    public void setSessions(DeviceSession[] sessions) {
        this.sessions = sessions;
    }
}
