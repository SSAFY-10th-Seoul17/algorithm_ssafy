import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static String[][] arr = new String[5][5];
	static StringBuilder sb;
	static Set<String> strSet;
	
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	
	static String[] str;
	
	static void dfs(int depth, int y, int x) {
		if(depth == 6) {
			sb = new StringBuilder();
			for(int i = 0; i < 6; i++) {
				sb.append(str[i]);
			}
			strSet.add(sb.toString());
			return;
		}
		for(int i = 0; i < 4; i++) {
			int yy = y + dy[i];
			int xx = x + dx[i];
			
			if (!(0 <= yy && yy < 5 && 0 <= xx && xx < 5)) 
				continue;
			str[depth] = arr[yy][xx];
			dfs(depth + 1, yy, xx);
		}

	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		strSet = new HashSet<String>();

		for(int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++) {
				arr[i][j] = st.nextToken();
			}
		}
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				str = new String[6];
				dfs(0, i, j);
			}
		}
		
		System.out.println(strSet.size());
	}

}
