import java.io.*;

public class boj12919 {

	static String S, T;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		S = br.readLine();
		T = br.readLine();

		System.out.println(solve(T));
	}

	private static int solve(String t) {
		if (t.length() == S.length()) {
			if (t.equals(S)) {
				return 1;
			}
			return 0;
		}

		int result = 0;
		if (t.charAt(0) == 'B') {
			result += solve(new StringBuilder(t.substring(1)).reverse().toString());
		}

		if (t.charAt(t.length() - 1) == 'A') {
			result += solve(t.substring(0, t.length() - 1));
		}

		return result == 0 ? 0 : 1;
	}
}
