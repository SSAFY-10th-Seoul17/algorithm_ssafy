import java.io.*;
import java.util.*;

public class boj1697 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		// solution : 연결된 숫자를 돌면서 bfs
		int min = Integer.MAX_VALUE;
		// bfs 를 위한 준비물 : 큐, 방문여부확인배열
		Deque<int[]> deque = new ArrayDeque<>();
		boolean[] visited = new boolean[100002];
		// bfs 트리거
		deque.offerLast(new int[] {N, 0});
		visited[N] = true;

		// bfs 시작
		while(!deque.isEmpty()) {
			int[] now = deque.pollFirst();
			if(now[0] == K) {
				min = Math.min(min, now[1]);
			}

			int next;
			for(int i=0; i<3; i++) {
				if(i == 0) {
					next = now[0]+1;
				} else if(i == 1) {
					next = now[0]-1;
				} else {
					next = now[0]*2;
				}
				if(next >= 0 && next < 100002 && !visited[next]) {
					deque.offerLast(new int[] {next, now[1]+1});
					visited[next] = true;
				}
			}
		}

		// output
		bw.write(min+"");
		bw.flush();
		bw.close();
	}
}
