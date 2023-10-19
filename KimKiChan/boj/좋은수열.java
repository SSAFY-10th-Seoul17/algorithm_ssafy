package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 좋은수열 {
	private static int[] arr;
	private static int n;
	private static boolean solved;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		arr = new int[n+1]; // 수열
		solved = false;
		sol(1);
	}

	private static void sol(int index) {
		if(solved) return;
		if(index == n+1) {
			solved = true;
			StringBuilder sb = new StringBuilder();
			for(int i = 1; i < arr.length; i++) {
				sb.append(arr[i]);
			}
			System.out.println(sb);
			return;
		}
		for(int i = 1; i <= 3; i++) {
			arr[index] = i;
			//부분수열 체크 true
			if(check(index)) {
				sol(index+1);
			}
		}
	}

	private static boolean check(int index) { // 부분 수열 체크
		int[] left;
		int[] right;
		
		for(int i = 1; i <= index/2; i++) {
			left = new int[i];
			right = new int[i];
			for(int j = 0; j < i; j++) {
				left[j] = arr[index-j];
				right[j] = arr[index-j-i];
			}
			if(Arrays.equals(left, right)) return false;
		}
		
		return true;
	}
}
