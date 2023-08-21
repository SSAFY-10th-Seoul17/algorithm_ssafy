import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**백준 15971번*/
public class 두로봇 {
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
	private static int robot0;
	private static int robot1;
	private static int n;
	private static boolean[] isVisited;
	private static ArrayList<Integer> order;
	private static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =new StringTokenizer(br.readLine(), " ");
		// 동굴 방의 개수 n
		n = Integer.parseInt(st.nextToken());
		// 두 로봇의 시작점
		robot0 = Integer.parseInt(st.nextToken());
		robot1 = Integer.parseInt(st.nextToken());
		// 양방향 그래프 graph
		graph = new ArrayList<>();
		for(int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}
		// 입력
		for(int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());

			graph.get(a).add(new Node(b, cost)); // a -> b, cost
			graph.get(b).add(new Node(a, cost)); // b -> a, cost
		} // 입력 끝
		
		// robot1 -> robot2로 가는 모든 경우의 거리 구하고 최솟값 찾기
		isVisited = new boolean[n+1];
		order = new ArrayList<>();
		order.add(robot0);
		dfs(robot0, 0);

		if(n == 1 || robot0 == robot1) result = 0;
		System.out.println(result);
		
	}
	

	static void dfs(int vertex, int dist) {
		if(vertex == robot1) {
			int minDist = Integer.MAX_VALUE;
			for(int i = 0; i < order.size()-1; i++) {
				int v1 = order.get(i);
				int v2 = order.get(i+1);
				int tempDist = 0;
				for(Node tempNode : graph.get(v1)) {
					if(tempNode.v == v2) {
						tempDist = tempNode.cost;
						break;
					}
				}
				if(minDist > dist - tempDist) {
					minDist = dist - tempDist;
					if(result > minDist) {
						result = minDist;
					}
				}
			}
			return;
		}
		for(Node next : graph.get(vertex)) {
			if(isVisited[next.v]) continue;
			isVisited[next.v] = true;
			order.add(next.v);
			dfs(next.v, dist + next.cost);
			isVisited[next.v] = false;
			order.remove(order.size()-1);
		}
		
	}

	
	
}
