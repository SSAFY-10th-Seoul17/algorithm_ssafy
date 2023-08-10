package Algo;

import java.util.*;
import java.io.*;

public class boj10972 {
	
	static ArrayList<Integer> arr = new ArrayList<>();
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int length = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()) {
			arr.add(Integer.parseInt(st.nextToken()));
		}
		
		// 1 phase : find A[i-1] < A[i]
		int i = length-1;
		
		while(i > 0 && arr.get(i) < arr.get(i-1)) i -= 1;
		if(i == 0) {
			System.out.println("-1");
		} else {
			// 2 phase : find j >= 1 and arr[i-1] > arr[j]
			
			int j = length - 1;
			while (j >= i && arr.get(i-1) > arr.get(j)) {
				j-=1;
			}
			swap(i-1, j);
			
			// phase 3 : sort to increasing sequence
			j = length - 1;
			while(i < j) {
				swap(i, j);
				i+=1;
				j-=1;
			}
			
			for(int k = 0; k<length; k++) {
				if(k == length-1) {
					System.out.print(arr.get(k));
				} else {
					System.out.print(arr.get(k) + " ");
				}
			}
		}
		
	}
	
	private static void swap(int i, int j) {
		int tmp = -1;
		tmp = arr.get(i);
		arr.set(i, arr.get(j));
		arr.set(j, tmp);
	}
}
