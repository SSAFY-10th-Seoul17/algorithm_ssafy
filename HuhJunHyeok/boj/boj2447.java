import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

/**
 * [boj] 2447. 별 찍기 - 10
 */
public class boj2447 {
	static int n;
	static char[][] starMap;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		starMap = new char[n][n];
		
		for(int i = 0; i < n; i++) {
			Arrays.fill(starMap[i],' '); // 배열을 빈 칸으로 초기화.
		}
		
		starDfs(n, 0, 0);
		
		for(int i = 0; i < n; i++) {
			bw.write(starMap[i]);
			bw.newLine();
		}
		
		bw.flush(); // flush는 마지막에 한 번만.
		bw.close();
	}
	
	public static void starDfs(int size, int x, int y) {
		if(size == 1) {
			starMap[x][y] = '*';
			return;
		}
		
		int newSize = size / 3;
		
		// 9분할 했을 때, (1,1) 부분이 빈칸이다.
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(i == 1 && j == 1) {
					continue;
				} else {
					starDfs(newSize, x + newSize * i, y + newSize * j);
				}
			}
		}
	}
}
