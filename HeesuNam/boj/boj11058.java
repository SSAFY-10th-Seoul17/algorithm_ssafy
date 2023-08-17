import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if (N <= 3) {
			System.out.println(N);
		} else {
			click(3, 0, N - 3, 0);
			System.out.println(ans);
		}
	}

	private static void click(int total, int stack, int n, int chk) {
		if (n == 0) {
			if (ans < total) {
				ans = total;
			}
			return;
		}
		click(total + 1, stack, n - 1, chk);// A누르는 경우
		if (n - 3 >= 0) {
			click(total, total, n - 1, 1);// ctrl a인 경우 chk=1바꾸기
		}
		if (chk == 1 && n - 2 >= 0) {
			click(total, stack, n - 1, 2);// ctrl c인 경우 chk=2바꾸기
		}
		if (chk == 2) {
			click(total + stack, stack, n - 1, chk);// ctrl v인 경우
		}
	}
}
