import java.io.*;
import java.util.*;

public class boj10159 {
	static int N, M;
	static int[] cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		cnt = new int[N + 1];

		ArrayList<ArrayList<Integer>> in = new ArrayList<>(); // 진입
		ArrayList<ArrayList<Integer>> out = new ArrayList<>(); // 진출
		for (int i = 0; i <= N; i++) {
			in.add(new ArrayList<>());
			out.add(new ArrayList<>());
		}

		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			in.get(a).add(b);
			out.get(b).add(a);
		}

		for (int i = 1; i <= N; i++) {
			dfs(i, i, in, new boolean[N + 1]);
			dfs(i, i, out, new boolean[N + 1]);
		}


		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			sb.append(N - cnt[i] - 1).append("\n");
		}
		System.out.print(sb.toString());
	}

	private static void dfs(int i, int start, ArrayList<ArrayList<Integer>> list, boolean[] visited) {
		visited[i] = true;
		for (int n : list.get(i)) {
			if (!visited[n]) {
				cnt[start]++;
				dfs(n, start, list, visited);
			}
		}
	}
}
