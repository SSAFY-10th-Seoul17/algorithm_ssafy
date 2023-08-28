package study8_5;

import java.io.*;
import java.util.*;

public class boj16198 {
	static int n;
	static int[] arr;
	static int[] arrCopy;
	static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.valueOf(br.readLine());
		arr = new int[n];
		arrCopy = new int[n];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.valueOf(st.nextToken());
			arrCopy[i] = arr[i];
		}
		
		dfs(n-2, 0);
		System.out.println(ans);
	}

	private static void dfs(int depth, int sum) {
		if (depth == 0) {
			ans = Math.max(ans, sum);
			return;
		}
		
		for (int i = 1; i < n-1; i++) {
			if (arrCopy[i] == 0)
				continue;
			int l = findLeft(i);
			int r = firnRight(i);
			arrCopy[i] = 0;
			dfs(depth - 1, sum + l * r);
			arrCopy[i] = arr[i];
		}
	}

	private static int firnRight(int i) {
		i--;
		while (arrCopy[i] == 0) {
			i--;
		}
		return arrCopy[i];
	}

	private static int findLeft(int i) {
		i++;
		while (arrCopy[i] == 0) {
			i++;
		}
		return arrCopy[i];
	}
}
