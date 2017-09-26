package com.zy.model;

/**
 * Created by ZJZL_HP on 2017/7/20.
 */
public class TbAttachEntity {

    private String attachId;
    private String attachFileName;
    private String attachFileUuid;
    private String attachFilePosition;

    public String getAttachId() {
        return attachId;
    }

    public void setAttachId(String attachId) {
        this.attachId = attachId;
    }

    public String getAttachFileName() {
        return attachFileName;
    }

    public void setAttachFileName(String attachFileName) {
        this.attachFileName = attachFileName;
    }

    public String getAttachFileUuid() {
        return attachFileUuid;
    }

    public void setAttachFileUuid(String attachFileUuid) {
        this.attachFileUuid = attachFileUuid;
    }

    public String getAttachFilePosition() {
        return attachFilePosition;
    }

    public void setAttachFilePosition(String attachFilePosition) {
        this.attachFilePosition = attachFilePosition;
    }
}
