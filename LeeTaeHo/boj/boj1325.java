import java.io.*;
import java.util.*;

public class boj1325 {
	static List<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
	static boolean[] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		// 방문 여부
		visited = new boolean[n+1];
		// 인접리스트 1차원 리스트 초기화
		for(int i = 0; i <= n; i++) {
			list.add(new ArrayList<Integer>());
		}
		
		// 인접리스트 2차원 리스트 초기화
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			list.get(to).add(from);
		}
		// 각 노드 시작으로 방문할 수 있는 횟수를 담을 배열
		int[] result = new int[n+1];
		// 방문하는 횟수의 최댓값
		int max = 0;
		for(int i = 1; i <= n; i++) {
			result[i] = bfs(i);
			// 다음 bfs() 시작 전 방문 여부 배열 false 초기화
			visited = new boolean[n+1];
			max = Math.max(max, result[i]);
		}
		for(int i = 1; i <= n; i++) {
			if(result[i] == max) {
				sb.append(i).append(" ");
			}
		}
		System.out.println(sb.toString());
	}
	public static int bfs(int start) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(start);
		visited[start] = true;
		int cnt = 0;
		while(!q.isEmpty()) {
			int n = q.poll();
			cnt++;
			for(int i : list.get(n)) {
				if(!visited[i]) {
					q.add(i);
					visited[i] = true;
				}
			}
		}
		return cnt;
	}
}
