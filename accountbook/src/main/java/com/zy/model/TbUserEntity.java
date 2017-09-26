package com.zy.model;

import java.sql.Timestamp;

/**
 * Created by ZJZL_HP on 2017/7/19.
 */
public class TbUserEntity {
    private String userId;
    private String userPwd;
    private String userName;
    private Double userPersonBalance;
    private Double userAaBalance;
    private Timestamp userLastLoginTime;
    private TbAttachEntity userAttach;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getUserPersonBalance() {
        return userPersonBalance;
    }

    public void setUserPersonBalance(Double userPersonBalance) {
        this.userPersonBalance = userPersonBalance;
    }

    public Double getUserAaBalance() {
        return userAaBalance;
    }

    public void setUserAaBalance(Double userAaBalance) {
        this.userAaBalance = userAaBalance;
    }

    public Timestamp getUserLastLoginTime() {
        return userLastLoginTime;
    }

    public void setUserLastLoginTime(Timestamp userLastLoginTime) {
        this.userLastLoginTime = userLastLoginTime;
    }

    public TbAttachEntity getUserAttach() {
        return userAttach;
    }

    public void setUserAttach(TbAttachEntity userAttach) {
        this.userAttach = userAttach;
    }
}
