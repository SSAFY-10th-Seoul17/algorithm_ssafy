import java.io.*;
import java.util.*;

// 간선 이어가기 2
public class boj14284 {
	static int n, m;
	static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
	static int s, t;
	
	static PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
		return a[0] - b[0];
	});
	static int[] dist;
	static boolean[] fixed;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.valueOf(st.nextToken());
		dist = new int[n + 1];
		fixed = new boolean[n + 1];
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<int[]>());
			dist[i] = Integer.MAX_VALUE;
		}
		m = Integer.valueOf(st.nextToken());
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.valueOf(st.nextToken());
			int b = Integer.valueOf(st.nextToken());
			int c = Integer.valueOf(st.nextToken());
			graph.get(a).add(new int[] {b, c});
			graph.get(b).add(new int[] {a, c});
		}
		
		st = new StringTokenizer(br.readLine());
		s = Integer.valueOf(st.nextToken());
		t = Integer.valueOf(st.nextToken());
		
		System.out.println(dijk());
	}

	private static int dijk() {
		dist[s] = 0;
		pq.add(new int[] {0, s});
		while (!pq.isEmpty()) {
			int[] now = pq.poll();
			
			if (fixed[now[1]])
				continue;
			fixed[now[1]] = true;
			if (now[1] == t)
				break;
			// 클래스를 만드는게 나을 뻔 했다.
			for (int[] next : graph.get(now[1])) {
				int nextDist = next[1] + now[0];
				if (nextDist < dist[next[0]]) {
					dist[next[0]] = nextDist;
					pq.add(new int[] {nextDist, next[0]});
				}
			}
		}
		return dist[t];
	}
	
}