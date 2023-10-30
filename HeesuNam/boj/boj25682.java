import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, K;
	private static int[][] Bcnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		Bcnt = new int[N + 1][M + 1]; // 체스판 시작이 B일때
		for (int i = 1; i <= N; i++) {
			char[] color = br.readLine().toCharArray();
			for (int j = 1, inx = 0; j <= M; j++, inx++) {
				// 누적합
				Bcnt[i][j] += Bcnt[i][j - 1] + Bcnt[i - 1][j] - Bcnt[i - 1][j - 1];
				// 열+행 홀이면 W 열+행 짝이면 B
				if (chkColor(i + j, color[inx])) {
					Bcnt[i][j] += 1;
				}
			}
		}
		int maxPaint = 0;
		int minPaint = K * K;
		// B 기준으로 최소 색칠, 최대 색칠구하기
		for (int i = 1; i <= N - K + 1; i++) {
			for (int j = 1; j <= M - K + 1; j++) {
				int cntPaint = Bcnt[i + K - 1][j + K - 1] - Bcnt[i - 1][j + K - 1] - Bcnt[i + K - 1][j - 1]
						+ Bcnt[i - 1][j - 1];
				if(maxPaint<cntPaint) maxPaint = cntPaint;
				if(minPaint>cntPaint) minPaint = cntPaint;
			}
		}
		// W로 시작하는 체스판의 경우 K*K -B최대색칠수
		System.out.println(Math.min(minPaint, K*K-maxPaint));
	}// end main

	private static boolean chkColor(int sumRC, char color) {
		return (sumRC % 2 == 1 && color != 'W') || (sumRC % 2 == 0 && color != 'B');
	}
}
// end class
