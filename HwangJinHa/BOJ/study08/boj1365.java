import java.io.*;
import java.util.*;

// 꼬인 전깃줄
public class Main {
	static int n;
	static int[] arr;
	static int[] lis;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.valueOf(br.readLine());
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) 
			arr[i] = Integer.valueOf(st.nextToken());
		
		// lis
		int result1;
		lis = new int[n];
		int idx = 0;
		lis[idx++] = arr[0];
		for (int a : arr) {
			if (a > lis[idx-1]) {
				lis[idx++] = a;
				continue;
			}
			int l = -1;
			int r = idx - 1;
			int m;
			while (l + 1< r) {
				m = (l + r) / 2;
				if (lis[m] < a)
					l = m;
				else
					r = m;
			}
			lis[r] = a;
		}
		result1 = n - idx;
		System.out.println(result1);
	}

}
