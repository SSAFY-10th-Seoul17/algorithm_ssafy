import java.io.*;
import java.util.*;

/*
* 특정 종류의 동전(비용)을 통해 목표금액(가치)을 만드는 모든 경우의 수 -> 완탐하기엔 최대 10000 -> 이전의 결과값을 저장해두고 재사용한다 => DP
* 구해야하는 것 : M을 만드는 경우의 수 -> 1 ~ M까지 완탐(중복 연산 X)
* 재료 : 동전
* 동전의 개수는 무한정 => 앞에서부터 DP 테이블 누적 가능
* */
public class boj9084 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			// input
			int N = Integer.parseInt(br.readLine());
			int[] coins = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i=0; i<N; i++) {
				coins[i] = Integer.parseInt(st.nextToken());
			}
			int M = Integer.parseInt(br.readLine());

			// solution
			int[] dp = new int[M+1];
			dp[0] = 1;

			for(int i=0; i<N; i++) {
				for(int j=1; j<=M; j++) {
					if(j-coins[i] >= 0) {
						dp[j] += dp[j-coins[i]];
					}
				}
			}

			// output
			sb.append(dp[M]).append("\n");
		}

		System.out.println(sb.toString());
	}
}
