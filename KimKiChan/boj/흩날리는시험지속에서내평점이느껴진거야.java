package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//17951번
public class 흩날리는시험지속에서내평점이느껴진거야 {
	private static int total;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		
		int[] score = new int[n];//점수
		total = 0;
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < score.length; i++) {
			score[i] = Integer.parseInt(st.nextToken());
			total += score[i];
		}
		br.close();//입력 끝
		
		sol(score, k);
		
	}

	private static void sol(int[] score, int k) {//이분 탐색, 매개변수 탐색
		int left = 0;
		int right = total;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			int sum = 0;
			int count = 0;
			for(int i = 0; i < score.length; i++) {
				sum += score[i];
				if(sum >= mid) {
					count++;
					sum = 0;
				}
			}
			
			if(count >= k) {
				left = mid+1;
			}else {
				right = mid-1;
			}
		}
		
		System.out.println(right);
		
	}
}
