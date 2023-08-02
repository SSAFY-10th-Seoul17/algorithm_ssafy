import java.io.*;
import java.lang.*;
import java.util.*;

public class boj2615 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// input
		StringTokenizer st;
		int[][] map = new int[19][19];
		for (int r = 0; r < 19; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < 19; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		// solution
		// 왼쪽부터 위에서 아래로 탐색 :
		// 연속된 다섯 개의 바둑알의 첫번쨰 좌표를 편하게 출력하기 위함 + 반대 경우 탐색 안할거임
		int color = 0;
		int row = 0;
		int col = 0;
		int[][] directions = {{0, 1}, {1, 0}, {-1, 1}, {1, 1}}; // 오른쪽, 아래, 오른쪽 위, 오른쪽 아래
		for (int c = 0; c < 19; c++) {
			for (int r = 0; r < 19; r++) {
				if (map[r][c] != 0) {
					for (int d = 0; d < 4; d++) {
						int nr = r;
						int nc = c;
						boolean isPossible = true;
						for (int cnt = 0; cnt < 4; cnt++) {
							nr += directions[d][0];
							nc += directions[d][1];
							if (nr >= 0 && nr < 19 && nc >= 0 && nc < 19) {
								if (map[nr][nc] != map[r][c]) { // 연속으로 똑같은 색깔 X
									isPossible = false;
									break;
								}
							} else { // 바둑판 범위를 벗어남
								isPossible = false;
								break;
							}
						}
						if (isPossible) {
							nr += directions[d][0];
							nc += directions[d][1];
							int tr = r - directions[d][0];
							int tc = c - directions[d][1];
							if (nr >= 0 && nr < 19 && nc >= 0 && nc < 19) {
								if ((map[nr][nc] == map[r][c])) {
									continue;
								}
							}
							if (tr >= 0 && tr < 19 && tc >= 0 && tc < 19) {
								if ((map[tr][tc] == map[r][c])) {
									continue;
								}
							}
							color = map[r][c];
							row = r;
							col = c;
							break;
						}
					}
				}
			}
		}
		// output
		if (color != 0) { // 이긴 사람 O
			bw.write(color + "\n" + (row + 1) + " " + (col + 1));
		} else { // 승부 결정 X
			bw.write("0");
		}
		bw.flush();
		bw.close();
	}
}

/**
 * 찾는 반례
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 * 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1
 */
