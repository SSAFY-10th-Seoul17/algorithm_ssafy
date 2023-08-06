import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2615 {
	static int[][] arr = new int[19][19];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 배열 입력
		for (int i = 0; i < 19; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for (int j = 0; j < 19; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		boolean flag = false;
		
		int i = 0;
		int j = 0;
		
		fofo:
		for (i = 0; i < 19; i++) {
			for (j = 0; j < 19; j++) {
				if (arr[i][j] == 0) {
					continue;
				} else {
					if (check(i,j)) {
						flag = true;
						break fofo;
					}
					
				}
				
			}
			
		}
		
		if (flag) {
			System.out.println(arr[i][j]);
			System.out.println((i+1) + " " + (j+1));
		} else {
			System.out.println(0);
		}
		
		
		
	}
	private static boolean check(int y, int x) {
		int[] dy = {1,0,-1,1};
		int[] dx = {1,1,1,0};
		
		boolean flag = false;
		for (int i = 0; i < 4; i++) {
			int py = y - dy[i];
			int px = x - dx[i];
			if (px >= 0 && px < 19 && py >= 0 && py <19 && arr[py][px] == arr[y][x]) {
				continue;
			}
			
			px = x;
			py = y;
			
			int val = arr[y][x];
			int cnt = 1;
			while (px >= 0 && px < 19 && py >= 0 && py < 19 && arr[py][px] == val) {
				px = px + dx[i];
				py = py + dy[i];

				if (px >= 0 && px < 19 && py >= 0 && py < 19 && arr[py][px] == val) {
					cnt++;
				}
			}
			
			if (cnt == 5) {
				return true;
			} 
	
			
		}
		return flag;
	}

}
