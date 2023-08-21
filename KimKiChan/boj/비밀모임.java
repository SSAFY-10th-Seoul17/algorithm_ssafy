import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


/** 백준 13424번 */
public class 비밀모임 {
	
	private static class Node implements Comparable<Node>{
		int v;
		int cost;
		
		public Node(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}

	}
	
	private static ArrayList<ArrayList<Node>> graph;
	private static int[] roomDist;
	private static int n;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// 테스트 케이스 test개
		int test = Integer.parseInt(br.readLine());
		for(int t = 1; t <= test; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			// n개의 방
			n = Integer.parseInt(st.nextToken());
			// m개의 비밀 통로, 양방향, 자연수
			int m = Integer.parseInt(st.nextToken());
			// 양방향 그래프 graph
			graph = new ArrayList<>();
			for(int i = 0; i <= n; i++) {
				graph.add(new ArrayList<>());
			}
			
			for(int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				
				graph.get(a).add(new Node(b, cost)); // a -> b, cost
				graph.get(b).add(new Node(a, cost)); // b -> a, cost
				
			}

			// 모임에 참여하는 친구들 k명
			int k = Integer.parseInt(br.readLine());
			int[] friends = new int[k];
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < k; i++) { // 친구들의 현재 위치하는 방 번호
				friends[i] = Integer.parseInt(st.nextToken());
			}
			
			roomDist = new int[n+1]; // 다른 방까지의 거리
			
			for(int i = 0; i < k; i++) { // 친구별 각 방까지의 최소 거리
				Dikjstra(i, friends[i]);
			}
			
			int minDist = Integer.MAX_VALUE;
			int room = 0;
			for(int i = 1; i <= n; i++) { // 작은 방 번호부터
				int dist = roomDist[i];
				if(minDist > dist) { // 최소 거리의 방 번호
					minDist = dist;
					room = i;
				}
			}
			// 방번호 출력
			sb.append(room).append("\n");
			
		}// end of test case
		
		System.out.println(sb.toString());
		
	}

	private static void Dikjstra(int friend, int start) {
		boolean[] isVisited = new boolean [n+1];
		
		int[] distance = new int[n+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node room = pq.poll();
			
			if(isVisited[room.v]) continue;
			isVisited[room.v] = true;
			
			for(Node nextRoom : graph.get(room.v)) {
				if(distance[nextRoom.v] > distance[room.v] + nextRoom.cost) {
					distance[nextRoom.v] = distance[room.v] + nextRoom.cost;
					pq.offer(new Node(nextRoom.v, distance[nextRoom.v]));
				}
			}
		} // end of while
		
		for(int i = 1; i <= n; i++) { 
			roomDist[i] += distance[i];
		}
		
	}// end of Dikjstra
	
}
