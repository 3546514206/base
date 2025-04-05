package IO;

public class DiGui {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		printTen(1);
	}


	private static void printTen(int n) {
		if (n > 10) {
			return;
		}
		System.out.println(n);
		printTen(n + 1);
	}
}
