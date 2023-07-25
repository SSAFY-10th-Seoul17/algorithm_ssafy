package miseo;

// 📝 
// 07:03 - 시간초과!!

import java.util.*;
import java.io.*;

public class BOJ17103 {
	
	static boolean cal(int num) {
		if (num == 1) return false; // 1이어도 false
		if (num %2 == 0) return false; // 짝수면 false
		
		for (int i=2; i*i<=num; i++) {
			if (num % i == 0) return false;
		}		
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		
		// 소수를 만들어서 저장? 
		// 3 5 7 11 13 17 23 29 31 ...
		// dp ? 
		// 2개 더하기 : 모든 경우 저장? 
		// n보다 작거나 클때 까지만 
		for (int tc=0; tc<t; tc++) {
			int n = Integer.parseInt(br.readLine());
			int result = 0;
			
			boolean[] isPrime = new boolean[1000001];
			isPrime[0] = true;
			isPrime[1] = true;
			
			for (int i=2; i<isPrime.length; i++) {
				// i=2 j=4 6 8 10 12 ..  
				// i=3 j=6 9 12 15 ...
				// i=4 j=8 12 .. 배수 
				// 소수는 false
				
				if (!isPrime[i]) {
					for (int j = i+i; j<=1000000; j+=i) {
						isPrime[j] = true;
					}
				}
			}
			
			for(int i=2; i<=n/2; i++) {
				int a = i;
				int b = n-i;
				
				if (!isPrime[a] && !isPrime[b]) {
					result++;
				}
		
		}
		System.out.println(result);
		}
	}
}



