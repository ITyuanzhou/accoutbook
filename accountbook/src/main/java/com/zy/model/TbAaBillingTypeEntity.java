package com.zy.model;

import java.sql.Timestamp;

/**
 * Created by ZJZL_HP on 2017/8/16.
 */
public class TbAaBillingTypeEntity {
    private String aaBilTypeId;
    private String aaBilTypeName;
    private Timestamp aaBilTypeCreTime;
    private int aaBilTypeIsValid;

    private TbUserEntity aaBilTypeCreUser;
    private TbAttachEntity aaBilTypeIconAttach;

    public String getAaBilTypeId() {
        return aaBilTypeId;
    }

    public void setAaBilTypeId(String aaBilTypeId) {
        this.aaBilTypeId = aaBilTypeId;
    }

    public String getAaBilTypeName() {
        return aaBilTypeName;
    }

    public void setAaBilTypeName(String aaBilTypeName) {
        this.aaBilTypeName = aaBilTypeName;
    }

    public Timestamp getAaBilTypeCreTime() {
        return aaBilTypeCreTime;
    }

    public void setAaBilTypeCreTime(Timestamp aaBilTypeCreTime) {
        this.aaBilTypeCreTime = aaBilTypeCreTime;
    }

    public int getAaBilTypeIsValid() {
        return aaBilTypeIsValid;
    }

    public void setAaBilTypeIsValid(int aaBilTypeIsValid) {
        this.aaBilTypeIsValid = aaBilTypeIsValid;
    }

    public TbUserEntity getAaBilTypeCreUser() {
        return aaBilTypeCreUser;
    }

    public void setAaBilTypeCreUser(TbUserEntity aaBilTypeCreUser) {
        this.aaBilTypeCreUser = aaBilTypeCreUser;
    }

    public TbAttachEntity getAaBilTypeIconAttach() {
        return aaBilTypeIconAttach;
    }

    public void setAaBilTypeIconAttach(TbAttachEntity aaBilTypeIconAttach) {
        this.aaBilTypeIconAttach = aaBilTypeIconAttach;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbAaBillingTypeEntity that = (TbAaBillingTypeEntity) o;

        if (aaBilTypeId != null ? !aaBilTypeId.equals(that.aaBilTypeId) : that.aaBilTypeId != null) return false;
        if (aaBilTypeName != null ? !aaBilTypeName.equals(that.aaBilTypeName) : that.aaBilTypeName != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = aaBilTypeId != null ? aaBilTypeId.hashCode() : 0;
        result = 31 * result + (aaBilTypeName != null ? aaBilTypeName.hashCode() : 0);
        return result;
    }
}
