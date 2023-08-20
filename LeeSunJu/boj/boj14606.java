import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

;public class boj14606 {
	static int result = 0;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		// 방법 1
//		System.out.println(n * (n - 1) / 2);

		// 방법 2
		solution(n);
		System.out.println(result);
	}

	public static void solution(int n) {
		int n1 = 0;
		int n2 = 0;

		if (n == 1) {
			return;
		}
		n1 = n / 2;
		n2 = n - n1;
		result += n1 * n2;
		solution(n1);
		solution(n2);
	}
}