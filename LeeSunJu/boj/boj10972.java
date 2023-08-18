import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj10972 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		int idx1 = -1;
		for (int i = N - 1; i >= 1; i--) {
			if (nums[i] > nums[i - 1]) {
				idx1 = i - 1;
				break;
			}
		}

		StringBuilder sb = new StringBuilder();
		if (idx1 != -1) {
			int idx2 = -1;
			for (int i = N - 1; i > idx1; i--) {
				if (nums[i] > nums[idx1]) {
					idx2 = i;
					break;
				}
			}

			int tmp = nums[idx1];
			nums[idx1] = nums[idx2];
			nums[idx2] = tmp;

			for (int i = idx1 + 1, j = N - 1; i <= idx1 + (N - idx1) / 2; i++) {
				tmp = nums[i];
				nums[i] = nums[j];
				nums[j--] = tmp;
			}

			for (int i = 0; i < N; i++) {
				sb.append(nums[i] + " ");
			}
		} else {
			sb.append(-1);
		}

		System.out.print(sb);
	}
}
