package edu.zjnu.base.base.cyclicDependencies;

/**
 * @description: CyclicDependencies
 * @author: 杨海波
 * @date: 2021-08-27
 **/
public class CyclicDependencies {
    public static void main(String args[]){
        Chicken c = new Chicken() ;
    }
}

class Chicken{
    private Egg e ;
    private int age ;
    public Chicken(){
        e = new Egg() ;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }

}
class Egg{
    private Chicken chicken ;
    private int weight ;
    public Egg(){
        chicken = new Chicken() ;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
}