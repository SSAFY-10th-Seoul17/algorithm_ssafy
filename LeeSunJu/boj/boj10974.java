import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj10974 {
	static int N;
	static int[] nums;
	static boolean[] isSelected;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		isSelected = new boolean[N];

		sb = new StringBuilder();
		permutation(0);
		System.out.print(sb);
	}

	private static void permutation(int cnt) {
		if (cnt == N) {
			for (int i = 0; i < N; i++) {
				sb.append(nums[i] + " ");
			}
			sb.append("\n");
		}

		for (int i = 1; i <= N; i++) {
			if (isSelected[i - 1]) {
				continue;
			}

			nums[cnt] = i;
			isSelected[i - 1] = true;
			permutation(cnt + 1);
			isSelected[i - 1] = false;
		}
	}
}
