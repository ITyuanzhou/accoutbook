package com.zy.model;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Transient;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by ZJZL_HP on 2017/8/16.
 */
public class TbAaBillingEntity {
    private String aaBilId;
    private int aaBilPersonNumber;
    private Double aaBilTotalAmount;
    private Timestamp aaBilTime;
    private String aaBilRemark;

    private TbUserEntity aaBilUser;
    private TbAaBillingTypeEntity aaBilBillingType;
    private Set<TbDetailAaBillingEntity> detailAaBillingEntitySet;

    @Transient
    private Set<TbUserEntity> aaBilBearUserSet;

    public String getAaBilId() {
        return aaBilId;
    }

    public void setAaBilId(String aaBilId) {
        this.aaBilId = aaBilId;
    }

    public int getAaBilPersonNumber() {
        return aaBilPersonNumber;
    }

    public void setAaBilPersonNumber(int aaBilPersonNumber) {
        this.aaBilPersonNumber = aaBilPersonNumber;
    }

    public Double getAaBilTotalAmount() {
        return aaBilTotalAmount;
    }

    public void setAaBilTotalAmount(Double aaBilTotalAmount) {
        this.aaBilTotalAmount = aaBilTotalAmount;
    }

    public Timestamp getAaBilTime() {
        return aaBilTime;
    }

    public void setAaBilTime(Timestamp aaBilTime) {
        this.aaBilTime = aaBilTime;
    }

    public String getAaBilRemark() {
        return aaBilRemark;
    }

    public void setAaBilRemark(String aaBilRemark) {
        this.aaBilRemark = aaBilRemark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbAaBillingEntity that = (TbAaBillingEntity) o;

        if (aaBilId != null ? !aaBilId.equals(that.aaBilId) : that.aaBilId != null) return false;
        if (aaBilTotalAmount != null ? !aaBilTotalAmount.equals(that.aaBilTotalAmount) : that.aaBilTotalAmount != null)
            return false;
        if (aaBilTime != null ? !aaBilTime.equals(that.aaBilTime) : that.aaBilTime != null) return false;
        if (aaBilRemark != null ? !aaBilRemark.equals(that.aaBilRemark) : that.aaBilRemark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = aaBilId != null ? aaBilId.hashCode() : 0;
        result = 31 * result + (aaBilTotalAmount != null ? aaBilTotalAmount.hashCode() : 0);
        result = 31 * result + (aaBilTime != null ? aaBilTime.hashCode() : 0);
        result = 31 * result + (aaBilRemark != null ? aaBilRemark.hashCode() : 0);
        return result;
    }

    public TbUserEntity getAaBilUser() {
        return aaBilUser;
    }

    public void setAaBilUser(TbUserEntity aaBilUser) {
        this.aaBilUser = aaBilUser;
    }

    public TbAaBillingTypeEntity getAaBilBillingType() {
        return aaBilBillingType;
    }

    public void setAaBilBillingType(TbAaBillingTypeEntity aaBilBillingType) {
        this.aaBilBillingType = aaBilBillingType;
    }

    @JSONField(serialize=false)
    public Set<TbDetailAaBillingEntity> getDetailAaBillingEntitySet() {
        return detailAaBillingEntitySet;
    }

    public void setDetailAaBillingEntitySet(Set<TbDetailAaBillingEntity> detailAaBillingEntitySet) {
        this.detailAaBillingEntitySet = detailAaBillingEntitySet;
    }

    public Set<TbUserEntity> getAaBilBearUserSet() {
        return aaBilBearUserSet;
    }

    public void setAaBilBearUserSet(Set<TbUserEntity> aaBilBearUserSet) {
        this.aaBilBearUserSet = aaBilBearUserSet;
    }
}
