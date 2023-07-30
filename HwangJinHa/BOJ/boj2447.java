import java.io.*;
import java.util.*;

// 별 찍기 - 10

public class boj2447 {
	static char[][] map;
	
	static void make(int u, int l, int size) {
		if (size == 1) {
			return;
		}
		
		size /= 3;
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				int yy = u + y * size;
				int xx = l + x * size;

				if (y == 1 && x == 1)
					makeSpace(yy, yy + size - 1, xx, xx + size - 1);
				else
					make(yy, xx, size);
			}
		}
	}
	
	static void makeSpace(int u, int d, int l, int r) {
		for(int y = u; y <= d; y++) {
			for(int x = l; x <= r; x++) {
				map[y][x] = ' ';
			}
		}
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.valueOf(br.readLine());
		map = new char[n][n];
		for(int i = 0; i < n; i++)
			Arrays.fill(map[i], '*');

		make(0, 0, n);
		
		for(int i = 0; i < n; i++) 
			System.out.println(String.valueOf(map[i]));
			
	}

}
