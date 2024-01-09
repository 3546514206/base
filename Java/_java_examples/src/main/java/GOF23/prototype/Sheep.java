package com.gqz.prototype;

import java.io.Serializable;
import java.util.Date;


public class Sheep implements Cloneable, Serializable {   //1997,英国的克隆羊，多利！
    private String sname;
    private Date birthday;


    public Sheep(String sname, Date birthday) {
        super();
        this.sname = sname;
        this.birthday = birthday;
    }


    public Sheep() {
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object obj = super.clone();  //直接调用object对象的clone()方法！
        return obj;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

}
