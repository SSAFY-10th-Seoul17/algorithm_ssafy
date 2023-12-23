import java.util.*;
import java.io.*;

public class Main {
	private static int N, M, S;
	private static ArrayList<Integer>[] graph;
	private static boolean[] fanclub;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
		}
		S = Integer.parseInt(br.readLine());
		fanclub = new boolean[N+1];
		st = new StringTokenizer(br.readLine()," ");
		for (int i = 0; i < S; i++) {
			fanclub[Integer.parseInt(st.nextToken())] = true;
		}
		System.out.println(meetGomGom()?"Yes":"yes");
	} // end of main

	private static boolean meetGomGom() {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(1);
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			if(fanclub[cur]) continue;
			if(graph[cur].size()==0)return false;
			for(int nxt: graph[cur]) {
				if(fanclub[nxt])continue;
				queue.add(nxt);
			}
		}
		return true;
	}
}// end of class
