import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj5567 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine()); // 동기의 수
		int m = Integer.parseInt(br.readLine()); // 리스트의 길이

		ArrayList<ArrayList<Integer>> list = new ArrayList<>();

		for (int i = 0; i <= n; i++) {
			list.add(new ArrayList<>());
		}

		int result = 0;
		boolean[] visited = new boolean[n + 1];
		visited[1] = true; // 상근이 방문처리
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			list.get(b).add(a);

			// 상근이 친구들
			if (a == 1) {
				result++;
				visited[b] = true;
			}
			else if (b == 1) {
				result++;
				visited[a] = true;
			}
		}

		for (int i = 0; i < list.get(1).size(); i++) {
			int num = list.get(1).get(i); // 상근이의 i번째 친구
			for (int j = 0; j < list.get(num).size(); j++) { // 상근이의 i번째 친구의 친구들
				if (!visited[list.get(num).get(j)]) {
					result++;
					visited[list.get(num).get(j)] = true;
				}
			}
		}

		System.out.println(result);
	}
}
