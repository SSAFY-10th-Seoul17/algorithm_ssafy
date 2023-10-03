import java.io.*;
import java.util.*;

public class boj20917 {
	static int N, S;
	static int[] locations;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			// input
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			S = Integer.parseInt(st.nextToken());

			locations = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			locations[0] = Integer.parseInt(st.nextToken());
			for(int i=1; i<N; i++) {
				locations[i] = Integer.parseInt(st.nextToken());
			}

			// solution
			Arrays.sort(locations);
			int res = binarySearch(1, locations[N-1]+1, S);

			// output
			sb.append(res).append("\n");
		}

		System.out.println(sb.toString());
	}

	static int binarySearch(int start, int end, int target) {
		while(start < end-1) {
			int mid = (start+end)/2;
			int cnt = 1, prev = locations[0];
			for(int i=1; i<N; i++) {
				if(Math.abs(prev - locations[i]) >= mid) {
					cnt++;
					prev = locations[i];
				}
			}
			if(cnt >= target) {
				start = mid;
			} else {
				end = mid;
			}
		}
		return end-1;
	}
}
