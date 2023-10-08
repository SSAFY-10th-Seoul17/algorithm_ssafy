import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int burger;
	private static int frenchfires;
	private static int[][] dp;
	private static int[][] orders;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		burger = Integer.parseInt(st.nextToken()); // 치즈버거 개수
		frenchfires = Integer.parseInt(st.nextToken()); // 감자튀김 개수
		orders = new int[N][2];
		dp = new int[burger + 1][frenchfires + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine()," ");
			orders[i][0] = Integer.parseInt(st.nextToken());
			orders[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = burger; j >= orders[i][0]; j--) {
				for (int k = frenchfires; k >= orders[i][1]; k--) {
					dp[j][k] = Math.max(dp[j][k], dp[j-orders[i][0]][k-orders[i][1]]+1);
				}
			}
		}
		System.out.println(dp[burger][frenchfires]);
	} // end of main
} // end of class
