import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= T; t++) {
			int N = Integer.parseInt(br.readLine());  // 동전 가지 수
			int[] coin = new int[N];  // 동전의 각 금액
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < coin.length; i++) {
				coin[i] = Integer.parseInt(st.nextToken());
			} 
			int M = Integer.parseInt(br.readLine());  // 동전으로 만들어야 할 금액
			
			int[] dp = new int[M+1];
			dp[0] = 1;
			for (int i = 0; i < coin.length; i++) {
				for (int j = coin[i]; j < dp.length; j++) {
					dp[j] += dp[j-coin[i]];
				}
			}
			
			sb.append(dp[M]).append('\n');

		}
		
		System.out.println(sb);
		
	}

}
