import java.io.*;
import java.util.*;

public class boj2096 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[][] minDp = new int[N][3];
		int[][] maxDp = new int[N][3];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				int num = Integer.parseInt(st.nextToken());
				minDp[i][j] = num;
				maxDp[i][j] = num;
			}
		}

		for (int i = 1; i < N; i++) {
			minDp[i][0] = Math.min(minDp[i - 1][0] + minDp[i][0], minDp[i - 1][1] + minDp[i][0]);
			minDp[i][1] = Math.min(Math.min(minDp[i - 1][0] + minDp[i][1], minDp[i - 1][1] + minDp[i][1]),
				minDp[i - 1][2] + minDp[i][1]);
			minDp[i][2] = Math.min(minDp[i - 1][1] + minDp[i][2], minDp[i - 1][2] + minDp[i][2]);

			maxDp[i][0] = Math.max(maxDp[i - 1][0] + maxDp[i][0], maxDp[i - 1][1] + maxDp[i][0]);
			maxDp[i][1] = Math.max(Math.max(maxDp[i - 1][0] + maxDp[i][1], maxDp[i - 1][1] + maxDp[i][1]),
				maxDp[i - 1][2] + maxDp[i][1]);
			maxDp[i][2] = Math.max(maxDp[i - 1][1] + maxDp[i][2], maxDp[i - 1][2] + maxDp[i][2]);
		}

		int min = minDp[N - 1][0];
		int max = maxDp[N - 1][0];
		for (int i = 1; i < 3; i++) {
			min = Math.min(min, minDp[N - 1][i]);
			max = Math.max(max, maxDp[N - 1][i]);
		}

		System.out.println(max + " " + min);
	}
}
