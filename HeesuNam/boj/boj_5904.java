import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static String S;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		S = "moo";
		System.out.println(getChar(N, 1, 3));

	}

	private static char getChar(int n, int k, int len) {
		if (n <= 3) {
			return S.charAt(n - 1);
		}
		int strLen = 2 * len + k + 3;
		if (n > strLen) { // 다음 수열 검사
			return getChar(n, k + 1, strLen);
		}
		// 현재 수열에서 검사 가능
		if (len < n && n <= len + k + 3) { // 가운데
			if (n - len == 1) {
				return 'm';
			} else {
				return 'o';
			}
		} else { // 오른쪽 S(k-1)
			return getChar(n - (len + k + 3), 1, 3);
		}
	}
}
