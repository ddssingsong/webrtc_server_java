package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.websocket.Session;

public class UserBean {

    @JsonFormat
    private String userId;
    @JsonFormat
    private String avatar;

    private boolean isPhone;

    private DeviceSession pcSession;
    private DeviceSession phoneSession;


    public UserBean(String userId, String avatar) {
        this.userId = userId;
        this.avatar = avatar;
    }

    @JsonIgnore
    public void setPhoneSession(Session session, int device) {
        if (session == null) {
            this.phoneSession = null;
            return;
        }
        this.phoneSession = new DeviceSession(session, device);
    }

    @JsonIgnore
    public void setPcSession(Session session, int device) {
        if (session == null) {
            this.pcSession = null;
            return;
        }
        this.pcSession = new DeviceSession(session, device);
    }

    @JsonIgnore
    public Session getPhoneSession() {
        return phoneSession == null ? null : phoneSession.getSession();
    }

    @JsonIgnore
    public Session getPcSession() {
        return pcSession == null ? null : pcSession.getSession();
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

    public boolean isPhone() {
        return isPhone;
    }

    public void setPhone(boolean phone) {
        isPhone = phone;
    }
}
