package com.example.demo.bean;

import javax.websocket.Session;

public class UserBean {
    private Session session;
    private String userId;
    private String avatar;

    public UserBean(Session session, String userId, String avatar) {
        this.session = session;
        this.userId = userId;
        this.avatar = avatar;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
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
        return this.userId.equals(user.getUserId()) && this.session.getId().equals(user.getSession().getId());
    }
}
