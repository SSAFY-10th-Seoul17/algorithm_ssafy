import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 2508. 사탕 박사 고창영
 */
public class boj2508 {
	static int t, r, c;
	static char[][] boxView;
	static int count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		t = Integer.parseInt(br.readLine());
		
		for(int testCase = 1; testCase <= t; testCase++) {
			br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			boxView = new char[r][c];
			count = 0;
			
			for(int i = 0; i < r; i++) {
				String tempRow = br.readLine();
				for(int j = 0; j < c; j++) {
					boxView[i][j] = tempRow.charAt(j);
				}
			}
			
			for(int i = 0; i < r; i++) {
				for(int j = 0; j < c; j++) {
					if(boxView[i][j] == 'o' && check(i, j)) {
						count++;
					}
				}
			}
			
			sb.append(count).append("\n");
			
		}
		
		System.out.println(sb);			
	}
	
	public static boolean check(int x, int y) {
		boolean leftRightCheck = false, upDwonCheck = false;

		// 좌우 체크 (leftRightCheck)
		int leftY = y - 1, rightY = y + 1;
		if(leftY >= 0 && rightY < c) {
			if(boxView[x][leftY] == '>' && boxView[x][rightY] == '<') {
				leftRightCheck = true;
			}
		}

		
		// 상하 체크 (upDownCheck) - 좌우 체크가 false일 때만
		if(!leftRightCheck) {
			int upX = x -1, downX = x + 1;
			if(upX >= 0 && downX < r) {
				if(boxView[upX][y] == 'v' && boxView[downX][y] == '^') {
					upDwonCheck = true;
				}
			}
		}
		
		return leftRightCheck || upDwonCheck;		
	}
}
