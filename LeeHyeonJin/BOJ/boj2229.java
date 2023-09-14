import java.io.*;
import java.util.*;

public class boj2229 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		int N = Integer.parseInt(br.readLine());

		// solution
		int[] ages = new int[N+1];
		int[] dp = new int[N+1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) { // 첫번째요소~N개의 요소에 대한 최댓값을 각각 살펴본다
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			ages[i] = Integer.parseInt(st.nextToken());
			for(int j=i; j>0; j--) {
				// 최댓값을 구해야하는 나이요소마다(i) 이전요소까지(j) 살펴보면서 최댓값(max-min)이 되는 경우로 갱신
				min = Math.min(min, ages[j]);
				max = Math.max(max, ages[j]);
				// i번째 요소까지의 최댓값은 이전(j-1)까지의 최댓값(이미 fix됨) + 방금 구했던 최대요소값과 최소요소값의 차이
				dp[i] = Math.max(dp[i], dp[j-1] + (max - min));
			}
		}

		// output
		System.out.println(dp[N]);
	}
}
