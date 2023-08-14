import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2004 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int result = Math.min((cal(N, 5) - cal(N - M, 5) - cal(M, 5)),
			(cal(N, 2) - cal(N - M, 2) - cal(M, 2)));

		System.out.println(result);
	}

	private static int cal(int num, int divisor) {
		int cnt = 0;
		while (num >= divisor) {
			cnt += num / divisor;
			num /= divisor;
		}
		return cnt;
	}
}
