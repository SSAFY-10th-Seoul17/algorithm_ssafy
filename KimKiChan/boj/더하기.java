package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

//15989번
public class 더하기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[] sum = new int[10001]; // 만드는 경우의 수
		Arrays.fill(sum, 1);

		for(int i = 2; i <= 10000; i++) {
			sum[i] += sum[i-2];
		}
		for(int i = 3; i <= 10000; i++) {
			sum[i] += sum[i-3];
		}

		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());
			
			sb.append(sum[n]).append("\n");
		}
		System.out.println(sb);
		
	}
}
