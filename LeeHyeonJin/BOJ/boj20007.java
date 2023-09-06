import java.io.*;
import java.util.*;

/*
 * 가중치 O 그래프 최단거리 => 다익스트라 응용
 * 응용 : 출발점부터 N-1의 집까지의 최단거리를 모두 뽑은 다음, 아래 기준에 따라 일수 측정
 * 메모 :
 * - 거리 왕복 : 각 집까지의 최단거리 * 2가 일수 측정기준
 * - 떡은 한번에 하나씩만 : 각 집의 최단거리 * 2를 누적시켜야
 * - 하루에 X보다 먼 거리를 걷지 X : 각 집의 최단거리 * 2의 누적합이 X를 넘을때 일자 + 1
 * - 모두 방문할 수 없으면 : 가장 먼 거리의 집의 최단거리 * 2가 X보다 크면, 최소 집 1개는 방문할 수 없음
 */
public class boj20007 {
	static int N, M, X, Y;
	static List<List<Position>> graph = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //집의 수
		M = Integer.parseInt(st.nextToken()); //도로 개수
		X = Integer.parseInt(st.nextToken()); //하루에 걸을 수 있는 거리
		Y = Integer.parseInt(st.nextToken()); //성현이의 집

		for(int i=0; i<N; i++) {
			graph.add(new ArrayList<>());
		}
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.get(a).add(new Position(b, c));
			graph.get(b).add(new Position(a, c));
		}

		// solution
		int res = countDays();

		// output
		System.out.println(res);
	}

	static int countDays() {
		int[] distances = dijkstra();
		Arrays.sort(distances);

		if(distances[N-1] == Integer.MAX_VALUE || distances[N-1] * 2 > X) return -1;
		int days = 1, temp = 0;
		for(int distance : distances) {
			if(temp + 2 * distance > X) {
				days++;
				temp = 0;
			}
			temp += 2 * distance;
		}
		return days;
	}

	static int[] dijkstra() {
		PriorityQueue<Position> queue = new PriorityQueue<>();
		int[] distance = new int[N];
		Arrays.fill(distance, Integer.MAX_VALUE);
		queue.offer(new Position(Y, 0));
		distance[Y] = 0;

		int cnt = 0;
		while(!queue.isEmpty()) {
			Position now = queue.poll();
			for(Position next : graph.get(now.idx)) {
				if(distance[next.idx] <= distance[now.idx] + next.dist) continue; //이미방문한곳
				distance[next.idx] = distance[now.idx] + next.dist;
				queue.offer(new Position(next.idx, distance[next.idx]));
			}
		}

		return distance;
	}

	static class Position implements Comparable<Position> {
		int idx, dist;

		public Position(int idx, int dist) {
			this.idx = idx;
			this.dist = dist;
		}

		@Override
		public int compareTo(Position position) {
			return this.dist - position.dist;
		}
	}
}
