import java.io.*;
import java.util.*;

public class boj17484 {
	static int result;
	static int n, m;
	static int[][] map;
	static int temp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		result = Integer.MAX_VALUE;
		temp = 0;
	}
	public static void dfs(int depth, int idx, int temp, String dir) {
		if(depth == n) {
			if(result > temp) {
				result = temp;
			}
			return;
		}
		temp += map[depth][idx];
		if(dir.equals("r")) {
			
		}
	}
}
