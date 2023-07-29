import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj17103 {
	public static int t, n, count;
	public static boolean[] isPrimeNum;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		t = Integer.parseInt(bf.readLine());
		
		for(int i = 0; i < t; i++) {
			n = Integer.parseInt(bf.readLine());
			isPrimeNum = new boolean[n + 1];
			
			// n까지의 소수 구하기
			findPrimeNum(n);

			// 반복문 돌면서 짝 찾기
			count = 0;
			int len = n / 2;
			for(int j = 2; j <= len; j++) {
				if(isPrimeNum[j] && isPrimeNum[n - j]) {
					count++;
				}
			}
			
			System.out.println(count);	
		}
	}
	
	/**
	 * 에라토스테네스의 체 사용해서 소수 구하기
	 */
	public static void findPrimeNum(int num) {
		for(int i = 2; i <= n; i++) {
			isPrimeNum[i] = true;
		}
		
		for(int i = 2; (i * i) <= n; i++) {
			if(isPrimeNum[i]) {
				for(int j = i * i; j <= n; j += i) {
					isPrimeNum[j] = false;
				}				
			}
		}
	}
}
