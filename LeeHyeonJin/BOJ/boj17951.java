import java.io.*;
import java.util.*;

public class boj17951 {
	static int N, K;
	static int[] counts;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		counts = new int[N];
		int sum = 0, min = Integer.MAX_VALUE;
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			counts[i] = Integer.parseInt(st.nextToken());
			sum += counts[i];
			min = Math.min(min, counts[i]);
		}

		// solution
		int left = 0, right = sum+1;
		while(left+1 != right) {
			int mid = (left+right)/2;
			if(check(mid)) {
				left = mid;
			} else {
				right = mid;
			}
		}

		// output
		System.out.println(left);
	}

	static boolean check(int mid) {
		int cnt = 0, sum = 0;
		for(int i=0; i<N; i++) {
			sum += counts[i];
			if(sum >= mid) {
				cnt++;
				sum = 0;
			}
		}
		return cnt >= K;
	}
}
