import java.io.*;
import java.util.*;

public class boj2210 {
	static String[][] arr = new String[5][5];
	static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	static Set<String> set = new HashSet<String>();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++) {
				arr[i][j] = st.nextToken();
			}
		}
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				dfs(i, j, "");
			}
		}
		System.out.println(set.size());
	}
	public static void dfs(int x, int y, String num) {
		if(num.length() == 6) {
			set.add(num);
			return;
		}
		for(int[] dxy : dir) {
			int dx = x + dxy[0];
			int dy = y + dxy[1];
			if(0 <= dx && dx < 5 && 0 <= dy && dy < 5) {
				dfs(dx, dy, num + arr[x][y]);
			}
		}
	}
}
