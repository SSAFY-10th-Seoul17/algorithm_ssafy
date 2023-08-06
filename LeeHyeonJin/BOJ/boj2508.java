import java.io.*;
import java.lang.*;
import java.util.*;

public class boj2508 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int t = Integer.parseInt(br.readLine());
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<t; i++) {
			// input
			br.readLine(); // 테스트케이스 구분 '공백'으로 -> 개행문자열을 한번받아서 넘겨야 함
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			char[][] map = new char[n][m];
			for(int r=0; r<n; r++) {
				sb.append(br.readLine());
				for(int c=0; c<m; c++) {
					map[r][c] = sb.charAt(c);
				}
				sb.delete(0, sb.length()); // StringBuilder 초기화
			}

			// solution : o 발견시, 양옆이 >< or 위아래가 ^v 이면 cnt++
			int cnt = 0;
			for(int r=0; r<n; r++) {
				for(int c=0; c<m; c++) {
					if(map[r][c] == 'o') {
						if((r-1 >= 0) && (r+1 < n)) { // 상하
							if((map[r-1][c] == 'v') && (map[r+1][c] == '^')) {
								cnt++;
							}
						}
						if((c-1 >= 0) && (c+1 < m)) { // 좌우
							if((map[r][c-1] == '>') && (map[r][c+1] == '<')) {
								cnt++;
							}
						}
					}
				}
			}
			// output
			bw.write(cnt+"\n");
		}
		bw.flush();
		bw.close();
	}
}
