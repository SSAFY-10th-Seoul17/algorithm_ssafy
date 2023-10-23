import java.io.*;
import java.util.*;

// 부메랑 만들기
// y = inx/M, x = inx%M
// rightUp, rightDown, leftUp, leftDown 부메랑모양만들기
// 세지점이 모두 방문하지 않은 상태 visited로 검사
// inx = N*M면 ans갱신
public class Main {
	private static int N, M;
	private static int[][] board;
	private static boolean[][] visited;
	private static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[N][M];
		makeBoomerang(0, 0);
		System.out.println(ans);

	} // end of main

	private static void makeBoomerang(int inx, int total) {
		if (inx == N * M) {
			if (ans < total)
				ans = total;
			return;
		}
		int y = inx / M;
		int x = inx % M;
		if (!visited[y][x]) {
			if (rightUp(y, x)) {
				visited[y][x + 1] = true;
				visited[y][x] = true;
				visited[y - 1][x] = true;
				makeBoomerang(inx + 1, total + board[y][x + 1] + 2 * board[y][x] + board[y - 1][x]);
				visited[y][x + 1] = false;
				visited[y][x] = false;
				visited[y - 1][x] = false;
			}
			if (rightDown(y, x)) {
				visited[y][x + 1] = true;
				visited[y][x] = true;
				visited[y + 1][x] = true;
				makeBoomerang(inx + 1, total + board[y][x + 1] + 2 * board[y][x] + board[y + 1][x]);
				visited[y][x + 1] = false;
				visited[y][x] = false;
				visited[y + 1][x] = false;
			}
			if (leftUp(y, x)) {
				visited[y][x - 1] = true;
				visited[y][x] = true;
				visited[y - 1][x] = true;
				makeBoomerang(inx + 1, total + board[y][x - 1] + 2 * board[y][x] + board[y - 1][x]);
				visited[y][x - 1] = false;
				visited[y][x] = false;
				visited[y - 1][x] = false;
			}
			if (leftDown(y, x)) {
				visited[y][x - 1] = true;
				visited[y][x] = true;
				visited[y + 1][x] = true;
				makeBoomerang(inx + 1, total + board[y][x - 1] + 2 * board[y][x] + board[y + 1][x]);
				visited[y][x - 1] = false;
				visited[y][x] = false;
				visited[y + 1][x] = false;
			}
		}
		makeBoomerang(inx + 1, total);

	}

	private static boolean leftDown(int y, int x) {
		if (y + 1 < N && x - 1 >= 0 && !visited[y + 1][x] && !visited[y][x - 1]) {
			return true;
		}
		return false;
	}

	private static boolean leftUp(int y, int x) {
		if (y - 1 >= 0 && x - 1 >= 0 && !visited[y - 1][x] && !visited[y][x - 1]) {
			return true;
		}
		return false;
	}

	private static boolean rightDown(int y, int x) {
		if (y + 1 < N && x + 1 < M && !visited[y + 1][x] && !visited[y][x + 1]) {
			return true;
		}
		return false;
	}

	private static boolean rightUp(int y, int x) {
		if (y - 1 >= 0 && x + 1 < M && !visited[y - 1][x] && !visited[y][x + 1]) {
			return true;
		}
		return false;
	}
} // end of class
