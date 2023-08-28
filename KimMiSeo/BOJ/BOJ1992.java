// 쿼드 트리
// ✅ 
// 04:18 - 5:18 : 1h

// 1. 처음에는 문제 자체를 이해하지 못해서 블로그를 보고 문제를 이해함.
// 2. 영역에 0과 1이 둘다 존재하면 영역을 다시 나눠서 하나의 종류가 있을 때까지 들어가야하므로 재귀를 사용하는 문제라고 판단

import java.util.*;
import java.io.*;

public class BOJ1992 {
	public static int[][] graph;
	public static String result ="";
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		graph = new int[n][n];
		
		
		for (int i=0; i<n; i++) {
			String[] line = br.readLine().split("");
			for(int j=0; j<n; j++) {
				graph[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		solve(0, 0, n);
		System.out.println(result);
	}
	
	private static void solve(int startX, int startY, int len) {
		if (len == 1) {
			result += graph[startX][startY]+"";
			return;
		}
		
		boolean isOne=false;
		boolean isZero = false;
		
		for (int i=0; i<len; i++) {
			for (int j=0; j<len; j++) {
				if (graph[startX+i][startY+j] == 1 ) {
					isOne = true;
				}
				if (graph[startX+i][startY+j] == 0) {
					isZero = true;
				}
			}
		}
		
		// 둘다 존재
		if (isOne && isZero) {
			result += "(";
			solve(startX,startY,len/2);
			solve(startX, startY+len/2,len/2);
			solve(startX+len/2 , startY, len/2);
			solve(startX+len/2, startY+len/2, len/2);
			result+=")";
		}
		
		else if (isOne) {
			result += "1";
		}
		
		else if (isZero) {
			result+= "0";
		}
		
		return;
		
	}

}
