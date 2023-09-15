import java.io.*;
import java.util.*;

public class boj20007 {

	static class Node implements Comparable<Node>{
		int to;
		int weight;

		public Node(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken()); // 집 개수
		int M = Integer.parseInt(st.nextToken()); // 도로 개수
		int X = Integer.parseInt(st.nextToken()); // 하루에 걸을 수 있는 거리
		int Y = Integer.parseInt(st.nextToken()); // 성현의 집

		ArrayList<ArrayList<Node>> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			list.add(new ArrayList<>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			list.get(a).add(new Node(b, c));
			list.get(b).add(new Node(a, c));
		}

		int[] distance = new int[N];
		Arrays.fill(distance, Integer.MAX_VALUE);

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(Y, 0));
		distance[Y] = 0;
		boolean[] visited = new boolean[N];
		while(!pq.isEmpty()) {
			Node cur = pq.poll();

			for (Node next : list.get(cur.to)) {
				if (!visited[next.to] && distance[next.to] > distance[cur.to] + next.weight) {
					distance[next.to] = distance[cur.to] + next.weight;
					pq.add(new Node(next.to, distance[next.to]));
				}
			}

			visited[cur.to] = true;
		}

		int total = 0;
		int days = 1;
		Arrays.sort(distance);
		for (int i = 0; i < distance.length; i++) {
			if (distance[i] * 2 > X) { // 모두 방문할 수 없으면
				days = -1;
				break;
			}

			total += distance[i];
			if (total * 2 > X) {
				days++;
				total = distance[i];
			}
		}

		System.out.println(days);
	}
}
