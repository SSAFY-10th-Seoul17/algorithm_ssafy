import java.util.*;
import java.io.*;

public class BOJ1325_효율적인해킹 {
	static boolean[] visited;
	static int max = Integer.MIN_VALUE;
	static int n,m;
	static int count;
	static List<ArrayList<Integer>> graph = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		// n개의 컴퓨터, 
		// 신뢰하면 같이 해킹 가능
		// 한번에 가장 많은 컴퓨터 해킹할 수 있는 번호 출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		for(int i=0; i<=n; i++) {
			graph.add(new ArrayList<>());
		}
		
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			graph.get(from).add(to);
		}
		
		List<Integer> indexs = new ArrayList<>();
		for (int i=1; i<=n; i++) {
			int c = bfs(i);
			if (c > max) {
				max = c;
				indexs = new ArrayList<>();
				indexs.add(i);
			}
			else if(c == max) {
				indexs.add(i);
			}
		}
		
		for(int idx : indexs) {
			sb.append(idx+" ");
		}
		System.out.println(sb.toString());
	}
	
	private static int bfs(int start) {
		count = 0;
		visited = new boolean[n+1];
		
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int v = q.poll();
			for (int i : graph.get(v)) {
				if (!visited[i]) {
					q.add(i);
					visited[i] = true;
					count++;
				}
			}
		}
		return count;
	}
}
