package study8_3;

import java.io.*;
import java.util.*;

// 병사 배치하기
public class boj18353 {
	static int n;
	static int[] soldiers;
	static int[] lis;
	static int idx = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		n = Integer.valueOf(br.readLine());
		soldiers = new int[n];
		lis = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = n-1; i >= 0; i--) {
			soldiers[i] = Integer.valueOf(st.nextToken());
		}
		
		lis[idx++] = soldiers[0];
		for(int i = 1; i < n; i++) {
			if (lis[idx-1] < soldiers[i]) {
				lis[idx++] = soldiers[i];
				continue;
			}
			int result = binSearch(i);
			lis[result] = soldiers[i];
		}
		System.out.println(n - idx);
		
	}
	// 현재 저장된 수열 중 현재 검사중인 수보다 큰 숫자중 가장 작은 수를 고른다 
	private static int binSearch(int i) {
		int l = 0;
		int r = idx - 1;
		while (l < r) {
			int m = (l + r) / 2;
			if (lis[m] < soldiers[i])
				l = m + 1;
			else
				r = m;
		}
		return l;
	}
}
