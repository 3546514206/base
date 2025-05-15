package com.lyr_ssh.dao;

import com.lyr_ssh.entity.User;

public interface UserDao extends BaseDao<User> {

    /**
     * ͨ���û��������û�
     */
    User getByUserCode(String userCode);


}
