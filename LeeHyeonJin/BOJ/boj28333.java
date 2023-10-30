import java.io.*;
import java.util.*;

public class boj28333 {
	static int N, M;
	static List<List<Integer>> ordered;
	static List<List<Integer>> reversed;
	static int[] distance;
	static List<Integer> results;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			// input
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			ordered = new ArrayList<>();
			reversed = new ArrayList<>();
			for(int i=0; i<=N; i++) {
				ordered.add(new ArrayList<>());
				reversed.add(new ArrayList<>());
			}

			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				ordered.get(from).add(to);
				reversed.get(to).add(from);
			}

			// solution
			distance = new int[N+1];
			Arrays.fill(distance, Integer.MAX_VALUE);
			results = new ArrayList<>();
			BFS(); // 각 노드의 최단거리 구하기
			backtrack(); // 역추적
			Collections.sort(results);

			// output
			for(int result : results) {
				sb.append(result).append(" ");
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
	}

	static void BFS() {
		Queue<int[]> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		queue.offer(new int[] {1, 1});
		visited[1] = true;
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			distance[now[0]] = Math.min(distance[now[0]], now[1]);
			for(int next : ordered.get(now[0])) {
				if(visited[next]) {
					continue;
				}
				queue.add(new int[] {next, now[1]+1});
				visited[next] = true;
			}
		}
	}

	/**
	 * backtrack 원리 :
	 * - 각 노드별(구간별) 최단거리인지 확인하면 된다
	 * - 이미 BFS 를 통해 전체를 1번씩 모두 탐색하면서 각 노드의 구간별 최단거리를 구해놓은 상태
	 * - 따라서 뒤에서부터 확인하면서 그 노드가 최단거리가 맞는지만 비교해보면 되는 것
	 */
	static void backtrack() {
		int compDist = distance[N]; //각 노드의 현재 거리가 최소인지 비교해주기 위한 기준값
		Queue<Integer> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[N+1];
		queue.add(N);
		visited[N] = true;
		while(!queue.isEmpty()) {
			Queue<Integer> temp = new ArrayDeque<>();
			compDist--;
			while(!queue.isEmpty()) {
				int now = queue.poll();
				results.add(now);
				for(int next : reversed.get(now)) {
					if(visited[next] || distance[next] != compDist) {
						continue;
					}
					temp.offer(next);
					visited[next] = true;
				}
			}
			queue = temp;
		}
	}
}

