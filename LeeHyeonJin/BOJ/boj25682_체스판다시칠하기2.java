import java.io.*;
import java.util.*;

// 아이디어 :
// 1. (0,0) -> (N,M) 까지 각 좌표마다 바꿔야하는 칸의 개수의 "누적합"을 저장한다.
// 2. K x K 사이즈의 칸으로 끝나는 좌표들에 저장된 누적합 중 가장 작은 값을 출력한다.
//    - 이때, 체스판을 색칠하는 경우가 2가지(맨 왼쪽 위 칸이 흰색인 경우, 하나는 검은색인 경우)이므로 위 1,2번 과정을 2번 반복한다.
public class boj25682_체스판다시칠하기2 {
	static int N, M, K;
	static char[][] map;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new char[N+1][M+1];
		for(int r=1; r<=N; r++) {
			String input = br.readLine();
			for(int c=1; c<=M; c++) {
				map[r][c] = input.charAt(c-1);
			}
		}

		// solution
		dp = new int[N+1][M+1];
		int res = Math.min(count('W'), count('B'));

		// output
		System.out.println(res);
	}

	static int count(char start) {
		// (1) 기준 색깔 start 별로 누적합 구하기
		for(int r=1; r<=N; r++) {
			for(int c=1; c<=M; c++) {
				int cnt = 0;
				if((r + c) % 2 == 0 && map[r][c] != start) cnt = 1;
				else if((r + c) % 2 == 1 && map[r][c] == start) cnt = 1;
				dp[r][c] = dp[r-1][c] + dp[r][c-1] - dp[r-1][c-1] + cnt;
			}
		}
		// (2) 구해진 누적합에서 최솟값 구하기
		int min = Integer.MAX_VALUE;
		for(int r=K; r<=N; r++) {
			for(int c=K; c<=M; c++) {
				min = Math.min(min, dp[r][c] - dp[r-K][c] - dp[r][c-K] + dp[r-K][c-K]);
			}
		}
		return min;
	}
}
