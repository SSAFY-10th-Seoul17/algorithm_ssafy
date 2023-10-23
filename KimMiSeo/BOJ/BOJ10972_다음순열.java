import java.util.*;
import java.io.*;

// 1. 모든 순열을 구하면서 동일한 순열을 만들었을 때, flag를 변경해주고 다음 순열을 만들어서 리턴하는 방법으로 구현했으나 시간초과
// 2. 블로그 보고 풀었다,, 

public class BOJ10972_다음순열 {
	public static int n;
	public static int[] input;
	public static StringBuilder sb;
//	public static int[] numbers;
//	public static int total = 1;
//	public static int t=0;
//	public static boolean isSelected[];

//	public static boolean flag = false;
	
	public static void main(String[] args) throws IOException {
		// 1 ~ n 순열, 다음에 오는 순열 구하기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		
		input = new int[n];
//		numbers= new int[n];
//		isSelected = new boolean[n+1];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0, index=1; i<n; i++,index++) {
//			total *= index;
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		if(next()) {
			for (int i=0; i<n; i++) {
				sb.append(input[i]+" ");
			}
		}else {
			sb.append("-1");
		}
		System.out.println(sb.toString());		
	} // end of main
	
	private static void swap(int i, int j) {
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}
	
	private static boolean next() {
		int i = n-1;
		while(i>0 && input[i-1]>=input[i]) {
			i--;
		}

		if (i<=0) { // 마지막까지 간 경우
			return false;
		}
		
		int j = n-1;
		while(input[j] <= input[i-1]) {
			j--;
		}		
		swap(i-1, j);
		
		// i ~ n-1 까지 뒤집기
		j = n-1;
		while (i < j) {
			swap(i,j);
			i++;
			j--;
		}
		return true;
	}
	
	// 중복 불가, 순서 의미있다
//	private static void solve(int cnt) {
//		if(cnt == n) {
//			t++;
//
//			if (flag) {
//				for(int i=0; i<n; i++) {
//					sb.append(numbers[i]+" ");
//				}
//				flag = false;
//				return;
//			}
//			
//			for(int i=0; i<n; i++) {
//				if (numbers[i]!=input[i]) {
//					flag = false;
//					return;
//				}
//				flag = true;
//				
//				if (t == total) {
//					sb.append(-1);
//					return;
//				}
//			}
//
//			return;
//		}
//		
//		for (int i=1; i<=n; i++) {
//			
//			if (isSelected[i]) continue;
//			numbers[cnt] = i;
//			isSelected[i] = true;
//			solve(cnt+1);
//			isSelected[i] = false;
//		}
//		
//	}
}
