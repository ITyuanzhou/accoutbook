package com.zy.model;

/**
 * Created by ZJZL_HP on 2017/7/19.
 */
public class TbBillingTypeEntity {
    private String btid;
    private String bttypeName;

    public String getBtid() {
        return btid;
    }

    public void setBtid(String btid) {
        this.btid = btid;
    }

    public String getBttypeName() {
        return bttypeName;
    }

    public void setBttypeName(String bttypeName) {
        this.bttypeName = bttypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbBillingTypeEntity that = (TbBillingTypeEntity) o;

        if (btid != null ? !btid.equals(that.btid) : that.btid != null) return false;
        if (bttypeName != null ? !bttypeName.equals(that.bttypeName) : that.bttypeName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = btid != null ? btid.hashCode() : 0;
        result = 31 * result + (bttypeName != null ? bttypeName.hashCode() : 0);
        return result;
    }
}
