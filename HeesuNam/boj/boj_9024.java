import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < T; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			int[] arr = new int[n];
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < n; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			sb.append(getSumDiff(arr, n, k)).append("\n");
		}
		System.out.println(sb.toString());
	} // end of main

	private static int getSumDiff(int[] arr, int len, int target) {
		Arrays.sort(arr);
		int left = 0;
		int right = len - 1;
		int cnt = 0;
		int minDiff = Integer.MAX_VALUE;
		while (left < right) {
			int sum = arr[left] + arr[right];
			if (sum == target) {
				left++;
				right--;
			} else if (sum < target) {
				left++;
			} else {
				right--;
			}
			int targetDiff = diff(target, sum);
			if (targetDiff < minDiff) {
				minDiff = targetDiff;
				cnt = 1;
			} else if (targetDiff == minDiff) {
				cnt++;
			}
		}
		return cnt;
	}

	private static int diff(int target, int sum) {
		return Math.abs(target - sum);
	}
} // end of class
