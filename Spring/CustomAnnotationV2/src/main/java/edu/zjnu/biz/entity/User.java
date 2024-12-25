package edu.zjnu.biz.entity;

/**
 * @description: todo
 * @author: 杨海波
 * @date: 2022-01-29
 **/
public class User {

    private Long id;

    private String name;

    private Integer age;

    private String uuid;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", uuid='" + uuid + '\'' +
                '}';
    }
}
