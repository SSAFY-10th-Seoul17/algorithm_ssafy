import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj5567 {
	static int n, m;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.valueOf(br.readLine());
		for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
		visited = new boolean[n+1];

		m = Integer.valueOf(br.readLine());
		
		int a, b;
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.valueOf(st.nextToken());
			b = Integer.valueOf(st.nextToken());
			
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
		
		System.out.println(bfs());
		
	}

	private static int bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		Queue<Integer> d = new LinkedList<Integer>();
		q.add(1);
		d.add(0);
		visited[1] = true;
		int cnt = 0;
		while (!q.isEmpty()) {
			int now = q.poll();
			int dist = d.poll();
			if (dist == 2)
				continue;
			else
				dist += 1;
			
			for (int next : graph.get(now)) {
				if (visited[next])
					continue;
//				System.out.println("invite : " + next);
				cnt += 1;
				visited[next] = true;
				q.add(next);
				d.add(dist);
			}
		}
		return cnt;
	}
}
