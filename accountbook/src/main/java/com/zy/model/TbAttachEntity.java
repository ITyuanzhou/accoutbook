package com.zy.model;

/**
 * Created by ZJZL_HP on 2017/7/20.
 */
public class TbAttachEntity {
    private String aid;
    private String afileName;
    private String afileUuid;
    private String afilePosition;

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getAfileName() {
        return afileName;
    }

    public void setAfileName(String afileName) {
        this.afileName = afileName;
    }

    public String getAfileUuid() {
        return afileUuid;
    }

    public void setAfileUuid(String afileUuid) {
        this.afileUuid = afileUuid;
    }

    public String getAfilePosition() {
        return afilePosition;
    }

    public void setAfilePosition(String afilePosition) {
        this.afilePosition = afilePosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TbAttachEntity that = (TbAttachEntity) o;

        if (aid != null ? !aid.equals(that.aid) : that.aid != null) return false;
        if (afileName != null ? !afileName.equals(that.afileName) : that.afileName != null) return false;
        if (afileUuid != null ? !afileUuid.equals(that.afileUuid) : that.afileUuid != null) return false;
        if (afilePosition != null ? !afilePosition.equals(that.afilePosition) : that.afilePosition != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = aid != null ? aid.hashCode() : 0;
        result = 31 * result + (afileName != null ? afileName.hashCode() : 0);
        result = 31 * result + (afileUuid != null ? afileUuid.hashCode() : 0);
        result = 31 * result + (afilePosition != null ? afilePosition.hashCode() : 0);
        return result;
    }
}
