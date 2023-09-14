package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

//1174번
public class 줄어드는수 {
	// 9 8 7 6 5 4 3 2 1 0 
	// nextPermutation
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		int[] numList = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
		int[] permutationList = new int[10];
		int count = 0;
		int num = 0;
		do {
			if(!nextPermutation(permutationList)) {
				num++;
				Arrays.fill(permutationList, 0);
				for(int i = 10-num; i < 10; i++) {
					permutationList[i] = 1;
				}
			}
				count++;
		} while (count < n && num < 10);
		
		if(count == n) {
			for(int i = 0; i < 10; i++) {
				if(permutationList[i] == 1) {
					sb.append(numList[i]);
				}
			}
			System.out.println(sb);
		}else {
			System.out.println(-1);
		}
		
	}

	private static boolean nextPermutation(int[] p) {
		int N = p.length;
		
		int i = N-1;
		while (i > 0 && p[i-1] >= p[i]) i--;
		
		if (i == 0) {
			return false;
		}
		
		int j = N-1;
		while(i <= j && p[i-1] >= p[j]) j--;
		
		swap(p, i-1, j);
		
		int k = N-1;
		while(i < k) {
			swap(p, i++, k--);
		}
		
		return true;
	}
	
	private static void swap(int[] p, int i, int j) {
		int tmp = p[i];
		p[i] = p[j];
		p[j] = tmp;
	}
}
