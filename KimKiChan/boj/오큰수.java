package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

//17298번
public class 오큰수 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//수열의 크기 n
		int n = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int[] arr = new int[n];
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] result = new int[n];
		Stack<Integer> stack = new Stack<>();
		for(int i = n-1; i >= 0; i--) {
			while(!stack.isEmpty() && arr[i] >= stack.peek()) {
				stack.pop();
			}
			
			result[i] = stack.isEmpty() ? -1 : stack.peek();
			stack.push(arr[i]);
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < n; i++) {
			sb.append(result[i]).append(" ");
		}
		System.out.println(sb.toString());
		
	}
}
