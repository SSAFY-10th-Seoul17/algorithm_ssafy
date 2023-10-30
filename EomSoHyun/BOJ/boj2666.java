package Algorithm;

import java.io.*;
import java.util.*;

public class Main {
	
	static int n, m, minCnt;
	static int[] open, closet;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());  // 벽장 개수 
		st = new StringTokenizer(br.readLine());
		open = new int[2];  // 열 있는 두 개의 벽장 위치 
		open[0] = Integer.parseInt(st.nextToken());
		open[1] = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(br.readLine());  // 사용할 벽장들의 길이 
		closet = new int[m];  // 사용할 벽장 번호 
		for (int i = 0; i < m; i++) {
			closet[i] = Integer.parseInt(br.readLine());
		}
		
		minCnt = Integer.MAX_VALUE;
		dfs(0 ,0);
		System.out.println(minCnt);
		
		
		
	}
	
	private static void dfs(int idx, int cnt) {
		if (idx == m) {
			minCnt = Math.min(minCnt, cnt);
			return;
		}
		
		for (int i = 0; i < 2; i++) {
			int temp = open[i];
			int sum = Math.abs(closet[idx] - open[i]);
			open[i] = closet[idx];
			dfs(idx+1, cnt+sum);
			open[i] = temp;
		}
	}
	

}
