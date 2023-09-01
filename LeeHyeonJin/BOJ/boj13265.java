import java.io.*;
import java.util.*;

public class boj13265 {
	static int n, m;
	static List<List<Integer>> graph;
	static int[] colors;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			// input
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			graph = new ArrayList<>();
			for(int i=0; i<=n; i++) {
				graph.add(new ArrayList<>());
			}
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				graph.get(x).add(y);
				graph.get(y).add(x);
			}

			// solution
			colors = new int[n+1];
			boolean isPossible = true;

			for(int i=1; i<=n; i++) {
				if(colors[i] != 0) continue;
				if(!BFS(i)) {
					isPossible = false;
					break;
				}
			}

			// output
			sb.append(isPossible ? "possible" : "impossible").append("\n");
		}

		System.out.println(sb.toString());
	}

	static boolean BFS(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(start);
		colors[start] = 1;

		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(int next : graph.get(now)) {
				if(colors[next] != 0) { //다음에 칠할 노드가 이미 색칠되어 있는 경우 => 현재 노드와 다음 노드의 색이 같으면 2가지 색으로 불가능
					if(colors[now] == colors[next]) return false;
				} else { //다음에 칠할 노드가 아직 색칠되지 않은 경우 => 현재 노드의 색과 다른 색으로 색칠
					colors[next] = -(colors[now]);
					queue.offer(next);
				}
			}
		}

		return true; //무사히 BFS를 다 돌면 색칠가능한 경우
	}
}
