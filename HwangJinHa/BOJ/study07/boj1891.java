package study8_3;

import java.io.*;
import java.util.*;

// 사분면
public class boj1891 {
	static int n;
	static long x, y, movex, movey;
	static String d;
	static StringBuilder ans = new StringBuilder();

	static void loc2xy(String d, int depth) {
		char now = d.charAt(d.length()-1);
		d = d.substring(0, d.length()-1);
		
		long move = (long)Math.pow(2, depth);
		
		switch(now) {
		case '1':
			x += move;
			break;
		case '2':
			break;
		case '3':
			y += move;
			break;
		case '4':
			x += move;
			y += move;
		}
		
		if (d.equals(""))
			return;
		
		loc2xy(d, depth + 1);
	}
	static void xy2loc(int depth) {
		long size = (long)Math.pow(2, depth);
		long m = size / 2;
		
		if (x < m) {
			if (y < m) {
				ans.append(2);
			}
			else {
				y -= m;
				ans.append(3);
			}
		}
		else {
			if (y < m) {
				x -= m;
				ans.append(1);
			}
			else {
				y -= m;
				x -= m;
				ans.append(4);
			}
		}
		
		if (depth == 1)
			return;
		
		xy2loc(depth-1);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.valueOf(st.nextToken());
		d = st.nextToken();
		
		st = new StringTokenizer(br.readLine());
		movex = Long.parseLong(st.nextToken());
		// store opposite position since positive value means upper move.
		movey = -Long.parseLong(st.nextToken());
		
		x = 0;
		y = 0;
		loc2xy(d, 0);
		
		x += movex;
		y += movey;
		long size = (long)Math.pow(2, n);
		if (!(0 <= y && y < size && 0 <= x && x < size)) {
			System.out.println(-1);
			System.exit(0);
		}
		xy2loc(n);
		
		System.out.println(ans);
	} // end of main
}
