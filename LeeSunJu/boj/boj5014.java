import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj5014 {
	private static int F, S, G, U, D;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		int result = bfs();
		if (result != -1) {
			System.out.println(result);
		} else {
			System.out.println("use the stairs");
		}
	}

	private static int bfs() {
		Queue<Integer> q = new LinkedList<>();
		boolean[] visited = new boolean[F + 1];
		q.add(S);
		int cnt = -1;
		while (!q.isEmpty()) {
			cnt++;
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int current = q.poll();

				if (current == G) {
					return cnt;
				}

				if (current + U <= F && !visited[current + U]) {
					visited[current + U] = true;
					q.add(current + U);
				}
				if (current - D > 0 && !visited[current - D]) {
					visited[current - D] = true;
					q.add(current - D);
				}
			}
		}
		return -1;
	}
}
