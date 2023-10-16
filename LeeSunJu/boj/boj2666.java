import java.io.*;
import java.util.*;

public class boj2666 {

	static int n, size;
	static int result;
	static int[] arr;
	static int[] open;
	static int[][][] memo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine()); // 벽장의 개수

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// 초기에 열려있는 벽장
		open = new int[2];
		for (int i = 0; i < 2; i++) {
			open[i] = Integer.parseInt(st.nextToken());
		}

		size  = Integer.parseInt(br.readLine()); // 사용할 벽장 길이
		arr = new int[size];
 		for (int i = 0; i < size; i++) {
			 arr[i] = Integer.parseInt(br.readLine());
		}

		 // 1
//		 result = Integer.MAX_VALUE;
//		 dfs(0, 0);
		memo = new int[size][n + 1][n + 1];
		result = dfs2(0, open[0], open[1]);
		System.out.println(result);
	}

	// 1
	private static void dfs(int idx, int total) {
		if (result <= total) return;

		if (idx == size) {
			result = Math.min(result, total);
			return;
		}

		for (int i = 0; i < 2; i++) {
			int tmp = open[i];
			int diff = Math.abs(open[i] - arr[idx]);
			open[i] = arr[idx];
			dfs(idx + 1, total + diff);
			open[i] = tmp;
		}
	}

	// 2
	private static int dfs2(int idx, int a, int b) {
		if (idx == size) {
			return 0;
		}

		if (memo[idx][a][b] != 0) return memo[idx][a][b];

		int aDiff = Math.abs(a - arr[idx]) + dfs2(idx + 1, arr[idx], b);
		int bDiff = Math.abs(b - arr[idx]) + dfs2(idx + 1, a, arr[idx]);
		int total = result + Math.min(aDiff, bDiff);
		memo[idx][a][b] = total;

		return total;
	}
}
