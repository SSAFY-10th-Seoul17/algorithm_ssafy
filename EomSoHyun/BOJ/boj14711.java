import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int n;
	static char[][] map;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		map = new char[n][n];
		String line = br.readLine();
		for (int i = 0; i < n; i++) {
			map[0][i] = line.charAt(i);
		}
		
		int[] dx = {0, -1, 0};
		int[] dy = {-1, 0, 1};
		for (int i = 0; i < n-1; i++) {
			for (int j = 0; j < n; j++) {
				int cnt = 0;
				for (int d = 0; d < 3; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if (0 <= nx && nx < n && 0 <= ny && ny < n) {
						if (map[nx][ny] == '#') cnt++;
					}
				}
				
				if (cnt % 2 == 0) {
					map[i+1][j] = '.';
				} else {
					map[i+1][j] = '#';
				}
				
			}

		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				sb.append(map[i][j]);
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
		
	}

}
