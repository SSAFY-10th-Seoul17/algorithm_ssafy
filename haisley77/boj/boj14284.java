import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj14284 {
	static int[] cost;
	static boolean[] visited;
	private static int n, m, res;
	private static int[][] A;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		A = new int[n+1][n+1];
		cost = new int[n+1];
		visited = new boolean[n+1];
		for (int i = 0 ; i < m ; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			A[a][b] = A[b][a] = c;
		}
		st = new StringTokenizer(br.readLine()," ");
		int s = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		cost[s] = 0;
		Dijkstra(s,t);
		System.out.println(cost[t]);
	}
	private static void Dijkstra(int s, int t) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1,o2) -> Integer.compare(o1[0], o2[0]));
		pq.offer(new int[] {0,s});
		int v = s;
		while (v != t) {
			int[] info = pq.poll();
			v = info[1];
			cost[v] = info[0];
			visited[v] = true;
			
			for (int i = 1; i <= n; i++) {
				if (A[v][i] != 0 && !visited[i]) {
					pq.offer(new int[] {cost[v] + A[v][i],i});
				}
			}
		}
	}

}
