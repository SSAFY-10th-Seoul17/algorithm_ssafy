import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj17103 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		int[] arr = new int[T];

		for (int i = 0; i < T; i++) {
			arr[i] = Integer.parseInt(bf.readLine());
		}

		int num = 1000000;
		boolean[] prime = new boolean[num + 1];
		prime[0] = prime[1] = false;
		for (int i = 2; i <= num; i++) {
			prime[i] = true;
		}

		for (int i = 2; i * i <= num; i++) {
			for (int j = i * i; j <= num; j += i) {
				prime[j] = false;
			}
		}

		for (int i = 0; i < arr.length; i++) {
			int cnt = 0;
			for (int j = 2; j <= arr[i] / 2;  j++) {
				if (prime[j] && prime[arr[i] - j]) {
					cnt++;
				}
			}
			System.out.println(cnt);
		}
	}
}
