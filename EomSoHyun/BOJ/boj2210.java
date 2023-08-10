import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] map = new int[5][5];
	static HashSet<Integer> result = new HashSet<Integer>();
	static int[] dx = {0, 0, 1, -1};
	static int[] dy = {1, -1, 0, 0};
	
	public static void makeNums(int x, int y, int cnt, double num) {
		if (cnt == 6) {
			result.add((int)num);
			return;
		}
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (!(0 <= nx && nx < 5 && 0 <= ny && ny < 5 )) continue;
			makeNums(nx, ny, cnt+1, num+map[nx][ny]*(Math.pow(10, cnt)));
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 입력 받기 
		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				makeNums(i, j, 0, 0);
			}
		}
		System.out.println(result.size());
		
		
		
	}  // end of main
}  // end of class
