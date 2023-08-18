import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int n, m, cnt;
	static List<ArrayList<Integer>> friends;
	static int[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());  // 상근이 동기 수
		m = Integer.parseInt(br.readLine());  // 리스트 길이
		
		// 친구 관계 저장
		friends = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			friends.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			friends.get(v1).add(v2);
			friends.get(v2).add(v1);
		}
		
		visited = new int[n+1];  // 방문 및 이동 깊이 저장
		bfs();
		System.out.println(cnt);
		
	}
	
	public static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(1);
		visited[1] = 1;
		while (!q.isEmpty()) {
			int w = q.poll();
			if (visited[w] == 3) return;
			for (int v : friends.get(w)) {
				if (visited[v] == 0) {
					visited[v] = visited[w] + 1;
					q.offer(v);
					cnt++;
				}
			}
		}
	}
}
