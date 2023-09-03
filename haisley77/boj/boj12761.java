import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class boj12761 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] visited = new int[100001];
		int[] dirs = {-1,1,A,B,-A,-B,A,B};
		ArrayDeque<Integer> q = new ArrayDeque<>();
		visited[N] = 1;
		q.offerLast(N);
		
		while (!q.isEmpty()) {
			int now = q.pollFirst();
			
			for (int i = 0; i < 6; i++) {
				int next = now + dirs[i];
				if (next < 0 || next > 100000 || visited[next] != 0) continue;
				visited[next] = visited[now] + 1;
				q.offerLast(next);		
			}
			for (int i = 6; i < 8; i++) {
				int next = now * dirs[i];
				if (next < 0 || next > 100000 || visited[next] != 0) continue;
				visited[next] = visited[now] + 1;
				q.offerLast(next);
			}
		}
		System.out.println(visited[M]-1);
	}

}
