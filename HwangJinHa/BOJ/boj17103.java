import java.util.Arrays;
import java.util.Scanner;

public class boj17103 {
	static int n;
	// 소수일때 false
	static boolean[] sosu;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		sosu = new boolean[1000001];
		for(int i = 2; i < 1000001; i++) {
			// 소수가 아닌경우 
			if (sosu[i]) {
				continue;
			}
			int idx = 2;
			int num = idx * i;
			while (num < 1000001) {
				sosu[num] = true;
				idx++;
				num = idx * i;
			}
		}
		sosu[1] = true;
		
//		System.out.println(Arrays.toString(sosu));
		
		int t = sc.nextInt();
		while (t-- != 0) {
			int num = sc.nextInt();
			int cnt = 0;
			for (int i = 1; i <= num / 2; i++) {
				if (!sosu[i] && !sosu[num-i]) {
					cnt++;
//					System.out.println(i + " " + (num - i));
				}
			}
			System.out.println(cnt);
			
		}
		
	}
}
