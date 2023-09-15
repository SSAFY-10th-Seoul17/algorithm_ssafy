import java.io.*;
import java.util.*;

public class boj14284 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		List<List<Node>> graph = new ArrayList<>();
		for(int i=0; i<=n; i++) graph.add(new ArrayList<>());

		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int wei = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Node(b, wei));
			graph.get(b).add(new Node(a, wei));
		}

		st = new StringTokenizer(br.readLine(), " ");
		int s = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());

		// solution
		int minDist = getShortestDist(n, graph, s, t);

		// output
		System.out.println(minDist);
	}

	/* 아이디어 : 우선순위큐 방식을 활용한 다익스트라 알고리즘을 통해 인접그래프의 최단경로를 구하려고 함 */
	static int getShortestDist(int n, List<List<Node>> graph, int s, int t) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		int[] dist = new int[n+1]; // 출발지 ~ 인덱스노드 까지의 최단거리를 저장하기 위한 배열
		Arrays.fill(dist, Integer.MAX_VALUE);
		queue.offer(new Node(s, 0));
		dist[s] = 0;

		while(!queue.isEmpty()) {
			Node now = queue.poll();
			if(dist[now.num] < now.wei) continue; // 방문여부체크
			for(Node next : graph.get(now.num)) {
				if(dist[next.num] > dist[now.num]+next.wei) { // 다음노드 누적최단거리 & 현재누적 가중치+다음노드 가중치 비교,갱신
					dist[next.num] = dist[now.num] + next.wei;
					queue.offer(new Node(next.num, dist[next.num]));
				}
			}
		}
		return dist[t];
	}
}

/* 개선된 다익스트라 알고리즘을 위해 노드 클래스에 Comparable을 상속받아 우선순위큐를 통해 최단경로 노드부터 추출 */
class Node implements Comparable<Node> {
	int num, wei;

	public Node(int num, int wei) {
		this.num = num;
		this.wei = wei;
	}

	@Override
	public int compareTo(Node node) {
		return this.wei - node.wei;
	}
}
