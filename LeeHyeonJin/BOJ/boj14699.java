import java.io.*;
import java.util.*;

/**
 * 아이디어 : 1~N 개의 쉼터마다의 연결하는 길의 최대 경우의 수
 * - 처음에는 BFS 를 통해 각 출발지부터 경유하는 모든 노드들의 개수를 세어주었습니다 -> 메모리초과
 * - 메모리초과를 수정해도 시간초과가 발생할 것 같아서 DFS + 메모이제이션을 해주었습니다
 */
public class boj14699 {
	static int N, M;
	static int[] heights;
	static List<List<Integer>> graph = new ArrayList<>();
	static int[] memo;


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		heights = new int[N+1];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=1; i<=N; i++) heights[i] = Integer.parseInt(st.nextToken());

		for(int i=0; i<=N; i++) graph.add(new ArrayList<>());
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(heights[a] < heights[b]) graph.get(a).add(b);
			else graph.get(b).add(a);
		}

		// solution
		memo = new int[N+1];
		int[] results = new int[N+1];
		for(int i=1; i<=N; i++) {
			results[i] = DFS(i);
		}

		// output
		StringBuilder sb = new StringBuilder();
		for(int i=1; i<=N; i++) sb.append(results[i]).append("\n");
		System.out.println(sb.toString());
	}

	static int DFS(int now) {
		if(graph.get(now).size() == 0) {
			return 1;
		}
		if(memo[now] != 0 ) {
			return memo[now];
		}
		int max = Integer.MIN_VALUE;
		for(int next : graph.get(now)) {
			max = Math.max(max, DFS(next));
		}
		return memo[now] = max + 1;
	}
}
