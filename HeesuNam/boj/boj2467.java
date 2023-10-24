import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int ansVal = Integer.MAX_VALUE;
		int[] ans = new int[2];
		if (arr[0] > 0) { // 모든 용액이 산성일때
			ans[0] = arr[0];
			ans[1] = arr[1];
		} else if (arr[N - 1] < 0) {// 모든 용액이 알칼리일때
			ans[0] = arr[N - 2];
			ans[1] = arr[N - 1];
		} else {// 섞여있을때
			int i = 0;
			int j = N - 1;
			while (i < j) {
				int sum = arr[i] + arr[j];
				if (ansVal > Math.abs(sum)) {
					ansVal = Math.abs(sum);
					ans[0] = arr[i];
					ans[1] = arr[j];
				}
				if (sum == 0) {
					break;
				} else if (sum < 0) {
					i++;
				} else {
					j--;
				}
			}
		}
		System.out.println(ans[0] + " " + ans[1] + "\n");
	} // end of main
} // end of class
