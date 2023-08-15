import java.io.*;
import java.util.*;

public class boj1325 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		// input
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<List<Integer>> nodes = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			nodes.add(new ArrayList<>());
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			nodes.get(from).add(to);
		}

		// solution : BFS 각 노드를 탐색하며 연결된 노드까지 탐색하고 cnt++
		int max = Integer.MIN_VALUE;
		int[] cnts = new int[N+1];
		for(int node=1; node<=N; node++) {
			// 현재 노드 탐색
			Deque<Integer> schedule = new ArrayDeque<>();
			boolean[] visited = new boolean[N+1];
			visited[node] = true;
			schedule.offerLast(node);
			int cnt = 0;
			// 현재 노드를 신뢰하는 다른 노드 탐색
			while(!schedule.isEmpty()) {
				int now = schedule.pollFirst();
				for(int next : nodes.get(now)) {
					if(!visited[next]) {
						schedule.offerLast(next);
						visited[next] = true;
						cnt++;
					}
				}
			}
			// 더 큰 범위로 갱신
			max = Math.max(max, cnt);
			cnts[node] = cnt;
		}
		for(int i=1; i<=N; i++) {
			if(cnts[i] == max) {
				sb.append(i+" ");
			}
		}

		// output
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}

