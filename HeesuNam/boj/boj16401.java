import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int m = Integer.parseInt(st.nextToken()); // 조카의 수
		int n = Integer.parseInt(st.nextToken()); // 과자의 수

		int[] chips = new int[n];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < n; i++) { // 과자 길이
			chips[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(chips);
		System.out.println(binarySearch(chips, m, 1, chips[n - 1]));

	} // end of main

	private static int binarySearch(int[] chips, int target, int left, int right) {
		int res = 0;
		while (left <= right) {
			int mid = (left + right) / 2;
			int cnt = 0;
			for (int chip : chips) {
				cnt += chip / mid;
			}

			if (cnt >= target) {
				res = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return res;
	}
} // end of class