import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj3184 {

	private static int R, C;
	private static char[][] map;
	private static int sCnt, wCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		int sheepCnt = 0;
		int wolfCnt = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int tmp[] = dfs(i, j);
				sheepCnt += tmp[0];
				wolfCnt += tmp[1];
				sCnt = 0;
				wCnt = 0;
			}
		}

		System.out.println(sheepCnt + " " + wolfCnt);
	}

	private static int[] dfs(int x, int y) {
		if (x < 0 || x >= R || y < 0 || y >= C) {
			return new int[]{0, 0};
		}

		if (map[x][y] != '#') {
			if (map[x][y] == 'v') {
				wCnt++;
			} else if (map[x][y] == 'o') {
				sCnt++;
			}
			map[x][y] = '#';
			dfs(x - 1, y);
			dfs(x + 1, y);
			dfs(x, y - 1);
			dfs(x, y + 1);

			if (wCnt >= sCnt) { // 늑대가 양보다 많을 경우
				return new int[]{0, wCnt};
			} else { // 양이 늑대보다 많을 경우
				return new int[]{sCnt, 0};
			}
		}

		return new int[]{0, 0};
	}
}
