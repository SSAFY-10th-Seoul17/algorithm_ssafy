import java.util.*;

public class boj14606 {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		printMax(sc.nextInt());
	}
	
	private static void printMax(int N) {
		int res = 0;
		for (int i = 0; i < N; i++) {
			res += i;
		}
		System.out.println(res);
	}

}
