import java.io.*;
import java.util.*;

public class boj9024 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int[] arr = new int[n];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(arr);

			int p1 = 0, p2 = n - 1;
			int minDiff = Integer.MAX_VALUE;
			int result = 0;
			while (p1 < p2) {
				int sum = arr[p1] + arr[p2];
				int diff = Math.abs(sum - K);

				if (diff == minDiff) {
					result++;
				} else if (diff < minDiff) {
					minDiff = diff;
					result = 1;
				}

				if (sum > K) {
					p2--;
				} else {
					p1++;
				}
			}

			sb.append(result).append("\n");
		}

		System.out.print(sb.toString());
	}
}
