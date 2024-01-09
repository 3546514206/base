package IO;

interface Say {
	void say();
}

/*
 * 实现放大器 对声音的放大功能
 */
public class DecorateTest01 {
	public static void main(String[] args) {
		Person person = new Person();
		person.say();

		Amplifier amplifier = new Amplifier(person);
		amplifier.say();

	}
}

class Person implements Say {

	//属性 
	private int voice = 10;

	public int getVoice() {
		return voice;
	}

	public void setVoice(int voice) {
		this.voice = voice;
	}

	@Override
	public void say() {
		System.out.println("人的声音" + this.getVoice());
	}

}

class Amplifier implements Say {
	private Person p;

	Amplifier(Person p) {
		this.p = p;
	}

	@Override
	public void say() {
		// TODO Auto-generated method stub
		System.out.println("人的声音" + p.getVoice() * 10);
	}

}
