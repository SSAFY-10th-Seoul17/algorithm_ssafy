import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1. 아이디어
 * 분할정복
 * 	- 종이검사(-1,0,1)
 * 	- 아닐경우 
 * 		(y,x,1/3) (y,x+1/3,1/3) (y,x+2/3,1/3)
 * 		(y+1/3,x,1/3) (y+1/3,x+1/3,1/3) (y+1/3,x+2/3,1/3)
 * 		(y+2/3,x,1/3) (y+1/3,x+2/3,1/3) (y+2/3,x+2/3,1/3)
 * 	나누기
 */
public class Main {
	private static int[] papers; // -1,0,1 종이의 개수
	private static int N;
	private static int[][] board;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		papers = new int[3];
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cntPaper(0,0,N);
		for(int paper:papers)
			System.out.println(paper);
	}

	private static void cntPaper(int y, int x, int size) {
		int standard = board[y][x]; // 기준

		if(chkPaper(y, x, size, standard)) {
			papers[standard+1]++;
			return;
		}
		int newSize = size * 1/3;
		int[] rangeY = {y,y+newSize,y+2*newSize};
		int[] rangeX = {x,x+newSize,x+2*newSize};
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				cntPaper(rangeY[i],rangeX[j],newSize);
			}
		}
	}

	private static boolean chkPaper(int y, int x, int size, int standard) {
		for (int i = y; i < y+size; i++) {
			for (int j = x; j < x+size; j++) {
				if(standard!=board[i][j])return false;
			}
		}
		return true;
	}
}
