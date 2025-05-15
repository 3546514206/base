package com.lyr_ssh.entity;

/**
 * 用户类
 *
 * @author Administrator
 */

public class User {
    /*
     * CREATE TABLE `sys_user` (
      `user_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '用户id',
      `user_code` varchar(32) NOT NULL COMMENT '用户账号',
      `user_name` varchar(64) NOT NULL COMMENT '用户名称',
      `user_password` varchar(32) NOT NULL COMMENT '用户密码',
      `user_state` char(1) NOT NULL COMMENT '1:正常,0:暂停',
      PRIMARY KEY (`user_id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
     */
    private Long user_id;
    private String user_code;
    private String user_name;
    private String user_password;
    private Character user_state;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_code() {
        return user_code;
    }

    public void setUser_code(String user_code) {
        this.user_code = user_code;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public Character getUser_state() {
        return user_state;
    }

    public void setUser_state(Character user_state) {
        this.user_state = user_state;
    }

    @Override
    public String toString() {
        return "User [user_id=" + user_id + ", user_code=" + user_code + ", user_name=" + user_name + ", user_password="
                + user_password + "]";
    }


}
