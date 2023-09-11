package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 두수의합 { 
	private static int[] arr;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		
		for(int t = 0; t < test; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 주어진 정수의 개수 n
			int n = Integer.parseInt(st.nextToken());
			// 목표 합
			int k = Integer.parseInt(st.nextToken());
			
			arr = new int[n];
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);
			
			int count = 0;
			int near = Integer.MAX_VALUE;
			int start = 0;
			int end = n-1;
			
			while(start < end) {
				int left = arr[start];
				int right = arr[end];
				
				if(left + right == k) {
					start++;
					end--;
				}
				else if(left + right > k) {
					end--;
				}
				else {
					start++;
				}
				
				//count 초기화, count++
				if(Math.abs(left + right -k) < Math.abs(near - k) ) {//기존보다 차이가 작으면
					near = left + right;
					count = 1;
				}
				else if(Math.abs(left + right -k) == Math.abs(near - k)){ // 기존과 차이가 같으면
					count++;
				}
			}
			sb.append(count).append("\n");
			System.gc();
		}// end of test case
		System.out.println(sb);
	}

}
