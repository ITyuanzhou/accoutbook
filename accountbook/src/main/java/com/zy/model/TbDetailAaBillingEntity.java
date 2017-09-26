package com.zy.model;

/**
 * Created by ZJZL_HP on 2017/8/16.
 */
public class TbDetailAaBillingEntity {
    private String detailAaBilId;
    private Double detailAaBilAmount;
    private int detailAaBilChargeState;

    private TbAaBillingEntity detailAaBilBilling;
    private TbUserEntity detailAaBilUser;

    public String getDetailAaBilId() {
        return detailAaBilId;
    }

    public void setDetailAaBilId(String detailAaBilId) {
        this.detailAaBilId = detailAaBilId;
    }

    public Double getDetailAaBilAmount() {
        return detailAaBilAmount;
    }

    public void setDetailAaBilAmount(Double detailAaBilAmount) {
        this.detailAaBilAmount = detailAaBilAmount;
    }

    public int getDetailAaBilChargeState() {
        return detailAaBilChargeState;
    }

    public void setDetailAaBilChargeState(int detailAaBilChargeState) {
        this.detailAaBilChargeState = detailAaBilChargeState;
    }

    public TbAaBillingEntity getDetailAaBilBilling() {
        return detailAaBilBilling;
    }

    public void setDetailAaBilBilling(TbAaBillingEntity detailAaBilBilling) {
        this.detailAaBilBilling = detailAaBilBilling;
    }

    public TbUserEntity getDetailAaBilUser() {
        return detailAaBilUser;
    }

    public void setDetailAaBilUser(TbUserEntity detailAaBilUser) {
        this.detailAaBilUser = detailAaBilUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TbDetailAaBillingEntity)) return false;

        TbDetailAaBillingEntity that = (TbDetailAaBillingEntity) o;

        if (getDetailAaBilChargeState() != that.getDetailAaBilChargeState()) return false;
        if (getDetailAaBilId() != null ? !getDetailAaBilId().equals(that.getDetailAaBilId()) : that.getDetailAaBilId() != null)
            return false;
        if (getDetailAaBilAmount() != null ? !getDetailAaBilAmount().equals(that.getDetailAaBilAmount()) : that.getDetailAaBilAmount() != null)
            return false;
        if (getDetailAaBilBilling() != null ? !getDetailAaBilBilling().equals(that.getDetailAaBilBilling()) : that.getDetailAaBilBilling() != null)
            return false;
        return getDetailAaBilUser() != null ? getDetailAaBilUser().equals(that.getDetailAaBilUser()) : that.getDetailAaBilUser() == null;
    }

    @Override
    public int hashCode() {
        int result = getDetailAaBilId() != null ? getDetailAaBilId().hashCode() : 0;
        result = 31 * result + (getDetailAaBilAmount() != null ? getDetailAaBilAmount().hashCode() : 0);
        result = 31 * result + getDetailAaBilChargeState();
        result = 31 * result + (getDetailAaBilBilling() != null ? getDetailAaBilBilling().hashCode() : 0);
        result = 31 * result + (getDetailAaBilUser() != null ? getDetailAaBilUser().hashCode() : 0);
        return result;
    }
}
