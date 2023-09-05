package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 휴게소세우기 {
	private static int[] rest;
	private static int l;
	private static int m;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// 휴게소 개수 n
		int n = Integer.parseInt(st.nextToken());
		// 더 지으려는 휴게소 개수 m
		m = Integer.parseInt(st.nextToken());
		// 고속도로 길이 l
		l = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		rest = new int [n+2];
		rest[0] = 0; // 고속도로의 시작점
		rest[n+1] = l; // 고속도로의 끝
		for(int i = 1; i <= n; i++) {
			rest[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(rest);

		//매개변수 탐색
		binarySearch();
		
	} // end of main

	private static void binarySearch() {
		
		int left = 1;
		int right = l-1;
		
		while(left <= right) {
			int mid = (left + right) / 2;
			int count = 0; // 새롭게 설치할 휴게소 개수
			for(int i = 1; i < rest.length; i++) {
				int pos = rest[i] - rest[i-1]; // 휴게소 사이의 거리
				count += pos / mid; // mid에 따른 설치 가능 휴게소 개수
				if(pos % mid == 0) count--; // 이미 설치된 휴게소에 새롭게 설치 불가
			}
			if(count > m) left = mid+1; // m개보다 많이 설치할 경우 줄이기 위해서 mid의 길이 늘이기
			else right = mid-1;
		}
		
		System.out.println(left);
	}

}// end of class
