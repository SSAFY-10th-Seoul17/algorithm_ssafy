import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj16401 {
	static int M, N;
	static int[] snacks;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		snacks = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			snacks[i] = Integer.parseInt(st.nextToken());

		// solution
		Arrays.sort(snacks);
		int len = binarySearch(1, snacks[N - 1]);

		// output
		System.out.println(len);
	}

	static int binarySearch(int start, int end) {
		int len = 0;
		while (start <= end) {
			int mid = (start + end) / 2;
			int cnt = 0;
			for (int i = 0; i < N; i++)
				cnt += snacks[i] / mid;
			if (cnt >= M) {
				start = mid + 1;
				len = mid;
			} else
				end = mid - 1;
		}
		return len;
	}
}
