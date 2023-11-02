package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//22865번
public class 가장먼곳 {
	private static int n;
	private static ArrayList<ArrayList<Node>> dist;
	private static int[] minDist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//땅 후보의 개수
		n = Integer.parseInt(br.readLine());
		// A, B, C가 사는 위치
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] friend = new int[3];
		for(int i = 0; i < 3; i++) {
			friend[i] = Integer.parseInt(st.nextToken());
		}
		//도로의 개수
		int m = Integer.parseInt(br.readLine());
		
		dist = new ArrayList<>();
		for(int i = 0; i <= n; i++) {
			dist.add(new ArrayList<>());
		}
		
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());
			dist.get(from).add(new Node(to, len));
			dist.get(to).add(new Node(from, len));
		}
		
		minDist = new int[n+1];
		Arrays.fill(minDist, Integer.MAX_VALUE);
		
		//다익스트라
		for(int friendHouse : friend) {
			Dikjkstra(friendHouse);
		}
		
		int maxDist = 0;
		int newHome = 0;
		for(int i = 1; i <= n; i++) {
			if(maxDist < minDist[i]) {
				maxDist = minDist[i];
				newHome = i;
			}
		}
		System.out.println(newHome);
	}

	private static void Dikjkstra(int start) {
		int[] houseDist = new int[n+1];
		Arrays.fill(houseDist, Integer.MAX_VALUE);
		houseDist[start] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>((Node a, Node b) -> a.dist >= b.dist ? 1:-1);
		pq.offer(new Node(start, 0));
		
		while(!pq.isEmpty()) {
			Node curNode = pq.poll();
			if(houseDist[curNode.house] < curNode.dist) {
				continue;
			}
			for(Node nextHouse : dist.get(curNode.house)) {
				if(houseDist[nextHouse.house] > curNode.dist + nextHouse.dist) {
					houseDist[nextHouse.house] = curNode.dist + nextHouse.dist;
					pq.add(new Node(nextHouse.house, houseDist[nextHouse.house]));
				}
				
			}
			
		}
		
		for(int i = 1; i <= n; i++) {
			if(minDist[i] > houseDist[i]) {
				minDist[i] = houseDist[i];
			}
		}
		
	}
	
	private static class Node{
		int house;
		int dist;
		public Node(int house, int dist) {
			super();
			this.house = house;
			this.dist = dist;
		}
		
	}
}
