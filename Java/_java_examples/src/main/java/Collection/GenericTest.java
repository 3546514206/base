package Collection;

import java.util.Vector;

public class GenericTest {
	public static void main(String[] args) {

		MyCollection mc = new MyCollection();

		Vector<String> vs = new Vector<String>();

//		mc.set("gqz", 0);
//		mc.set(341432, 1);
//		
//		String a = (String)mc.get(0);
//		Integer b = (Integer) mc.get(1);
//		
//		System.out.println(a+"  "+b);
//		
//		MyGeneric<String> mg =new MyGeneric<String>();
//		mg.set("fwefe", 0);
//		mg.set("fwefw", 1);
//		
//		System.out.println(mg.get(0)+"  "+mg.get(1));
//		
//		List<String> list = new ArrayList<String>();
//		
//		list.add("123");

//		vs.add("A");
//		vs.add("B");
//		vs.add("C");
//		vs.add("D");
//		vs.add("A");
//		System.out.println(vs.lastIndexOf("A"));

		String a = "ganquanzhong.q.top";

		String[] split = a.split("\\.");

		for (String s : split) {
			System.out.println(s);
		}
	}


}


class MyCollection {
	Object[] objs = new Object[5];

	public void set(Object obj, int index) {
		objs[index] = obj;
	}

	public Object get(int index) {
		return objs[index];
	}
}

class MyGeneric<E> {
	Object[] objs = new Object[5];

	public void set(E e, int index) {
		objs[index] = e;
	}

	public E get(int index) {
		return (E) objs[index];
	}
}
