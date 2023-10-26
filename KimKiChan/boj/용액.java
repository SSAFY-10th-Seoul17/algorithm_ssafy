package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//2467번
public class 용액 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int left = 0;
		int right = n-1;
		int sum = Math.abs(arr[left] + arr[right]);
		
		int resultL = left;
		int resultR = right;
		
		while(left < right) {
			int tempSum = arr[left] + arr[right];
			if(Math.abs(tempSum) < sum) {
				sum = Math.abs(tempSum);
				resultL = left;
				resultR = right;
			}
			
			if(tempSum < 0) {
				left++;
			}else {
				right--;
			}
			
		}
		System.out.println(arr[resultL] + " " + arr[resultR]);
	}
	
}
