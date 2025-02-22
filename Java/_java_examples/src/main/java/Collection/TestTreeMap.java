package Collection;

import java.util.*;
import java.util.Map.Entry;

public class TestTreeMap {
	public static void main(String[] args) {


		Map<Integer, String> tm = new TreeMap<Integer, String>();
		Map<Integer, String> mp = new HashMap<Integer, String>();

		tm.put(1, "fsdf");
		tm.put(34, "34");
		tm.put(46, "gerg");
		tm.put(3, "3");

		Set<Integer> keySet = tm.keySet();
		for (Integer key : keySet) {
			System.out.println(key + "----" + tm.get(key));
		}


		//使用iterator遍历Map  先使用
		System.out.println("使用entrySet");
		Set<Entry<Integer, String>> es = tm.entrySet();
		for (Iterator<Entry<Integer, String>> itr = es.iterator(); itr.hasNext(); ) {
			Entry<Integer, String> temp = itr.next();
			System.out.println(temp.getKey() + "---" + temp.getValue());
		}

		//使用keySet
		System.out.println("使用keySet");
		Set<Integer> key = tm.keySet();
		for (Iterator<Integer> itr = key.iterator(); itr.hasNext(); ) {
			Integer temp = itr.next();
			System.out.println(temp + "-----" + tm.get(temp));
		}

//		int h;
//		System.out.println(8<<1);

		// 0001 0000
		// 0000 1000
		//^0001 1000
		//24
		// 0000 0110
		// 0000 1100
//		System.out.println((h=(8<<1)) );
//		System.out.println((h=(8<<1)) ^ (h >> 1));

	}
}


