package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

//14567번
public class 선수과목 {

	private static int n;
	private static int[] degree;
	private static ArrayList<Integer>[] graph;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		//과목의 수 n
		n = Integer.parseInt(st.nextToken());
		//선수 조건의 수 m
		int m = Integer.parseInt(st.nextToken());
		//진입차수
		degree = new int[n+1];
		//그래프
		graph = new ArrayList[n+1];
		for(int i = 0; i <= n; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			degree[b]++;
		}
		
		topologySort();
		
	}

	private static void topologySort() {
		ArrayList<Integer> sorted = new ArrayList<>();
		Deque<Integer> dq = new ArrayDeque<>();
		int[] semester = new int[n+1];
		Arrays.fill(semester, 1);
		
		for(int i = 1; i <= n; i++) {
			if(degree[i] == 0) dq.offer(i);
		}
		
		while(!dq.isEmpty()) {
			int now = dq.poll();
			sorted.add(now);
			for(int next : graph[now]) {
				degree[next]-=1;
				semester[next]=semester[now]+1;
				if(degree[next] == 0) {
					dq.offer(next);
				}
			}
		}// empty dq
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= n; i++) {
			sb.append(semester[i]).append(" ");
		}
		System.out.println(sb);
		
	} // end of topology sort

}// end of class
