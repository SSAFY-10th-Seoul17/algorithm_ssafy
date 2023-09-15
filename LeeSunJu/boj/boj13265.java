import java.io.*;
import java.util.*;

public class boj13265 {
	static ArrayList<ArrayList<Integer>> list;
	static String result;
	static int n, m;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			list = new ArrayList<>();
			for (int i = 0; i <= n; i++) {
				list.add(new ArrayList<>());
			}

			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				list.get(x).add(y);
			}

			int[] circleColor = new int[n + 1];
			result = "possible";
			for (int i = 1; i <= n; i++) {
				if (circleColor[i] == 0) {
					dfs(circleColor, i);
				}
				if (result.equals("impossible")) {
					break;
				}
			}
			sb.append(result).append("\n");
		}
		System.out.print(sb.toString());
	}

	private static void dfs(int[] circleColor, int circle) {
		for (int i = 0; i < list.get(circle).size(); i++) {
			int next = list.get(circle).get(i);
			if (circleColor[next] == 0) {
				circleColor[next] = 3 - circleColor[circle]; // 2가지 색 칠하기
				dfs(circleColor, list.get(circle).get(i));
			}
			if (circleColor[circle] == circleColor[next]){ // 인접한 동그라미의 색이 같은 때
				result = "impossible";
				return;
			}
		}
	}
}
