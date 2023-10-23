import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj2210 {
	static HashSet<String> set;
	static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
	private static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		set = new HashSet<>();
		map = new int[5][5];
		
		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 처음 출발점 지정
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				check(0,"",i,j);
			}
		}
		
		System.out.println(set.size());
	}
	private static void check(int cnt, String res, int x, int y) {
		
		if (cnt == 6) {
			set.add(res);
			return;
		}
		
		for (int i = 0; i < 4; i++) {
			int px = x + dirs[i][0];
			int py = y + dirs[i][1];
			
			if (px >= 0 && px < 5 && py >= 0 && py < 5) {
				check(cnt+1,res+map[px][py],px,py);
			}
		}
	}
	
	
	

}
