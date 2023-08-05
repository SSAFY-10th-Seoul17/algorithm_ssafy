import java.util.*;

public class boj17103 {

	static Scanner sc = new Scanner(System.in);
	static boolean[] nums = new boolean[1000001];
	
	public static void main(String[] args) {
		int T = sc.nextInt();
		makePrimeArr(nums);

		for (int i = 0; i < T; i++) {
			int N = sc.nextInt();
			int cnt = 0;	
			for (int j = 2; j <= (N / 2); j++) {
				if (!(isNotPrime(j)) && !(isNotPrime(N-j))) {
					cnt++;
				}
			}
			System.out.println(cnt);			
		}

	}
	
	private static boolean isNotPrime(int N) {
		return nums[N];
	}
	
	// 소수 판별 배열 생성
	private static void makePrimeArr(boolean[] nums) {
		
		nums[0] = true;
		nums[1] = true;
	
		for (int i = 2; i < Math.sqrt(nums.length) + 1; i++) {
			if (nums[i] == false) {
				for (int j = i * 2; j < nums.length; j += i) {
					nums[j] = true;
				}
			}
		}
	}
	

}
