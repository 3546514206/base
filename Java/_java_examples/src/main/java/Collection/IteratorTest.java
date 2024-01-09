package Collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * 测试迭代器遍历  list set map
 */
public class IteratorTest {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("aa");
		list.add("bb");
		list.add("cc");

		System.out.println(list);

		for (String string : list) {
			System.out.println(string);
		}

		System.out.println("使用iterator");
		for (Iterator<String> itr = list.iterator(); itr.hasNext(); ) {
			String next = itr.next();
			System.out.println(next);
		}
//		System.exit(0);
	}
}
