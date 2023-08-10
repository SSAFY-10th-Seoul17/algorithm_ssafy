import java.io.*;
import java.util.*;

public class boj2004 {
	static int counter(long n, int div) {
		int count = 0;
		while (n >= div) {
			count += n / div;
			n /= div;
		}
		return count;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long n = sc.nextLong();
		long m = sc.nextLong();
		
		int count2 = counter(n, 2) - counter(n - m, 2) - counter(m, 2);
		int count5 = counter(n, 5) - counter(n - m, 5) - counter(m, 5);
		
		System.out.println(Math.min(count2, count5));
	}
}
