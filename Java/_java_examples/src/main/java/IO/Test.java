package IO;

public class Test {
	public static void main(String[] args) {
		String a = new String("abc");
		String b = new String("abc");
		String c = "abc";
		String d = "a" + "b" + "c";

		System.out.println("==================");
		System.out.println(a == b);
		System.out.println(a.equals(b));
		System.out.println(a.hashCode() + "  " + b.hashCode());

		System.out.println("==================");
		System.out.println(a == c);
		System.out.println(a.equals(c));
		System.out.println(a.hashCode() + "  " + c.hashCode());


		System.out.println("==================");
		System.out.println(a == d);
		System.out.println(a.equals(d));
		System.out.println(a.hashCode() + "  " + d.hashCode());

		System.out.println("==================");
		System.out.println(c.toUpperCase());
		System.out.println(c.equals(d));
		System.out.println(c.hashCode() + "  " + c.hashCode());

	}
}
