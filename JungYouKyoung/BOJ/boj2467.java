import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		// 산성용액 : 1 ~ 1_000_000_000 양의 정수
		// 알칼리성 : -1 ~ -1_000_000_000 음의 정수

		// 특성 값이 0에 가장 가까운 용액을 만들려고 한다
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		long[] arr = new long[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		int s = 0; // 인덱스 1
		int e = n - 1; // 인덱스 2
		long answer = Long.MAX_VALUE; // 최종 0과 가까운 값
		int ai = 0; // 값이 될 것 담아두는 인덱스 1
		int aj = 0; // 값이 될 것 담아두는 인덱스 2
		long target = 0;

//		System.out.println(Arrays.toString(arr));
		while (s<e) {

			target = arr[s] + arr[e]; // 두 포인 터

			if (answer > Math.abs(target)) {
				answer =  Math.abs(target); // answer 값 바꾸어줌
				ai = s;
				aj = e;
			}
			if(target >= 0) {
				e--;
			}else {
				s++;
			}
			

		}
		System.out.println(arr[ai] + " " + arr[aj]);

	}

}
