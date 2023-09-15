import java.io.*;
import java.util.*;

public class boj5567 {
	static int N,M;
	static Node[] graph;
	static boolean[] V;
	static class Node {
		int id;
		Node next;
		Node(int id, Node next){
			this.id = id;
			this.next = next;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		graph = new Node[N+1];
		V = new boolean[N+1];
		
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a] = new Node(b,graph[a]);
			graph[b] = new Node(a,graph[b]);
		}
		
		V[1] = true;
		dfs(0,1);
		int res = 0;
		for (int i = 2; i < N+1; i++) {
			if (V[i]) res++;
		}
		System.out.println(res);
	}
	
	private static void dfs(int cnt, int id) {
		if (cnt == 2) return;
		
		Node p = graph[id];
		while (p != null) {
			V[p.id] = true;
			dfs(cnt+1,p.id);
			p = p.next;
		}
		
	}
}
