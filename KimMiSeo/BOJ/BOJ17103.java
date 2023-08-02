

// 📝 
// 07:03 - 시간초과!!
// 1 ~ 1000000 까지 소수를 모두 체크해야겠다는 생각을 어떻게?
// 시간제한은 0.5초 10^6 

import java.util.*;
import java.io.*;

public class BOJ17103 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());

		for (int tc=0; tc<t; tc++) {
			int n = Integer.parseInt(br.readLine());
			int result = 0;
			
			boolean[] isNotPrime = new boolean[1000001];
			isNotPrime[0] = true;
			isNotPrime[1] = true;
			
			// 1000000
			for (int i=2; i<isNotPrime.length; i++) {
				// i=2 j=4 6 8 10 12 ..  
				// i=3 j=6 9 12 15 ...
				// i=4 j=8 12 .. 배수 
				// 소수는 false
				
				if (!isNotPrime[i]) {
					for (int j = i+i; j<=1000000; j+=i) {
						isNotPrime[j] = true;
					}
				}
			}
			
			// 2도 실수,, i를 3부터 하면 안된다!!! 
			for(int i=2; i<=n/2; i++) {
				int a = i;
				int b = n-i;
				
				if (!isNotPrime[a] && !isNotPrime[b]) {
					result++;
				}
		
			}
		System.out.println(result);
		}
	}
}



