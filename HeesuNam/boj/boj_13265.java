import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int m;
	private static int n;
	private static ArrayList<ArrayList<Integer>> graph;
	private static int[] colors;
	private static boolean[] visited;
	private static boolean isPoissible;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			graph = new ArrayList<>();
			for (int j = 0; j <= n; j++) {
				graph.add(new ArrayList<>());
			}
			for (int j = 0; j < m; j++) {
				st = new StringTokenizer(br.readLine()," ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				graph.get(x).add(y);
				graph.get(y).add(x);
			}
			colors = new int[n+1];
			visited = new boolean[n+1];
			isPoissible = true;
			for (int j = 1; j <= n; j++) {
				if(!isPoissible) break;
				if(visited[j]) continue;
				chkColor(j,2);
			}
			sb.append(isPoissible?"possible\n": "impossible\n");
		}
		System.out.println(sb.toString());
	} // end of main

	private static void chkColor(int start, int color) {
		Queue<int[]> queue = new ArrayDeque<>();
		colors[start] = color;
		visited[start]=true;
		queue.offer(new int[] {start,color});
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			int nxtColor = 3-cur[1]; 
			for(int nxt:graph.get(cur[0])) {
				if(colors[nxt]!=0 && colors[nxt]!=nxtColor) {
					isPoissible = false;
					return;
				}
				if(visited[nxt]) continue;
				if(colors[nxt]==0) colors[nxt] = nxtColor;
				visited[nxt] = true;
				queue.offer(new int[] {nxt,nxtColor});
			}
		}
	}
} // end of class
