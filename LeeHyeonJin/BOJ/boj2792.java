import java.io.*;
import java.util.*;

public class boj2792 {
	static int N, M;
	static int[] resources;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		resources = new int[M];
		int max = Integer.MIN_VALUE;
		for(int i=0; i<M; i++) {
			resources[i] = Integer.parseInt(br.readLine());
			max = Math.max(max, resources[i]);
		}

		// solution
		int res = binarySearch(0, max, N);

		// output
		System.out.println(res);
	}

	static int binarySearch(int start, int end, int target) {
		int res = 0;
		while(start <= end) {
			int mid = (start+end)/2;
			// 매개변수 조건 로직 시작
			int cnt = 0;
			for(int i=0; i<M; i++) {
				if(resources[i]%mid == 0) cnt += resources[i]/mid;
				else cnt += resources[i]/mid+1;
			}
			// 매개변수 조건 로직 끝
			if(cnt > target) {
				start = mid+1;
			} else {
				end = mid-1;
				res = mid;
			}
		}
		return res;
	}
}
