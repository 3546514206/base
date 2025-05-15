package com.lyr_ssh.service.impl;


import com.lyr_ssh.dao.UserDao;
import com.lyr_ssh.entity.User;
import com.lyr_ssh.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userdao;

    public void setUserdao(UserDao userdao) {
        this.userdao = userdao;
    }

    public User getByUserCode(String userCode) {
        User user = userdao.getByUserCode(userCode);
        return user;
    }

    /**
     * ��¼ҵ���߼�
     */
    public User getByUserByCodePassword(User user) {
        // 1.�������Ʋ�ѯ��¼�û�
        User existU = userdao.getByUserCode(user.getUser_code());
        // 2.�ж��û��Ƿ���ڣ�������=���׳��쳣����ʾ�û���������
        if (existU == null) {
            throw new RuntimeException("用户不存在！");
        }
        // 3.�ж��û������Ƿ���ȷ=������ȷ=���׳��쳣����ʾ�������
        if (!existU.getUser_password().equals(user.getUser_password())) {
            throw new RuntimeException("密码错误！");
        }
        return existU;
    }

    /**
     * ע���û�ҵ���߼�
     */
    public void saveUser(User u) {

    }

}
