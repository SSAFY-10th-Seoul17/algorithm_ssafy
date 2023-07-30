import java.util.Scanner;

public class boj17484 {
	static int n, m;
	static int[][][] dp;
	static int[][] arr;
	
	static void disp() {
		for(int i = 0; i < 3; i++) {
			System.out.println("방향 : " + i);
			for (int y = 0; y < n; y++) {
				for (int x = 0; x < m; x++) {
					System.out.print(dp[i][y][x] + " ");
				}
				System.out.println();
			}
			System.err.println();
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		m = sc.nextInt();
		
		dp = new int[3][n][m];
		arr = new int[n][m];
		for(int y = 0; y < n; y++) 
			for(int x = 0; x < m; x++) 
				arr[y][x] = sc.nextInt();
		
		for(int y = 0; y < n; y++) 
			for(int x = 0; x < m; x++) 
				for(int i = 0; i < 3; i++) // 다음진행방향
					dp[i][y][x] = Integer.MAX_VALUE;
		for(int x = 0; x < m; x++) 
			for(int i = 0; i < 3; i++) // 다음진행방향
				dp[i][0][x] = arr[0][x];

		for(int y = 0; y < n - 1; y++) {
			for(int x = 0; x < m; x++) {
				for(int prev = 0; prev < 3; prev++) { // 지금 고려하는테이블 
					for(int i = 0; i < 3; i++) { // 다음 진행방향 
						if (i == prev)
							continue;
						int xx = x + i - 1;
						if (m <= xx || xx < 0)
							continue;
						if (dp[prev][y][x] == Integer.MAX_VALUE)
							continue;
						dp[i][y+1][xx] = Math.min(
								dp[i][y+1][xx],
								dp[prev][y][x] + arr[y+1][xx]);
					}
				}
			}
		}
		
		int ans = Integer.MAX_VALUE;
		for(int x = 0; x < m; x++) {
			for(int d = 0; d < 3; d++) {
				ans = Math.min(ans, dp[d][n-1][x]);
			}
		}
		System.out.println(ans);

	}
}
