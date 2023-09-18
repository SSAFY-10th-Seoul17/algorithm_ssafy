package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//14719번
public class 빗물 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		// 세로의 길이 h
		int h = Integer.parseInt(st.nextToken());
		// 가로의 길이 w
		int w = Integer.parseInt(st.nextToken());
		
		int[] height = new int[w];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < w; i++) {
			height[i] = Integer.parseInt(st.nextToken());
		}
		
		// 중요한 것은 솟아오른 봉우리
		// 좌 우 중 최소의 높이 
		// 최소의 높이 - 현재 높이 = 빗물의 양
		int rain = 0;
		
		for(int i = 0; i < w; i++) {
			int left = height[i]; // 자신보다 왼쪽에 있는 가장 높은 봉우리
			int right = height[i]; // 자신보다 오른쪽에 있는 가장 높은 봉우리
			for(int j = 0; j < i; j++) {
				left = Math.max(left, height[j]);
			}
			for(int j = i+1; j < w; j++) {
				right = Math.max(right, height[j]);
			}
			int top = Math.min(left, right); // 봉우리 중 최소 높이
			if(top > height[i]) {
				rain += top - height[i];
			}

		}
		
		System.out.println(rain);
		
	}
}

