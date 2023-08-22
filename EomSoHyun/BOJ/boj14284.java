import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int n, m, s, t;
	static List<List<int[]>> graph;
	static PriorityQueue<int[]> pq;
	static int[] dist;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());  // 정점 개수
		m = Integer.parseInt(st.nextToken());  // 정점 개수
		
		graph = new ArrayList<>();  // {정점, 가중치}
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<int[]>());
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());  // 정점 1
			int v2 = Integer.parseInt(st.nextToken());  // 정점 2
			int w = Integer.parseInt(st.nextToken());  // 가중치
			graph.get(v1).add(new int[] {v2, w});
			graph.get(v2).add(new int[] {v1, w});
		}
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());  // 시작 정점
		t = Integer.parseInt(st.nextToken());  // 목적지 점점
		
		
		// s로부터 거리 초기화
		dist = new int[n+1];
		for (int i = 1; i <= n; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[s] = 0;
		
		dijkstra();
		System.out.println(dist[t]);
	}
	
	public static void dijkstra() {
		// 탐색 노드 저장 -> 가중치가 작은 것이 앞에 오도록
		pq = new PriorityQueue<>(new Comparator<int[]>() {
		@Override
		public int compare(int[] o1, int[] o2) {
			int w1 = o1[1];
			int w2 = o2[1];
			return Integer.compare(w1, w2);
		}
		});  
		
		pq.add(new int[] {s, 0});
		while (!pq.isEmpty()) {
			int[] v = pq.poll();  // {인덱스, 가중치}
			for (int[] w : graph.get(v[0])) {
				if (dist[v[0]] + w[1] < dist[w[0]]) {  // 기존 값보다 거쳐가는 것이 더 작을 경우
					dist[w[0]] = dist[v[0]] + w[1];
					pq.add(new int[] {w[0], dist[w[0]]});
				}
				
			}
		}
	}

}
