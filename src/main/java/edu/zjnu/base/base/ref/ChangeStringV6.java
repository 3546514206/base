package edu.zjnu.base.base.ref;

public class ChangeStringV6 {
    public void changeStr(ChangeStringV6$User user) {
        user.setId("2");
    }

    public static void main(String[] args) {
        ChangeStringV6$User user = new ChangeStringV6$User("1");
        new ChangeStringV6().changeStr(user);
        System.out.println(user.getId());
    }


}

class ChangeStringV6$User {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ChangeStringV6$User(String id) {
        this.id = id;
    }
}
