package IO;

//抽象组件
interface Drink {
	double cost();//费用

	String info();//说明
}

/*
 * Docrator 装饰器设计模式
 */
public class DecorateTest02 {
	public static void main(String[] args) {
		Drink coffee = new Coffee();
		Drink suger = new Suger(coffee);

		System.out.println(suger.info() + "-->" + suger.cost());

		Drink milk = new Milk(coffee);
		System.out.println(milk.info() + "-->" + milk.cost());

		Drink milk_suger = new Milk(suger);
		System.out.println(milk_suger.info() + "-->" + milk_suger.cost());

	}
}

//具体组件
class Coffee implements Drink {
	private String name = "原味咖啡(10元)";

	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public String info() {
		// TODO Auto-generated method stub
		return name;
	}

}

//抽象装饰类
abstract class Decorate implements Drink {
	//对抽象组件的引用
	private Drink drink;

	public Decorate(Drink drink) {
		this.drink = drink;
	}

	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return this.drink.cost();
	}

	@Override
	public String info() {
		// TODO Auto-generated method stub
		return this.drink.info();
	}
}

//具体的装饰类
class Milk extends Decorate {
	public Milk(Drink drink) {
		super(drink);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return super.cost() + 8;
	}

	@Override
	public String info() {
		// TODO Auto-generated method stub
		return super.info() + "加入了牛奶(cost+8元)";
	}
}


class Suger extends Decorate {
	public Suger(Drink drink) {
		super(drink);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double cost() {
		// TODO Auto-generated method stub
		return super.cost() + 4;
	}

	@Override
	public String info() {
		// TODO Auto-generated method stub
		return super.info() + "加入了蔗糖(cost+4元)";
	}
}

