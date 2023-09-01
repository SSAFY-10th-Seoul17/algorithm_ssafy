import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj14284 {
	private static ArrayList<Node>[] graph;
	private static int[] distance;

	public static class Node {
		int v;
		int distance;

		public Node(int v, int distance) {
			this.v = v;
			this.distance = distance;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		distance = new int[n + 1];
		graph = new ArrayList[n + 1];

		for (int i = 0; i <= n; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[a].add(new Node(b, c));
			graph[b].add(new Node(a, c));
		}

		st = new StringTokenizer(br.readLine(), " ");
		int s = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());

		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[s] = 0;
		solve(s, t);

		System.out.println(distance[t]);
	}
	
	private static void solve(int s, int t) {
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.distance - o2.distance);
		pq.add(new Node(s, 0));
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			for (Node next : graph[cur.v]) {
				if (distance[next.v] > distance[cur.v] + next.distance) {
					distance[next.v] = distance[cur.v] + next.distance;
					pq.add(new Node(next.v, distance[next.v]));
				}
			}
		}
	}
}
