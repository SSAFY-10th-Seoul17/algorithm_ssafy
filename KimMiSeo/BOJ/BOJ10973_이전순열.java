import java.util.*;
import java.io.*;

public class BOJ10973_이전순열 {
	public static int n;
	public static int[] input;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		input = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			input[i] = Integer.parseInt(st.nextToken());
		}
		
		if (before()) {
			for (int i=0; i<n; i++) {
				sb.append(input[i]+" ");
			}
		}else {
			sb.append("-1");
		}
		
		System.out.println(sb.toString());
	}
	
	private static void swap(int i, int j) {
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}
	
	private static boolean before() {
		int i = n-1;
		while (i > 0 && input[i-1] <= input[i]) {
			i--;
		}
		
		if( i <=0 ) {
			return false;
		}
		
		int j = n-1;
		while (input[j] >= input[i-1]) {
			j--;
		}
		
		swap(i-1,j);
		
		j = n-1;
		while (j > i) {
			swap(i,j);
			j--;
			i++;
			
		}
		return true;
	}

}
