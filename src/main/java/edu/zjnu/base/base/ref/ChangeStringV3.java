package edu.zjnu.base.base.ref;

/**
 * @description: TODO
 * @author: 杨海波
 * @date: 2022-09-20 16:30
 **/
public class ChangeStringV3 {

    public void changeStr(ChangeStringV3$User user) {
        user = new ChangeStringV3$User("2");
    }

    public static void main(String[] args) {
        ChangeStringV3$User user = new ChangeStringV3$User("1");
        new ChangeStringV3().changeStr(user);
        System.out.println(user.getId());
    }


}

class ChangeStringV3$User {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ChangeStringV3$User(String id) {
        this.id = id;
    }
}
