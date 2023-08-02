import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class boj2508{
	
	static String[] cells;
	
	static boolean check(int y, int x, int r, int c, char chr) {
		if (chr == '>') {
			int yy, xx;
			yy = y;
			xx = x + 1;
			if(xx >= c)
				return false;
			if(cells[yy].charAt(xx) != 'o')
				return false;
			
			yy = y;
			xx = x + 2;
			if(xx >= c)
				return false;
			if(cells[yy].charAt(xx) != '<')
				return false;
			return true;
		}else if (chr == 'v') {
			int yy, xx;
			yy = y + 1;
			xx = x;
			if(yy >= r)
				return false;
			if(cells[yy].charAt(xx) != 'o')
				return false;
			
			yy = y + 2;
			xx = x;
			if(yy >= r)
				return false;
			if(cells[yy].charAt(xx) != '^')
				return false;
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int t = Integer.valueOf(br.readLine());
		for(int testCase = 0; testCase < t; testCase++) {
			br.readLine();
			st = new StringTokenizer(br.readLine());
			int r = Integer.valueOf(st.nextToken());
			int c = Integer.valueOf(st.nextToken());
			
			cells = new String[r];
			for(int i = 0; i < r; i++) {
				cells[i] = br.readLine();
			}
			
			int cnt = 0;
			for(int i = 0; i < r; i++) {
				for(int j = 0; j < c; j++) {
					if (cells[i].charAt(j) == '.')
						continue;
					if( check(i, j, r, c, cells[i].charAt(j)) ) {
						cnt++;
					}
				}
			}
			System.out.println(cnt);			
		}
	}
}
