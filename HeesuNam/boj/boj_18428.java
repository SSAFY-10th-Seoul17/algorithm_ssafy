import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

	private static char[][] map;
	private static ArrayList<Plot> teachers;
	private static int[] dy = { -1, 0, 1, 0 };
	private static int[] dx = { 0, 1, 0, -1 };
	private static int N;
	private static ArrayList<Plot> walls;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		teachers = new ArrayList<Plot>();
		walls = new ArrayList<Plot>();
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0, inx = 0; j < N; j++, inx += 2) {
				map[i][j] = str.charAt(inx);
				if (map[i][j] == 'T') {
					teachers.add(new Plot(i, j));
				} else if (map[i][j] == 'X') {
					walls.add(new Plot(i, j));
				}
			}
		}
		barricade(0, 0);
		System.out.println("NO");

	} // end of main

	private static void barricade(int inx, int cnt) {
		if (cnt == 3) {
			for (Plot teacher : teachers) {
				if (Catchable(teacher.y, teacher.x)) {
					return;
				}
			}
			System.out.println("YES");
			System.exit(0);
		}
		for (int i = inx; i < walls.size(); i++) {
			Plot cur = walls.get(i);
			if (map[cur.y][cur.x] == 'X') {
				map[cur.y][cur.x] = 'O';
				barricade(i + 1, cnt + 1);
				map[cur.y][cur.x] = 'X';
			}
		}
	}

	private static boolean rangeChk(int ny, int nx) {
		return ny >= 0 && ny < N && nx >= 0 && nx < N;
	}

	private static boolean Catchable(int y, int x) {
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i], nx = x + dx[i];
			while (rangeChk(ny, nx)) {
				if (map[ny][nx] == 'S') {
					return true;
				} else if (map[ny][nx] == 'O')
					break;
				ny += dy[i];
				nx += dx[i];
			}
		}
		return false;
	}

	static class Plot {
		int y;
		int x;

		public Plot(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}
	}
} // end of class
