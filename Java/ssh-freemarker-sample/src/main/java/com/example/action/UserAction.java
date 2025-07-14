package com.example.action;

import com.example.model.User;
import com.example.service.UserService;
import java.util.List;

public class UserAction {
    private UserService userService;
    private User user;
    private List<User> users;

    public String add() {
        userService.addUser(user);
        return "success";
    }

    public String list() {
        users = userService.getUsers();
        return "list";
    }

    public void setUser(User user) { this.user = user; }
    public User getUser() { return user; }

    public void setUsers(List<User> users) { this.users = users; }
    public List<User> getUsers() { return users; }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}