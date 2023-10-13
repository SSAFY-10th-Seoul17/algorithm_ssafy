import java.io.*;
import java.util.*;

public class boj5567 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		List<List<Integer>> graph = new ArrayList<>();
		for(int i=0; i<=n; i++) graph.add(new ArrayList<>());

		for(int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		// solution
		final int SANGGUEN = 1;
		int cnt = count(n, graph, SANGGUEN);

		// output
		System.out.println(cnt);
	}

	/* 아이디어 : 큐에 넣어서 인접그래프 탐색, 종료조건은 카운팅횟수 2회(상근->친구, 상근친구-> 친구) */
	static int count(int n, List<List<Integer>> graph, int SANGGUEN) {
		int cnt = 0;
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[n+1];
		queue.offer(new int[] {SANGGUEN, 0});
		visited[SANGGUEN] = true;

		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for(int next : graph.get(now[0])) {
				if(now[1] < 2 && !visited[next]) {
					queue.offer(new int[] {next, now[1]+1});
					visited[next] = true;
					cnt++;
				}
			}
		}

		return cnt;
	}
}
