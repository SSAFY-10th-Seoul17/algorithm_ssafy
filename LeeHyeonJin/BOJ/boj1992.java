import java.io.*;
import java.util.*;
import java.lang.*;

public class boj1992 {
	static int[][] map;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// input
		int n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for(int r=0; r<n; r++) {
			String input = br.readLine();
			for(int c=0; c<n; c++) {
				map[r][c] = input.charAt(c) - '0';
			}
		}

		// solution
		devide(0, 0, n);

		// output
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void devide(int r, int c, int length) {
		if(isSame(r, c, length)) {
			sb.append(map[r][c]);
			return;
		}
		int devidedLength = length/2;
		// 압축조건에 부합하지 않으면, 4등분해서 작업반복 + 괄호 추가
		sb.append("(");
		devide(r, c, devidedLength);
		devide(r, c+devidedLength, devidedLength);
		devide(r+devidedLength, c, devidedLength);
		devide(r+devidedLength, c+devidedLength, devidedLength);
		sb.append(")");
	}

	static boolean isSame(int r, int c, int length) {
		for(int tr=r; tr<r+length; tr++) {
			for(int tc=c; tc<c+length; tc++) {
				if(map[tr][tc] != map[r][c]) {
					return false;
				}
			}
		}
		return true;
	}
}
