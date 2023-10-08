import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		move(N,M,A,B);
	} // end of main

	private static void move(int src, int dest, int A, int B) {
		int[] dir = {1,-1,A,-A,B,-B, A, B};
		boolean[] visited = new boolean[100001];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {src,0});
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			if(visited[cur[0]]) continue;
			if(cur[0]==dest) {
				System.out.println(cur[1]);
				return;
			}
			visited[cur[0]] = true;
			for (int i = 0; i < 8; i++) {
					int nxt = cur[0];
					if(i<6) {
						nxt+=dir[i];
					}else {
						nxt*=dir[i];
					}
					if(nxt>100000 || nxt < 0 || visited[nxt]) continue;
					queue.offer(new int[] {nxt,cur[1]+1});
				}
			}
		}
} // end of class
