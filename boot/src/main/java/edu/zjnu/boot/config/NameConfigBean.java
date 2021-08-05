package edu.zjnu.boot.config;

/**
 * @description: NameConfigBean
 * @author: 杨海波
 * @date: 2021-08-05
 **/
public class NameConfigBean {

    private String bootConfigValue;

    public NameConfigBean(String bootConfigValue) {
        this.bootConfigValue = bootConfigValue;
    }

    public String getBootConfigValue() {
        return bootConfigValue;
    }

    public void setBootConfigValue(String bootConfigValue) {
        this.bootConfigValue = bootConfigValue;
    }
}
