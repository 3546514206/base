package Collection;

import java.util.Arrays;

public class LinkedList01 {
	public static void main(String[] args) {
//		List<String> list = new ArrayList<String>(); 
//		List<String> list2 = new LinkedList<String>(); 
//		
//		list.add("1");
//		list.add("221");
//		list.add("312");
//		list.add("41");
//		list.add("512");
//		System.out.println(list);
//		
//	
//		
//		Collections.reverse(list);
//		System.out.println(list);
//		
//		Collections.shuffle(list);
//		System.out.println(list);
//		
//		Collections.sort(list);
//		System.out.println(list);
//
//		for (int i = 0; i < args.length; i++) {
//			System.out.println(args[i]);
//		}

		int[] a = {32, 4, 1, 6, 84, 5, 72, 745};
		print(a);

		Arrays.sort(a);
		print(a);

		System.out.println(Arrays.binarySearch(a, 32));

	}

	public static void print(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + "  ");
		}
		System.out.println();
	}
}
