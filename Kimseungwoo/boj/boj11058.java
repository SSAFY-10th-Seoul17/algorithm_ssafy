package boj;

import java.util.*;

public class boj11058 {
	
	static int buf = 0;
	static long[] lengthDP;
	static int count;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		count = sc.nextInt();
		lengthDP = new long[101];
		lengthDP[1] = 1;
		lengthDP[2] = 2;
		lengthDP[3] = 3;
		
		for(int i = 4; i <= count; i++) {
			lengthDP[i] = lengthDP[i-1]+1;
			
			for(int j = i; j >= 3; j--) {
				lengthDP[i] = Math.max(lengthDP[i], lengthDP[i-j]*(j-1));
			}
		}
		
		System.out.println(lengthDP[count]);
	}

}
