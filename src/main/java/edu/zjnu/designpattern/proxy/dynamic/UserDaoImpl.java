package edu.zjnu.designpattern.proxy.dynamic;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2021-12-04
 **/
public class UserDaoImpl implements UserDao {

    @Override
    public void save() {
        System.out.println("保存数据");
    }
}
