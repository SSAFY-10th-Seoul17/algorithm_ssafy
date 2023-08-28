package study8_5;

import java.io.*;
import java.util.*;

// 나의 인생에는 수학과 함께
public class boj17265 {
	static int n;
	static char[][] cells;
	static int[][] maxDp;
	static int[][] minDp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		n = Integer.valueOf(br.readLine());
		cells = new char[n][n];
		maxDp = new int[n][n];
		minDp = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				cells[i][j] = st.nextToken().charAt(0);
				maxDp[i][j] = Integer.MIN_VALUE;
				minDp[i][j] = Integer.MAX_VALUE;
			}
		}
		
		maxDp[0][0] = cells[0][0] - '0';
		minDp[0][0] = cells[0][0] - '0';
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (!Character.isDigit(cells[i][j]))
					continue;
				if (j + 2 < n) {
					if (cells[i][j+1] == '*') {
						maxDp[i][j+2] = Math.max(maxDp[i][j+2], maxDp[i][j] * (cells[i][j+2] - '0'));
						minDp[i][j+2] = Math.min(minDp[i][j+2], minDp[i][j] * (cells[i][j+2] - '0'));
					}
					else if (cells[i][j+1] == '+') {
						maxDp[i][j+2] = Math.max(maxDp[i][j+2], maxDp[i][j] + (cells[i][j+2] - '0'));
						minDp[i][j+2] = Math.min(minDp[i][j+2], minDp[i][j] + (cells[i][j+2] - '0'));
					}
					else if (cells[i][j+1] == '-') {
						maxDp[i][j+2] = Math.max(maxDp[i][j+2], maxDp[i][j] - (cells[i][j+2] - '0'));
						minDp[i][j+2] = Math.min(minDp[i][j+2], minDp[i][j] - (cells[i][j+2] - '0'));
					}
				}
				if (j + 1 < n && i + 1 < n) {
					if (cells[i][j+1] == '*') {
						maxDp[i+1][j+1] = Math.max(maxDp[i+1][j+1], maxDp[i][j] * (cells[i+1][j+1] - '0'));
						minDp[i+1][j+1] = Math.min(minDp[i+1][j+1], minDp[i][j] * (cells[i+1][j+1] - '0'));
					}
					else if (cells[i][j+1] == '+') {
						maxDp[i+1][j+1] = Math.max(maxDp[i+1][j+1], maxDp[i][j] + (cells[i+1][j+1] - '0'));
						minDp[i+1][j+1] = Math.min(minDp[i+1][j+1], minDp[i][j] + (cells[i+1][j+1] - '0'));
					}
					else if (cells[i][j+1] == '-') {
						maxDp[i+1][j+1] = Math.max(maxDp[i+1][j+1], maxDp[i][j] - (cells[i+1][j+1] - '0'));
						minDp[i+1][j+1] = Math.min(minDp[i+1][j+1], minDp[i][j] - (cells[i+1][j+1] - '0'));
					}

					if (cells[i+1][j] == '*') {
						maxDp[i+1][j+1] = Math.max(maxDp[i+1][j+1], maxDp[i][j] * (cells[i+1][j+1] - '0'));
						minDp[i+1][j+1] = Math.min(minDp[i+1][j+1], minDp[i][j] * (cells[i+1][j+1] - '0'));
					}
					else if (cells[i+1][j] == '+') {
						maxDp[i+1][j+1] = Math.max(maxDp[i+1][j+1], maxDp[i][j] + (cells[i+1][j+1] - '0'));
						minDp[i+1][j+1] = Math.min(minDp[i+1][j+1], minDp[i][j] + (cells[i+1][j+1] - '0'));
					}
					else if (cells[i+1][j] == '-') {
						maxDp[i+1][j+1] = Math.max(maxDp[i+1][j+1], maxDp[i][j] - (cells[i+1][j+1] - '0'));
						minDp[i+1][j+1] = Math.min(minDp[i+1][j+1], minDp[i][j] - (cells[i+1][j+1] - '0'));
					}
				}
				if (i + 2 < n) {
					if (cells[i+1][j] == '*') {
						maxDp[i+2][j] = Math.max(maxDp[i+2][j], maxDp[i][j] * (cells[i+2][j] - '0'));
						minDp[i+2][j] = Math.min(minDp[i+2][j], minDp[i][j] * (cells[i+2][j] - '0'));
					}
					else if (cells[i+1][j] == '+') {
						maxDp[i+2][j] = Math.max(maxDp[i+2][j], maxDp[i][j] + (cells[i+2][j] - '0'));
						minDp[i+2][j] = Math.min(minDp[i+2][j], minDp[i][j] + (cells[i+2][j] - '0'));
					}
					else if (cells[i+1][j] == '-') {
						maxDp[i+2][j] = Math.max(maxDp[i+2][j], maxDp[i][j] - (cells[i+2][j] - '0'));
						minDp[i+2][j] = Math.min(minDp[i+2][j], minDp[i][j] - (cells[i+2][j] - '0'));
					}
				}
			}
		}
		System.out.println(maxDp[n-1][n-1] + " " + minDp[n-1][n-1]);
	}
}
