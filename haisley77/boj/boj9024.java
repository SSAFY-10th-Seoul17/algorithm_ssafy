import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj9024 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int[] A = new int[N];
			st = new StringTokenizer(br.readLine()," ");
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(A);
			int s = 0, e = N-1, cnt = 0;
			int diff = Integer.MAX_VALUE;
			while (s < e) {
				int sum = A[s] + A[e];
				int tmp = Math.abs(sum - K);
				if (tmp < diff) {
					diff = tmp;	
					cnt = 1;	
				} else if (tmp == diff) {	
					cnt++;	
				}
				if (sum < K) {
					s++;
				} else if (sum == K) {
					s++;
					e--;
				} else {
					e--;
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}
}
