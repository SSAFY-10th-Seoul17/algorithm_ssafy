import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj5904 {
	static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		int k = 0;
		int length = 3;
		while (length < N) {
			length *= 2;
			length += (++k + 3);
		}

		N -= 1;
		System.out.println(solve(k, length));
	}

	private static char solve(int k, int n) {
		int mid = (n - (k + 3)) / 2;

		if (N >= n - mid) {
			N = N - mid - (k + 3);
		}
		if (N >= mid && N < n - mid) {
			if (N == mid) {
				return 'm';
			} else {
				return 'o';
			}
		}

		return solve(k - 1, mid);
	}
}
