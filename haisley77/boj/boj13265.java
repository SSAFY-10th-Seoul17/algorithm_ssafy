import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class boj13265 {
	static int[] visited;
	static Node[] graph;
	static boolean flag;
	static int n, m;
	static class Node {
		int v;
		Node next;
		Node(int v, Node next){
			this.v = v;
			this.next = next;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T ; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			graph = new Node[n+1];
			visited = new int[n+1];
			flag = true;
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine()," ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				graph[x] = new Node(y, graph[x]);
				graph[y] = new Node(x, graph[y]);
			}
			for (int i = 1; i <= n; i++) {
				if (visited[i] == 0) {
					bfs(i);
					if (!flag) break;
				}
			}		
			if (flag) {
				sb.append("possible\n");
			} else {
				sb.append("impossible\n");
			}
		}
		System.out.println(sb.toString());
	}

	private static void bfs(int s) {
		ArrayDeque<Integer> q = new ArrayDeque<>();
		q.offerLast(s);
		visited[s] = 1;
		while (!q.isEmpty()) {
			int cur = q.pollFirst();	
			for (Node temp = graph[cur]; temp != null; temp = temp.next) {
				if (visited[temp.v] == 0) {
					visited[temp.v] = 3 - visited[cur];
					q.offerLast(temp.v);
				} else {
					if (visited[temp.v] == visited[cur]) {
						flag = false;
						return;
					}
				}
			}
		}
	}
}