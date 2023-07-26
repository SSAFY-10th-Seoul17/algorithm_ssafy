package myAlgo;

import java.util.*;

class Value {
	static int val;
	
	static void func(int n) {
		if (n <= 1) {
			return;
		}
		
		int over = (int)(n/2);
		int under = n - over;
		
		val += over*under;
		
		func(over);
		func(under);
	}
}

public class Boj14606 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		
		Value.func(n);
		
		System.out.println(Value.val);
	}
}
