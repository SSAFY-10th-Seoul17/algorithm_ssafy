import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int M;
	private static int X;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		ArrayList<ArrayList<Integer>>up = new ArrayList<>();
		ArrayList<ArrayList<Integer>>down = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			up.add(new ArrayList<Integer>());
			down.add(new ArrayList<Integer>());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			up.get(b).add(a); // b보다 a가 높은 등수
			down.get(a).add(b); 
		}
		int u = search(up)+1;
		int v = N-search(down);
		sb.append(u).append(" ").append(v);
		System.out.println(sb.toString());
	}

	private static int search(ArrayList<ArrayList<Integer>> graph) {
		int cnt = 0;
		boolean[] visited = new boolean[N+1];
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(X);
		visited[X] = true;
		while(!q.isEmpty()) {
			int cur = q.poll();
			ArrayList<Integer> tmp = graph.get(cur);
			for(int nxt:tmp) {
				if(!visited[nxt]) {
					cnt++;
					visited[nxt]=true;
					q.offer(nxt);
				}
			}
		}
		return cnt;
	}
}
