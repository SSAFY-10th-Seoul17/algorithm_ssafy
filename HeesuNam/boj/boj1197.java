import java.io.*;
import java.util.*;

public class Main {
	private static int V, E;
	private static ArrayList<Node>[] graph;
	private static PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.cost, n2.cost));

	static class Node {
		int num;
		int cost;

		public Node(int num, int cost) {
			super();
			this.num = num;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			graph[A].add(new Node(B, C));
			graph[B].add(new Node(A, C));
		}
		prim();

	} // end of main

	private static void prim() {
		boolean[] visited = new boolean[V + 1];
		pq.add(new Node(1, 0));
		int total = 0;
		while (!pq.isEmpty() && V > 0) {
			Node cur = pq.poll();
			if (visited[cur.num])
				continue;
			visited[cur.num] = true;
			V--;
			total += cur.cost;
			for (Node nxt : graph[cur.num]) {
				if (!visited[nxt.num]) {
					pq.offer(nxt);
				}
			}
		}
		System.out.println(total);
	}
} // end of class
