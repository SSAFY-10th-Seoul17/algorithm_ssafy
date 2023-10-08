import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static long M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Long.parseLong(st.nextToken());
		int[] time = new int[N];
		int maxTime = 0;
		for (int i = 0; i < N; i++) {
			time[i] = Integer.parseInt(br.readLine());
			if (maxTime < time[i])
				maxTime = time[i];
		}
		binarySearch(time, 1, maxTime * M);
	}

	private static void binarySearch(int[] time, long left, long right) {
		long ans = 0;
		while (left <= right) {
			long mid = (left + right) / 2;
			if (chkTime(time, mid)) {
				right = mid - 1;
				ans = mid;
			} else {
				left = mid + 1;
			}
		}
		System.out.println(ans);
	}

	private static boolean chkTime(int[] time, long total) {
		long cnt = 0;
		for (int i = 0; i < N; i++) {
			cnt += total / time[i];
			if (cnt >= M)
				return true;
		}

		return false;
	}

}
