import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1325 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static Queue<Integer> q;
	static int[] visited;
	
	static int cnt;
	
	static int n;
	static int m;

	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();

	static void bfs(int node, int n) {
		cnt = 0;
		q = new LinkedList<Integer>();
		q.add(node);

		visited = new int[n + 1];
		visited[node] = 1;

		while (!q.isEmpty()) {
			int now = q.poll();
			cnt += 1;
			for (int next : graph.get(now)) {
				if (visited[next] == 1) continue;
				visited[next] = 0;
				q.add(next);
			}
		}
	}
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		st = new StringTokenizer(br.readLine());
		n = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());
		
		for(int i = 0; i < n + 1; i++) {
			graph.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.valueOf(st.nextToken());
			int b = Integer.valueOf(st.nextToken());
			
			graph.get(b).add(a);
		}
		
		int amount = -1;
		ArrayList<Integer> comArr = new ArrayList<Integer>();
		for (int i = 1; i < n + 1; i++) {
			bfs(i, n);
			if (cnt > amount) {
				comArr.clear();
				comArr.add(i);
				amount = cnt;
			}
			else if (cnt == amount) {
				comArr.add(i);
			}
		}
		for (int c : comArr) {
			sb.append(c + " ");
		}
		System.out.println(sb);
	}

}
