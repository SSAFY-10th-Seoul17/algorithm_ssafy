import java.util.*;
import java.io.*;

public class BOJ10974_모든순열 {
	public static int n;
	public static boolean[] isSelected;
	public static int[] numbers;
	public static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		isSelected = new boolean[n+1];
		numbers = new int[n];
		
		solve(0);
		System.out.println(sb.toString());
	}
	
	private static void solve(int cnt) {
		if (cnt == n) {
			for (int i=0; i<n; i++) {
				sb.append(numbers[i]+" ");
			}
			sb.append("\n");
		}
		for (int i=1; i<=n; i++) {
			if (isSelected[i]) continue;
			numbers[cnt] = i;
			isSelected[i] = true;
			solve(cnt+1);
			isSelected[i] = false;
		}
		
		
	}

}
