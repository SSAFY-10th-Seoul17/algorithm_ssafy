package study9_1;

import java.awt.Point;
import java.io.*;
import java.util.*;

// 감시 피하기 
public class boj18428{
	static int n;
	static int nSquare;
	static char[][] cells;
	static boolean[][] blocks;
	static ArrayList<Point> teachers = new ArrayList<Point>();
	static int size;
	
	static final int[] dy = {0, 1, 0, -1};
	static final int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	n = Integer.valueOf(br.readLine());
    	cells = new char[n][n];
    	blocks = new boolean[n][n];
    	
    	for (int i = 0; i < n; i++) {
    		st = new StringTokenizer(br.readLine());
    		for  (int j = 0; j < n; j++) {
    			cells[i][j] = st.nextToken().charAt(0);
    			if (cells[i][j] == 'T') {
    				teachers.add(new Point(j, i));
    			}
    		}
    	}
    	size = teachers.size();
    	
    	dfs(0, -1, 0);
    	System.out.println("NO");
    } // end of main

	private static void dfs(int y, int x, int depth) {
		if (depth == 3) {
			if (simulate()) {
				System.out.println("YES");
				System.exit(0);
			}
			return;
		}
		
		for (int j = x + 1; j < n; j++) {
			if (placable(y, j)) {
				blocks[y][j] = true;
				dfs(y, j, depth + 1);
				blocks[y][j] = false;
			}
		}
		for (int i = y + 1; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (placable(i, j)) {
					blocks[i][j] = true;
					dfs(i, j, depth + 1);
					blocks[i][j] = false;
				}
			}
		}
	}

	private static boolean placable(int y, int x) {
		return cells[y][x] == 'X';
	}

	private static boolean simulate() {
		for (Point teacher : teachers) {
			int y = teacher.y;
			int x = teacher.x;
			
			for (int d = 0; d < 4; d++) {
				for (int i = 1; i <= n; i++) {
					int yy = y + dy[d] * i;
					int xx = x + dx[d] * i;
					
					if (inBoundary(yy, xx)) {
						if (blocks[yy][xx]) {
							break;
						}
						if (cells[yy][xx] == 'S') {
							return false;
						}
					}
					else {
						break;
					}
				}
			}
		}
		return true;
	}

	private static boolean inBoundary(int yy, int xx) {
		return (0 <= yy && yy < n && 0 <= xx && xx < n);
	}
}
