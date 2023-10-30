import java.io.*;
import java.util.*;

// 다익스트라 A,B,C
// HashSet에 친구집 담기
// for문 돌면서 min값 구하기
// min =0이면 건너뛰기 
// town 갱신
public class Main {
	private static int N;
	private static HashSet<Integer> friends;
	private static ArrayList<Node>[] graph;
	private static long[][] dijk;

	private static class Node {
		int node;
		long dist;

		public Node(int node, long dist) {
			super();
			this.node = node;
			this.dist = dist;
		}
	}

	private static PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Long.compare(n1.dist, n2.dist));

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		graph = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			graph[i] = new ArrayList<Node>();
		}
		friends = new HashSet<Integer>();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 3; i++) {
			friends.add(Integer.parseInt(st.nextToken()));
		}
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			long dist = Long.parseLong(st.nextToken());
			graph[d].add(new Node(e, dist));
			graph[e].add(new Node(d, dist));
		}
		dijk = new long[friends.size()][N + 1];
		int inx = 0;
		for (int friend : friends) {
			Arrays.fill(dijk[inx], Long.MAX_VALUE);
			dijk[inx][friend] = 0;
			pq.offer(new Node(friend, 0));
			Dijk(inx++);
		}

		int town = 0;
		long totalMaxD = 0;
		for (int i = 1; i <= N; i++) {
			if (friends.contains(i))
				continue;
			long minD = Long.MAX_VALUE;
			for (int j = 0; j < friends.size(); j++) {
				if (minD > dijk[j][i])
					minD = dijk[j][i];
			}
			if (totalMaxD < minD) {
				totalMaxD = minD;
				town = i;
			}
		}
		System.out.println(town);

	} // end of main

	private static void Dijk(int inx) {
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			if (cur.dist > dijk[inx][cur.node])
				continue;
			for (Node nxt : graph[cur.node]) {
				long d = nxt.dist + dijk[inx][cur.node];
				if (dijk[inx][nxt.node] > d) {
					dijk[inx][nxt.node] = d;
					pq.offer(new Node(nxt.node, d));
				}
			}
		}

	}
} // end of class
