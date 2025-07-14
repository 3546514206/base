package com.example.service;

import com.example.dao.UserDao;
import com.example.model.User;
import java.util.List;

public class UserService {
    private UserDao userDao;

    public void addUser(User user) {
        userDao.save(user);
    }

    public List<User> getUsers() {
        return userDao.list();
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}