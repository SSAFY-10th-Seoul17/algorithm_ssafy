import java.io.*;
import java.util.*;

// 서울 지하철 2호선
public class boj16947 {
	static int n;

	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static int[] roots;
	
	// 하나의 사이클만이 존재할 수 있고 이를 저장한다.
	static HashSet<Integer> cycle = new HashSet<>();
	static boolean[] visited;
	static int found = -1;
	static int[] ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		n = Integer.valueOf(br.readLine());
		roots = new int[n+1];
		ans = new int[n+1];
		for (int i = 0; i <= n; i++) {
			ans[i] = -1;
			roots[i] = i;
			graph.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.valueOf(st.nextToken());
			int b = Integer.valueOf(st.nextToken());
			
			graph.get(a).add(b);
			graph.get(b).add(a);
			boolean result = union(a, b);
			// 사이클 발생시
			if(result) {
				visited = new boolean[n + 1];
				findCycle(a, 0);
			}
		}
		
		// 사이클로부터 거리 검사
//		visited = new boolean[n + 1];
		for (int node : cycle) {
			for(int next : graph.get(node)) {
				if (cycle.contains(next)) {
					ans[next] = 0;
				}
			}
		}

		for (int node : cycle) {
			for(int next : graph.get(node)) {
				if (cycle.contains(next)) {
					bfs(next);
				}
			}
		}
		
		for (int i = 1; i <= n; i++) {
			sb.append(ans[i] + " ");
		}
		System.out.println(sb);
	}
	

	private static void bfs(int node) {
		ans[node] = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(node);
		while (!q.isEmpty()) {
			int now = q.poll();
			for(int next : graph.get(now)) {
				if (ans[next] != -1)
					continue;
				ans[next] = ans[now] + 1;
				q.add(next);
			}
		}
	}


	// visited 체크를 하며 사이클 검사
	private static int findCycle(int node, int prevNode) {
		visited[node] = true;
		for (Integer next : graph.get(node)) {
			if (next == prevNode)
				continue;
			// 사이클 발생
			if (visited[next]) {
				found = next;
				cycle.add(node);
				return found;
			}
			
			int result = findCycle(next, node);
			if (result != -1) {
				cycle.add(node);
				if (result == node) {
					return -1;
				}
				else {
					return found;
				}
			}
		}
		return -1;
	}

	private static int find(int a) {
		if (roots[a] == a)
			return a;
		
		roots[a] = find(roots[a]);
		return roots[a];
	}

	private static boolean union(int a, int b) {
		int ra = find(a);
		int rb = find(b);
		roots[rb] = ra;
		// 같으면 사이클 발생으로 간주
		return ra == rb;
	}
}
