import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int[] P = new int[N];
		long[] sum = new long[N];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			P[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(P);

		sum[0] = P[0];
		for (int i = 1; i < N; i++) {
			sum[i] = sum[i - 1] + P[i];
		}

		for (int i = 0; i < Q; i++) {
			int X = Integer.parseInt(br.readLine());
			if (X < P[0] || X > P[N - 1]) {
				sb.append(Math.abs(sum[N - 1] - (long)N * X));
			} else {
				int inx = binarySearch(P, X);
				sb.append(getScore(sum, N, X, inx));
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
	} // end of main

	private static long getScore(long[] sum, int N, int x, int inx) {
		long smaller = (long)(inx + 1) * x - sum[inx];
		long bigger = (sum[N - 1] - sum[inx]) - (long)(N - inx - 1) * x;
		return smaller + bigger;
	}

	private static int binarySearch(int[] P, long X) {
		int left = 0;
		int right = P.length - 1;
		int inx = -1;
		while (left <= right) {
			int mid = (right + left) / 2;
			if (P[mid] <= X) {
				inx = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return inx;
	}
} // end of class
