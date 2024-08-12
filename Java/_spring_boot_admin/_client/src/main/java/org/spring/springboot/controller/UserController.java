package org.spring.springboot.controller;

import org.spring.springboot.domain.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 杨海波
 * @date: 2023-02-14 17:18:29
 * @description: 
 */
@RestController
public class UserController {

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public User login(@RequestBody User user) {
        user.setId("-1");
        user.setName("setsunayang");
        user.setAge("18");
        return user;
    }
}
