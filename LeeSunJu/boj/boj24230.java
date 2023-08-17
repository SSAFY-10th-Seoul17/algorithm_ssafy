import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj24230 {

	/**
	 * 양방향 그래프를 만든다.
	 * 위에서부터 내려오면서 부모와 숫자가 다르면 cnt + 1을 해준다.
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] colors = new int[N];
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			colors[i] = Integer.parseInt(st.nextToken());
			graph.add(new ArrayList<>());
		}

		int n1, n2;
		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());

			graph.get(n1 - 1).add(n2);
			graph.get(n2 - 1).add(n1);
		}

		int cnt = 0;
		if (colors[0] != 0) {
			cnt++;
		}

		boolean[] visited = new boolean[N];
		for (int i = 0; i < graph.size(); i++) {
			for (int j = 0; j < graph.get(i).size(); j++) {
				if (!visited[graph.get(i).get(j) - 1]) {
					if (colors[i] != colors[graph.get(i).get(j) - 1]) {
						cnt++;
					}
				}
			}
			visited[i] = true;
		}
		System.out.println(cnt);
	}
}
