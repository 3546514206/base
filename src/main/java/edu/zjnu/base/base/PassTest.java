package edu.zjnu.base.base;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2022-01-13
 **/
public class PassTest {

    public static void main(String[] args) {
        PassTest pt = new PassTest();

        User hollis = new User();
        hollis.setName("Hollis");
        hollis.setGender("Male");
        pt.pass(hollis);
        System.out.println("print in main , user is " + hollis);
    }

    public void pass(User user) {
        user = new User();
        user.setName("hollischuang");
        user.setGender("Male");
        System.out.println("print in pass , user is " + user);
    }
}

class User {

    private String name;

    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
