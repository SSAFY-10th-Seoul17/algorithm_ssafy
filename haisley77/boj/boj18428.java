import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class boj18428 {

	private static char[][] map;
	private static int N, res;
	private static ArrayList<int[]> teachers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		teachers = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String s =  br.readLine();
			for (int j = 0, index = 0; j < N; j++, index+=2) {
				map[i][j] = s.charAt(index);
				if (map[i][j] == 'T') teachers.add(new int[] {i,j});
			}
		}
		makeCase(0);
		System.out.println(res == 1? "YES" : "NO");
	}
	
	public static void makeCase(int cnt) {	
		if (cnt == 3) {
			if (check(map)) res = 1;
			return;
		}
		
		for (int i = 0; i < N * N; i++) {
			int r = i/N;
			int c = i%N;
			if (map[r][c] == 'X') {
				map[r][c] = 'O';
				makeCase(cnt+1);
				map[r][c] = 'X';
			}
		}
	}

	private static boolean check(char[][] map) {
		
		for (int i = 0; i < teachers.size(); i++){
			int[] t = teachers.get(i);
			int r = t[0];
			int c = t[1];
			// 상
			int pr = r, pc = c;
			while (pr >= 0 && map[pr][pc] != 'O') {
				if (map[pr][pc] == 'S') {
					return false;
				}
				pr--;
			}
			// 하
			pr = r; pc = c;
			while (pr < N && map[pr][pc] != 'O') {
				if (map[pr][pc] == 'S') {
					return false;
				}
				pr++;
			}
			// 좌
			pr = r; pc = c;
			while (pc >= 0&& map[pr][pc] != 'O') {
				if (map[pr][pc] == 'S') {
					return false;
				}
				pc--;
			}
			// 우
			pr = r; pc = c;
			while (pc < N && map[pr][pc] != 'O') {
				if (map[pr][pc] == 'S') {
					return false;
				}
				pc++;
			}
		}
		return true;
	}

}
