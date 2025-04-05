package com.gqz.udp;

//javabean 封装数据
public class Employee implements java.io.Serializable {
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;
    private transient String name; //该数据不需要序列化
    private double salay;

    public Employee() {
    }

    public Employee(String name, double salay) {
        super();
        this.name = name;
        this.salay = salay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalay() {
        return salay;
    }

    public void setSalay(double salay) {
        this.salay = salay;
    }
}
