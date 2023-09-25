import java.io.*;
import java.util.*;

/*
 * 아이디어 :
 * 분할-정복이다 : 현재의 종이를 동일한 규칙을 가지고 계속해서 분할(9등분) & 정복(모두 같은 숫자를 가졌는지 확인)
 * 멈추는 시점 : 현재의 종이를 구성하고 있는 숫자가 모두 동일할 때 -> 현재 종이의 숫자 카운팅 + 1
 */
public class boj1780 {
	static int[][] paper;
	static int[] cnt = new int[3]; // -1,0,1의 개수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		int N = Integer.parseInt(br.readLine());
		paper = new int[N][N];
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// solution
		divideConquer(N, 0, 0);

		// output
		for(int c : cnt) {
			System.out.println(c);
		}
	}

	static void divideConquer(int len, int sr, int sc) {
		if(len == 1) {
			cnt[paper[sr][sc]+1]++;
			return;
		}
		int[][] temp = new int[len][len];
		for(int r=0; r<len; r++) {
			for(int c=0; c<len; c++) {
				temp[r][c] = paper[r+sr][c+sc];
			}
		}
		if(check(temp)) {
			cnt[temp[0][0]+1]++;
			return;
		}
		int newLen = len/3;
		divideConquer(newLen, sr, sc);
		divideConquer(newLen, sr, sc+newLen);
		divideConquer(newLen, sr, sc+2*newLen);
		divideConquer(newLen, sr+newLen, sc);
		divideConquer(newLen, sr+newLen, sc+newLen);
		divideConquer(newLen, sr+newLen, sc+2*newLen);
		divideConquer(newLen, sr+2*newLen, sc);
		divideConquer(newLen, sr+2*newLen, sc+newLen);
		divideConquer(newLen, sr+2*newLen, sc+2*newLen);
	}

	static boolean check(int[][] paper) {
		boolean possible = true;
		for(int i=0; i<paper.length; i++) {
			for(int j=0; j<paper[0].length; j++) {
				if(paper[i][j] != paper[0][0]) {
					possible = false;
				}
			}
		}
		return possible;
	}
}
