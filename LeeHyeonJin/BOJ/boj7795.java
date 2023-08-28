import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj7795 {
	static int T, N, M;
	static int[] numA, numB;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// input
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			numA = new int[N];
			M = Integer.parseInt(st.nextToken());
			numB = new int[M];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++)
				numA[i] = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < M; i++)
				numB[i] = Integer.parseInt(st.nextToken());

			// solution
			Arrays.sort(numB);
			int cnt = 0;
			for (int num : numA)
				cnt += binarySearch(0, M - 1, num);
			sb.append(cnt).append("\n");
		}

		// output
		System.out.println(sb.toString());
	}

	static int binarySearch(int start, int end, int target) {
		int cnt = 0;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (target > numB[mid]) {
				start = mid + 1;
				cnt = mid + 1;
			} else
				end = mid - 1;
		}
		return cnt;
	}
}
