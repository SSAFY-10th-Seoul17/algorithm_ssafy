// n개인 1 ~ n , 
// 트리 정보, 색 정보, 값 0 :하얀색, 모든 정점을 색으로 칠하고 싶을때 최소 몇번색
import java.util.*;
import java.io.*;

public class BOJ24230_트리색칠하기 {
	static int n;
	static int[] colors;
	static List<ArrayList<Integer>> graph = new ArrayList<>();
	static boolean visited[];
	static int count = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		colors = new int[n];
		visited = new boolean[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i=0; i<n; i++) {
			colors[i] = Integer.parseInt(st.nextToken());
			graph.add(new ArrayList<>());
		}
		
		for (int i=0; i<n-1; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph.get(from-1).add(to-1);
			graph.get(to-1).add(from-1);
		}

		for (int i=0; i<n; i++) {
			dfs(i);
		}
		if (colors[0] != 0) {
		      count++;
		}
		
		System.out.println(count);				
	}
	
	private static void dfs(int idx) {
		Queue<Integer> q = new LinkedList<>();
		q.add(idx); // idx부터 시작
		visited[idx] = true;
		
		while (!q.isEmpty()) {
			int p = q.poll();

			for (int i: graph.get(p)) {
				if (!visited[i]) { // 방문하지 않았으면 
					if (!(colors[p] == colors[i])) { // 색이 다르면 count
							count++;
					}
					q.add(i);
					visited[i] = true;
				}
			}
		}
	}
}
