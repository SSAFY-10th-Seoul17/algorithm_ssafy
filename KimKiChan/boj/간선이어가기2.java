import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/** 백준 14284번 */
public class 간선이어가기2 {
	
	public static class Node implements Comparable<Node>{
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

	private static int n;
	private static ArrayList<ArrayList<Node>> graph;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		// 정점의 개수 n
		n = Integer.parseInt(st.nextToken());
		// 간선 리스트의 간선 수 m
		int m = Integer.parseInt(st.nextToken());
		// 무방향 그래프 graph
		graph = new ArrayList<>();
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i = 0; i < m; i++) { // 간선 리스트 입력
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			graph.get(a).add(new Node(b, cost)); // a -> b, cost
			graph.get(b).add(new Node(a, cost)); // b -> a, cost	
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		// 두 개의 정점 s, t
		int s = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
	
		Dikjstra(s, t);
	
	}// end of main

	private static void Dikjstra(int s, int t) {
		boolean[] isVisited = new boolean[n+1];
		
		int[] distance = new int[n+1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[s] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(s, 0));
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			if(isVisited[current.v]) continue;
			isVisited[current.v] = true;
			
			for(Node next : graph.get(current.v)) {
				if(distance[next.v] > distance[current.v] + next.cost) {
					distance[next.v] = distance[current.v] + next.cost;
					pq.offer(new Node(next.v, distance[next.v]));
				}
			}
		}
		
		System.out.println(distance[t]);
		
	}

}// end of class
