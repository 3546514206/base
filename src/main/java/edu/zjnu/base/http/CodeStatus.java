package edu.zjnu.base.http;

/**
 * @description: CodeStatus
 * @author: 杨海波
 * @date: 2022-01-14
 **/
public enum CodeStatus {

    SUCCESS(200, "SUCCESS");

    private Integer status;
    private String desc;

    CodeStatus(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
