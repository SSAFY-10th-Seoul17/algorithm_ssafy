package boj;

import java.io.*;
import java.util.*;

public class 입국심사 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 심사대 개수 n
		long n = Integer.parseInt(st.nextToken());
		// 사람 m명
		long m = Integer.parseInt(st.nextToken());
		
		long[] t = new long[(int)n]; // t[k] : k번 심사대에서 걸리는 시간
		for(int i = 0; i < n; i++) {
			t[i] = Long.parseLong(br.readLine());
		}
		
		Arrays.sort(t);	
		
		sol(t, m);
	}

	private static void sol(long[] time, long friends) {

		long left = 0L;
		long right = time[time.length-1] * friends;
		
		while(left <= right) {
			long mid = (left + right) / 2; // 걸리는 시간
			long count = 0; // 심사 인원수
			
			for(int i = 0; i < time.length; i++) {
				count+= mid/time[i]; // 시간 당 심사 가능한 인원수
				if(count > friends) break;
			}
			
			if(count < friends) { // 심사 가능 인원수 < 친구들 -> 시간이 촉박하다 -> 최소를 올린다
				left = mid+1;
			}
			else { 
				right = mid-1;
			}

		}// end of while
		
		System.out.println(left);
		
	}
}
