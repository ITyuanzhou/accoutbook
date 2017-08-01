package com.zy.model;

import java.sql.Timestamp;

/**
 * Created by ZJZL_HP on 2017/7/19.
 */
public class TbUserEntity {
    private String uid;
    private String upwd;
    private String uname;
    private Double upersonBalance;
    private Double uaaBalance;
    private Timestamp ulastLoginTime;
    private TbAttachEntity uattach;

    public TbAttachEntity getUattach() {
        return uattach;
    }

    public void setUattach(TbAttachEntity uattach) {
        this.uattach = uattach;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Double getUpersonBalance() {
        return upersonBalance;
    }

    public void setUpersonBalance(Double upersonBalance) {
        this.upersonBalance = upersonBalance;
    }

    public Double getUaaBalance() {
        return uaaBalance;
    }

    public void setUaaBalance(Double uaaBalance) {
        this.uaaBalance = uaaBalance;
    }

    public Timestamp getUlastLoginTime() {
        return ulastLoginTime;
    }

    public void setUlastLoginTime(Timestamp ulastLoginTime) {
        this.ulastLoginTime = ulastLoginTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbUserEntity that = (TbUserEntity) o;

        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (upwd != null ? !upwd.equals(that.upwd) : that.upwd != null) return false;
        if (uname != null ? !uname.equals(that.uname) : that.uname != null) return false;
        if (upersonBalance != null ? !upersonBalance.equals(that.upersonBalance) : that.upersonBalance != null)
            return false;
        if (uaaBalance != null ? !uaaBalance.equals(that.uaaBalance) : that.uaaBalance != null) return false;
        if (ulastLoginTime != null ? !ulastLoginTime.equals(that.ulastLoginTime) : that.ulastLoginTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uid != null ? uid.hashCode() : 0;
        result = 31 * result + (upwd != null ? upwd.hashCode() : 0);
        result = 31 * result + (uname != null ? uname.hashCode() : 0);
        result = 31 * result + (upersonBalance != null ? upersonBalance.hashCode() : 0);
        result = 31 * result + (uaaBalance != null ? uaaBalance.hashCode() : 0);
        result = 31 * result + (ulastLoginTime != null ? ulastLoginTime.hashCode() : 0);
        return result;
    }
}
