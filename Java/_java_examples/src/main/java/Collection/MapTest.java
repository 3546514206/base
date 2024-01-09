package Collection;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {


        Map<String, String> mp = new HashMap<String, String>();


        StringBuilder sb = new StringBuilder();
    }
}


class Employee {
    private String name;
    private int number;


    public Employee(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Employee [name=" + name + ", number=" + number + "]";
    }


}

class Teacher extends Employee {
    private String type;

    public Teacher(String name, int number) {
        super(name, number);
        // TODO Auto-generated constructor stub
    }

    public Teacher(String name, int number, String type) {
        super(name, number);
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Teacher [" + super.toString() + "]" + "   type =" + getType() + "";
    }
}