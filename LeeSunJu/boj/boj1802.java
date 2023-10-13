import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1802 {
	static String s;
	static boolean flag;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < T; i++) {
			s = br.readLine();

			flag = true;
			solve(0, s.length() - 1);

			if (flag) {
				sb.append("YES").append("\n");
			} else {
				sb.append("NO").append("\n");
			}
		}

		System.out.print(sb.toString());
	}

	private static void solve(int left, int right) {
		if (left == right) return;

		int mid = (left + right) / 2;
		for (int i = left; i < mid; i++) {
			if (s.charAt(i) == s.charAt(right - i)) {
				flag = false;
				return;
			}
		}

		solve(left, mid - 1);
		solve(mid + 1, right);
	}
}
