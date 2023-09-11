package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//20007번
public class 떡돌리기 {
	private static class Node{
		int next; // 연결되는 곳
		int distance; // 거리
		
		public Node(int next, int distance) {
			super();
			this.next = next;
			this.distance = distance;
		}

	}

	private static int n;
	private static ArrayList<Node>[] graph;
	private static int x;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// n개의 집
		n = Integer.parseInt(st.nextToken());
		// m개의 양방향 도로
		int m = Integer.parseInt(st.nextToken());
		// 최대 거리 x
		x = Integer.parseInt(st.nextToken());
		// 성현이의 집 y
		int y = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[n];
		for(int i = 0; i < n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			
			graph[a].add(new Node(b, dist));
			graph[b].add(new Node(a, dist));

		}
		
		// 다익스트라
		dijkstra(y);
		
	}

	private static void dijkstra(int y) {
		int[] dist = new int[n];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[y] = 0;
		
		boolean[] isVisited = new boolean[n];
		
		PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.distance, o2.distance));
		pq.offer(new Node(y, 0));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			int house = now.next;
			if(isVisited[house]) continue;
			isVisited[house] = true;
			
			for(Node nextHouse : graph[house]) {
				if(dist[nextHouse.next] > dist[house] + nextHouse.distance) {
					dist[nextHouse.next] = dist[house] + nextHouse.distance;
					pq.offer(new Node(nextHouse.next, dist[nextHouse.next]));
				}
			}

		}// end of while
		
//		System.out.println(Arrays.toString(dist));
		
		Arrays.sort(dist);
		
		// 본인을 제외한 집으로 감
		int cnt = 1;
		int limit = 0;
		boolean possible = true;
		for(int i = 1; i < n; i++) {
			if(dist[i]*2 <= x) {
				if(limit + dist[i]*2 <= x) {
					limit += dist[i]*2;
				}else {
					cnt++;
					i--;
					limit = 0;
				}
			}else {
				possible = false;
				break;
			}
		}
		
		if(possible) {
			System.out.println(cnt);
		}else {
			System.out.println(-1);
		}
		
	}// end of dijkstra
}
