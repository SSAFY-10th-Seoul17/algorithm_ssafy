package boj;

import java.util.*;
import java.io.*;
//5904번
public class Moo게임 {
	
	private static ArrayList<Integer> moo;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		moo = new ArrayList<>();
		moo.add(0);
		int index = 0;
		while(moo.get(index) < n) {
			moo.add(moo.get(index++)*2 + index + 2);
		}
		
		sol(n, index);
	}

	private static void sol(int n, int index) {
		while(moo.get(index)<n) {
			index++;
		}
		n -= moo.get(index-1);
		if(n <= index+2) {
			if(n==1)System.out.println('m');
			else System.out.println('o');
			return;
		}else {
			n -= index+2;
			sol(n, 0);
		}
		
	}
}
