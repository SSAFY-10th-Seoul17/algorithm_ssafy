import java.io.*;
import java.util.*;
// 06:55 - 

// 가중치가 있는 최단 거리 구하기는 다익스트라를 이용하는 것임을 인지하고 있었지만 자바로 구현해본적이 없어서 블로그를 참고하여 풀었습니다.
// 최소비용을 기준으로 추출해야하므로 priorityqueue를 사용했습니다. 
// 자바로 구현하는 연습이 필요할 것같습니다ㅎㅎ

public class BOJ14284_간선이어가기2 {
	static int N,M,s,t;
	static List<ArrayList<Node>> graph = new ArrayList<>();
	static int[] dist;
	static class Node{
		int idx, cost;
		
		Node(int idx, int cost){
			this.idx = idx;
			this.cost = cost;
		}
		
		@Override
		public String toString() {
			return idx+" "+cost;
		}
	}
	
	public static void main(String[] args) throws Exception {
		// 정점 n개 , 0개의 간선, m개의 가중치 간선정보가 있는 간선리스트
		// 정점 s t 연결되는 시점에 간선 추가 멈춤
		// s t가 연결되는 시점의 간선의 가중치 합이 최소가 되게 추가
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<N+1; i++) {
			graph.add(new ArrayList<Node>()); // N개만큼 생성
		}
		
		for (int j=0; j<M; j++) {
			st = new StringTokenizer(br.readLine());
			int from  = Integer.parseInt(st.nextToken());
			int to  = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(new Node(to,val));
			graph.get(to).add(new Node(from,val));
			
		}
		
		dist = new int[N+1]; // 최소 비용 저장할 배열
		for (int i=0; i<N+1; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		
		st = new StringTokenizer(br.readLine());
		s = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		
		dijkstra(s);
		System.out.println(dist[t]);
	}
	
	private static void dijkstra(int start) {
		// 최소 비용
		PriorityQueue<Node> q = new PriorityQueue<Node>((o1, o2)-> Integer.compare(o1.cost, o2.cost));
		q.offer(new Node(start, 0));
		
		dist[start] = 0;
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			if (dist[cur.idx] < cur.cost) { // 거리가 더 크면 안간다
				continue;
			}
			
			for (int i=0; i<graph.get(cur.idx).size(); i++) {
				Node next = graph.get(cur.idx).get(i);
				
				if (dist[next.idx] > cur.cost+next.cost) {
					dist[next.idx] = cur.cost+next.cost;
					q.offer(new Node(next.idx, dist[next.idx]));
				}
			}
		}
	}
}
