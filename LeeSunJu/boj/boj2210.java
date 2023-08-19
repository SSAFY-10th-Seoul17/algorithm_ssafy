import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj2210 {
	static String[][] map;
	static HashSet<String> set;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new String[5][5];
		set = new HashSet<>();

		for (int i = 0; i < map.length; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = st.nextToken();
			}
		}

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				dfs(i, j, map[i][j]);
			}
		}

		System.out.println(set.size());
	}

	private static void dfs(int x, int y, String s) {
		if (s.length() == 6) {
			set.add(s);
			return;
		}

		if (x - 1 >= 0) dfs(x - 1, y, s + map[x - 1][y]); // 상
		if (x + 1 < 5) dfs(x + 1, y, s + map[x + 1][y]); // 하
		if (y - 1 >= 0) dfs(x, y - 1, s + map[x][y - 1]); // 좌
		if (y + 1 < 5) dfs(x, y + 1, s + map[x][y + 1]); // 우
	}
}
