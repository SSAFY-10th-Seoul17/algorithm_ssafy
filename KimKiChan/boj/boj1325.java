import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj1325 {
	static boolean[] visited;
	static ArrayList <Integer>[] comp;
	static int[] hacked;
	static int n, m;
	static int max_cnt = 0;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder("");

		// B -> A
		// 단방향 그래프
		// 한번 방문한 장소는 다시 방문하지 않는다, 많이 방문할 수록 좋다
		// 장소 개수 : n, 길 개수 : m
				
		visited = new boolean[n+1];
		hacked = new int[n+1];
		comp = new ArrayList[n+1];
		for(int i = 0; i <= n; i++) {
			comp[i] = new ArrayList<Integer>();
		}
		
		for (int i = 1; i < m+1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			comp[a].add(b); // b는 a를 해킹할 수 잇다
		}
		for(int i = 1; i <= n; i++) {
			Arrays.fill(visited, false);
			dfs(i);
		}

		// 해킹 최대값 구하기
		for(int i = 1; i <= n; i++) {
			if(max_cnt < hacked[i]) {
				max_cnt = hacked[i];
			}
		}
		// 최대값 해킹할 수 있는 컴퓨터
		for(int i = 1; i <= n; i++) {
			if(max_cnt == hacked[i]) {
				sb.append(i).append(" ");
			}
		}
		
		System.out.println(sb.toString());
		
	}
	
	static void dfs(int start) {
		visited[start] = true;
		
		for(int i : comp[start]) {
			if(!visited[i]) {
				hacked[i]++;
				dfs(i);				
			}
		}
		
		
	}
}
