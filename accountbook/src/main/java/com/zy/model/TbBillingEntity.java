package com.zy.model;

import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by ZJZL_HP on 2017/7/19.
 */
public class TbBillingEntity {
    private String bid;
    private Double btotalAmount;
    private Timestamp btime;
    private String bremark;
    private int bisAa;
    private TbUserEntity buser;
    private TbBillingTypeEntity bbillingType;
    private Set<TbDetailAaBillingEntity> detailBillings;

    public Set<TbDetailAaBillingEntity> getDetailBillings() {
        return detailBillings;
    }

    public void setDetailBillings(Set<TbDetailAaBillingEntity> detailBillings) {
        this.detailBillings = detailBillings;
    }

    public TbBillingTypeEntity getBbillingType() {
        return bbillingType;
    }

    public void setBbillingType(TbBillingTypeEntity bbillingType) {
        this.bbillingType = bbillingType;
    }

    public TbUserEntity getBuser() {
        return buser;
    }

    public void setBuser(TbUserEntity buser) {
        this.buser = buser;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public Double getBtotalAmount() {
        return btotalAmount;
    }

    public void setBtotalAmount(Double btotalAmount) {
        this.btotalAmount = btotalAmount;
    }

    public Timestamp getBtime() {
        return btime;
    }

    public void setBtime(Timestamp btime) {
        this.btime = btime;
    }

    public String getBremark() {
        return bremark;
    }

    public void setBremark(String bremark) {
        this.bremark = bremark;
    }

    public int getBisAa() {
        return bisAa;
    }

    public void setBisAa(int bisAa) {
        this.bisAa = bisAa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbBillingEntity that = (TbBillingEntity) o;

        if (bisAa != that.bisAa) return false;
        if (bid != null ? !bid.equals(that.bid) : that.bid != null) return false;
        if (btotalAmount != null ? !btotalAmount.equals(that.btotalAmount) : that.btotalAmount != null) return false;
        if (btime != null ? !btime.equals(that.btime) : that.btime != null) return false;
        if (bremark != null ? !bremark.equals(that.bremark) : that.bremark != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bid != null ? bid.hashCode() : 0;
        result = 31 * result + (btotalAmount != null ? btotalAmount.hashCode() : 0);
        result = 31 * result + (btime != null ? btime.hashCode() : 0);
        result = 31 * result + (bremark != null ? bremark.hashCode() : 0);
        result = 31 * result + bisAa;
        return result;
    }
}
