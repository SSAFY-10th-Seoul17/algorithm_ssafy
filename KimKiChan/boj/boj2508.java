import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2508 {
	static char[][] candy;
	static int r;
	static int c;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCase;

		testCase = Integer.parseInt(br.readLine());
		
		for(int t = 0 ; t < testCase; t++) {
       		br.readLine();
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
//			System.out.println(testCase + " " + r + " " + c);
			candy = new char[r][c];
			
			for(int i = 0; i < r; i++) {
				char[] ch = br.readLine().toCharArray();
				for(int j = 0; j < c; j++) {
					candy[i][j] = ch[j];
				}
			}
			
			findCandy();
		}
		
	}
	
	static public void findCandy() {
		int cnt = 0;
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				if(candy[i][j] == 'o') {
					if(checkCandy(i, j)) {
						cnt++;
					}
				}
			}
		}
		System.out.println(cnt);
	}
	
	static public boolean checkCandy(int x, int y) {
//		상하
		if(x-1 >= 0 && x+1 < r) {
			if(candy[x-1][y] == 'v' && candy[x+1][y] == '^') {
				return true;
			}
		}
		
//		좌우
		if(y -1 >= 0 && y + 1 < c) {
			if(candy[x][y-1] == '>' && candy[x][y+1] == '<') {
				return true;
			}
		}	
		
		return false;
	}
	
}
