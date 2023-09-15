import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class boj16198 {

	static int N, result;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		List<Integer> weights = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			weights.add(Integer.parseInt(st.nextToken()));
		}

		solve(weights, 0);

		System.out.println(result);
	}

	private static void solve(List<Integer> list, int total) {
		if (list.size() == 2) {
			if (result < total) {
				result = total;
			}
			return;
		}

		for (int i = 1; i < list.size() - 1; i++) { // 첫 번째와 마지막 에너지 구슬은 고를 수 없음.
			int n = list.get(i);
			list.remove(i);
			solve(list, total + list.get(i - 1) * list.get(i));
			list.add(i, n);
		}
	}
}
