package Algo;

import java.util.*;
import java.io.*;

public class boj2004 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		int twoCnt = getTwoCnt(n) - getTwoCnt(n-r) - getTwoCnt(r);
		int fiveCnt = getFiveCnt(n) - getFiveCnt(n-r) - getFiveCnt(r);
		
		
		System.out.println(Math.min(twoCnt, fiveCnt));
	}
	private static int getFiveCnt(int n) {
		int cnt = 0;
		
		while (n >= 5) {
			cnt += n/5;
			n/=5;
		}
		
		return cnt;
	}
	
	private static int getTwoCnt(int n) {
		int cnt = 0;
		
		while (n >= 2) {
			cnt += n/2;
			n/=2;
		}
		
		return cnt;
	}
}