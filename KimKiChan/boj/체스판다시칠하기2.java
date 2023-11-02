package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//25682
public class 체스판다시칠하기2 {
	private static int[][] bw;
	private static int[][] wb;
	private static int k;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		bw = new int[n][m];//bwbwbw로 시작하는 체스판
		wb = new int[n][m];//wbwbwb로 시작하는 체스판
		for(int i = 0; i < n; i++) {
			String str = br.readLine();
			for(int j = 0; j < m; j++) {
				char c = str.charAt(j);
				
				if((i+j)%2==0) {//i+j합이 짝수
					if(c == 'B') {
						wb[i][j] = 1;
					}else {
						bw[i][j] = 1;
					}
				}else {//i+j합이 홀수
					if(c == 'B') {
						bw[i][j] = 1;
					}else {
						wb[i][j] = 1;
					}
				}
				
			}
		}
				
		//k*k 보드, 다시 칠해야 하는 정사각형 개수의 최소값
		//각 줄 가로, 세로 k개 고를 시 다시 칠해야 하는 개수
		//누적합
		//가로 누적합
		for(int i = 0; i < n; i++) {
			for(int j = 1; j < m; j++) {
				bw[i][j] += bw[i][j-1];
				wb[i][j] += wb[i][j-1];
			}
		}
		//세로 누적합
		for(int j = 0; j < m; j++) {
			for(int i = 1; i < n; i++) {
				bw[i][j] += bw[i-1][j];
				wb[i][j] += wb[i-1][j];
			}
		}
		
		int min = Integer.MAX_VALUE;
		for(int i = k-1; i < n; i++) {
			for(int j = k-1; j < m; j++) {
				int sum = repaint(i, j);
				if(min > sum) {
					min = sum;
				}
			}
		}
		System.out.println(min);
		
	}

	private static int repaint(int x, int y) {
		int sumBW = bw[x][y];
		int sumWB = wb[x][y];
		if(x-k>=0) {
			sumBW -= bw[x-k][y];
			sumWB -= wb[x-k][y];
		}
		if(y-k>=0) {
			sumBW -= bw[x][y-k];
			sumWB -= wb[x][y-k];
		}
		if(x-k>=0 && y-k>=0) {
			sumBW += bw[x-k][y-k];
			sumWB += wb[x-k][y-k];
		}
		return Math.min(sumBW, sumWB);
	}
	
}
