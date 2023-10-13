import java.io.*;
import java.util.*;

public class boj12761 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 이동할 수 있는 거리
		int[] loc = {1, -1, A, -A, B, -B, A, B};

		int[] memo = new int[100001]; // 이전 이동 횟수 기록들을 저장하기 위한 배열
		Arrays.fill(memo, Integer.MAX_VALUE);
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[]{N, 0});
		while (!q.isEmpty()) {
			int[] tmp = q.poll();
			for (int i = 0; i < loc.length; i++) {
				int next;
				if (i >= 6) { // 순간적으로 힘을 모아 배수로 가는 경우
					next = tmp[0] * loc[i];
				} else {
					next = tmp[0] + loc[i];
				}

				if (next < 0 || next > 100000 || memo[next] <= tmp[1] + 1) {
					continue;
				}

				q.add(new int[] {next, tmp[1] + 1});
				memo[next] = tmp[1] + 1;
			}
		}

		System.out.println(memo[M]);
	}
}
