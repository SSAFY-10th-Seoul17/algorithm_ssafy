import java.io.*;
import java.util.*;

// 먹을 것인가 먹힐 것인가.
public class boj7795 {
	static int[] arr;
	static int[] brr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.valueOf(br.readLine());
		while(T-- > 0) {
			st = new StringTokenizer(br.readLine());
			
			int a = Integer.valueOf(st.nextToken());
			int b = Integer.valueOf(st.nextToken());
			
			arr = new int[a];
			brr = new int[b];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < a; i++) 
				arr[i] = Integer.valueOf(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < b; i++) 
				brr[i] = Integer.valueOf(st.nextToken());
			
			Arrays.sort(arr);
			Arrays.sort(brr);
			
			int cnt = 0;
			for (int num : arr) {
				int idx = lower_bound(num, b);
				if (brr[idx] < num)
					cnt += (idx + 1);
			}
				
			sb.append(cnt + "\n");
		}
		System.out.println(sb);
	} // end of main

	private static int lower_bound(int num, int b) {
		int l = 0;
		int r = b;
		int m;
		while (l + 1 < r) {
			m = (l + r) / 2;
			if (brr[m] < num)
				l = m;
			else
				r = m;
		}
		return l;
	}
}
