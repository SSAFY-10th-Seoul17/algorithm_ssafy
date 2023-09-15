import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj18243 {
	static int[][] graph;
	static int[] visited;
	private static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		graph = new int[N+1][N+1];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			graph[A][B] = graph[B][A] = 1;
		}
		boolean flag = true;
		fofo:
		for (int j = 1; j <= N; j++) {
			visited = new int[N+1];
			for (int i = 0; i <= N; i++) {
				visited[i] = -1;
			}
			bfs(j);
			flag = true;
			for (int i = 1; i <= N; i++) {
				if (visited[i] == -1 || visited[i] > 6) {
					flag = false;
					break fofo;
				}
			}
		}
		if (flag) {
			System.out.println("Small World!");
		} else {
			System.out.println("Big World!");
		}
	}
	
	public static void bfs(int j) {
		Queue<Integer> q = new LinkedList<>();
		visited[j] = 0;
		q.offer(j);
		while (!q.isEmpty()) {
			int now = q.poll();
			for (int i = 1; i <= N; i++) {
				if (graph[now][i] == 1 && visited[i] == -1) {
					visited[i] = visited[now] + 1;
					q.offer(i);
				}
			}
		}
	}

}
