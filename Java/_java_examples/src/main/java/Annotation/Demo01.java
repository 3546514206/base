package Annotation;


public class Demo01 {

	public static void main(String[] args) {
		new Demo01().say();
		new Demo01().speak();
	}

	@Override
	public String toString() {
		return "";
	}

	@TestAnnotation01
	public void Test() {

	}

	@Deprecated

	public void say() {
		System.out.println("Noting has to say!");
	}

	public void speak() {
		System.out.println("I have a dream!");
	}

}
