import java.io.*;
import java.util.*;

public class Main {
	private static int N, S;
	private static int[] inputs;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		inputs = new int[N + 1];
		st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++) {
			inputs[i] = Integer.parseInt(st.nextToken());
		}
		getMinLen();

	} // end of main

	private static void getMinLen() {
		int left = 0, right = 0, sum = 0;
		int ans = Integer.MAX_VALUE;
		while (left <= right && right <= N) {
			if (sum < S) {
				sum += inputs[right++];
			} else {
				ans = Math.min(ans, right - left);
				sum -= inputs[left++];
			}
		}
		System.out.println(ans == Integer.MAX_VALUE ? 0 : ans);
	}
} // end of class
