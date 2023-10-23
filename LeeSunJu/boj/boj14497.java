import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj14497 {
	static char[][] map;
	static int N, M;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		int x1 = Integer.parseInt(st.nextToken()) - 1;
		int y1 = Integer.parseInt(st.nextToken()) - 1;
		int x2 = Integer.parseInt(st.nextToken()) - 1;
		int y2 = Integer.parseInt(st.nextToken()) - 1;

		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		int[][] dir =  {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
		pq.add(new int[]{x1, y1, 0});
		boolean[][] checked = new boolean[N + 1][M + 1];
		checked[x1][y1] = true;
		int result = 0;
		while (!pq.isEmpty()) {
			int[] tmp = pq.poll();

			if (tmp[0] == x2 && tmp[1] == y2) {
				result = tmp[2];
				break;
			}

			for (int i = 0; i < dir.length; i++) {
				int nx = tmp[0] + dir[i][0];
				int ny = tmp[1] + dir[i][1];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M || checked[nx][ny]) {
					continue;
				}

				int cnt = map[nx][ny] == '0' ? tmp[2] : tmp[2] + 1;
				checked[nx][ny] = true;
				pq.add(new int[]{nx, ny, cnt});
			}
		}

		System.out.println(result);
	}
}
