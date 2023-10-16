import java.io.*;
import java.util.*;

public class boj4811 {
	static long[][] memo;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N;
		while((N = Integer.parseInt(br.readLine())) != 0) {
			// solution
			memo = new long[N+1][N+1];
			long res = DFS(N, 0);

			// output
			sb.append(res).append("\n");
		}

		System.out.println(sb.toString());
	}

	static long DFS(int cntW, int cntH) {
		// 기저조건
		if(cntW == 0) return 1;

		// 메모이제이션
		if(memo[cntW][cntH] != 0) return memo[cntW][cntH];

		// 알약 구분
		memo[cntW][cntH] = DFS(cntW-1, cntH+1); // 한개뽑음
		if(cntH > 0) memo[cntW][cntH] += DFS(cntW, cntH-1); // 반개뽑음

		// 반환
		return memo[cntW][cntH];
	}
}
