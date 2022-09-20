package edu.zjnu.base.base.ref;

/**
 * @description: TODO
 * @author: 杨海波
 * @date: 2022-09-20 16:36
 **/
public class ChangeStringV4 {

    public ChangeStringV4$User changeStr(ChangeStringV4$User user) {
        user = new ChangeStringV4$User("2");
        return user;
    }

    public static void main(String[] args) {
        ChangeStringV4$User user = new ChangeStringV4$User("1");
        System.out.println((new ChangeStringV4().changeStr(user).getId()));
    }
}

class ChangeStringV4$User {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ChangeStringV4$User(String id) {
        this.id = id;
    }
}

