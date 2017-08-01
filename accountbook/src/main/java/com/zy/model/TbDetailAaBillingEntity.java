package com.zy.model;

/**
 * Created by ZJZL_HP on 2017/7/19.
 */
public class TbDetailAaBillingEntity {
    private String did;
    private Double damount;
    private int dchargeState;
    private TbBillingEntity dbilling;
    private TbUserEntity duser;

    public TbBillingEntity getDbilling() {
        return dbilling;
    }

    public void setDbilling(TbBillingEntity dbilling) {
        this.dbilling = dbilling;
    }

    public TbUserEntity getDuser() {
        return duser;
    }

    public void setDuser(TbUserEntity duser) {
        this.duser = duser;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public Double getDamount() {
        return damount;
    }

    public void setDamount(Double damount) {
        this.damount = damount;
    }

    public int getDchargeState() {
        return dchargeState;
    }

    public void setDchargeState(int dchargeState) {
        this.dchargeState = dchargeState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbDetailAaBillingEntity that = (TbDetailAaBillingEntity) o;

        if (dchargeState != that.dchargeState) return false;
        if (did != null ? !did.equals(that.did) : that.did != null) return false;
        if (damount != null ? !damount.equals(that.damount) : that.damount != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = did != null ? did.hashCode() : 0;
        result = 31 * result + (damount != null ? damount.hashCode() : 0);
        result = 31 * result + dchargeState;
        return result;
    }
}
