import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 과자 나눠주기
public class boj16401 {
	static int n;
	static int m;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		m = Integer.valueOf(st.nextToken());
		n = Integer.valueOf(st.nextToken());
		
		arr = new int[n];
		int longest = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.valueOf(st.nextToken());
			longest = Math.max(longest, arr[i]);
		}
		
		int l = 0;
		int r = longest + 1;
		int mid;
		while (l + 1 < r) {
			mid = (l+r) / 2;
			if (check(mid))
				l = mid;
			else
				r = mid;
		}
		System.out.println(l);
	} // end of main

	private static boolean check(int mid) {
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			cnt += arr[i] / mid;
		}
		return cnt >= m;
	}
}
