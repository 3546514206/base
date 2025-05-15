package com.lyr_ssh.service;

import com.lyr_ssh.entity.User;

public interface UserService {

    /**
     * �����û��������û�
     */
    User getByUserCode(String userCode);

    User getByUserByCodePassword(User user);

    // ע���û�
    void saveUser(User u);
}
