import java.io.*;
import java.util.*;

public class boj3020 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		int[] sum = new int[H + 1];
		for (int i = 0; i < N; i++) {
			int obstacle = Integer.parseInt(br.readLine()); // 장애물
			if (i % 2 == 0) { // 석순일 경우
				sum[H - obstacle + 1] += 1;
			} else { // 종유석일 경우
				sum[1] += 1;
				sum[1 + obstacle] -= 1;
			}
		}

		int result = sum[1];
		int cnt = 1;
		for (int i = 2; i <= H; i++) {
			sum[i] += sum[i - 1];
			if (result > sum[i]) {
				result = sum[i];
				cnt = 1;
			} else if (result == sum[i]) {
				cnt++;
			}
		}

		System.out.println(result + " " + cnt);
	}
}
