import java.io.*;
import java.util.*;

// 아이디어 :
// 1. "다익스트라"를 통해 각각의 후보땅에 대한 가장 가까운 거리 구한다.
// 2. 전체 가장 가까운 거리는 더 큰 가까운 거리값이 나오면 갱신된다.
public class boj22865_가장먼곳 {
	static int N, M;
	static int[] friends = new int[3];
	static List<List<Road>> roads = new ArrayList<>();
	static final int INF = Integer.MAX_VALUE;
	static int[] distance;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<3; i++) friends[i] = Integer.parseInt(st.nextToken());

		M = Integer.parseInt(br.readLine());
		for(int i=0; i<=N; i++) roads.add(new ArrayList<>());
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());
			roads.get(a).add(new Road(b, len));
			roads.get(b).add(new Road(a, len));
		}

		// solution
		distance = new int[N+1];
		Arrays.fill(distance, INF);
		for(int i=0; i<3; i++) {
			DIJKSTRA(friends[i]); // 친구들(A,B,C)집 -> N
		}

		// 친구들이 살고 있는 집으로부터 가장 먼 곳 구하기
		int res = 0, min = -INF;
		for(int i=1; i<=N; i++) {
			if(min < distance[i]) {
				min = distance[i];
				res = i;
			}
		}

		// output
		System.out.println(res);
	}

	static void DIJKSTRA(int start) {
		PriorityQueue<Road> queue = new PriorityQueue<>();
		queue.offer(new Road(start, 0));
		int[] temp = new int[N+1];
		Arrays.fill(temp, INF);
		temp[start] = 0;

		while(!queue.isEmpty()) {
			Road now = queue.poll();
			// 방문처리
			if(now.cost > temp[now.destination]) continue;
			for(Road next : roads.get(now.destination)) {
				// 최단거리 갱신 & 다음목적지도 살펴보기 위해 큐에 추가
				if(temp[next.destination] > temp[now.destination]+next.cost) {
					temp[next.destination] = temp[now.destination]+next.cost;
					queue.offer(new Road(next.destination, temp[next.destination]));
				}
			}
		}

		// 출발지(A,B,C) 중 하나 -> N 위치의 모든 집들에 대한 최단거리 저장
		for(int i=1; i<=N; i++) {
			if(distance[i] > temp[i]) distance[i] = temp[i];
		}
	}

	static class Road implements Comparable<Road> {
		int destination, cost;

		public Road(int destination, int cost) {
			this.destination = destination;
			this.cost = cost;
		}

		@Override
		public int compareTo(Road road) {
			return Integer.compare(this.cost, road.cost);
		}
	}
}
