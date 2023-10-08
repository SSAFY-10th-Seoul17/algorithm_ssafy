import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/***
 * 1. 아이디어
 * 	- 장애물의 어느 높이에 존재하는지 세기
 * 		- 석순 : 위로자람
 * 		- 종유석 : 아래로 자람 (
 * 		- 각 층에서 석순+종유석 (층이 높으면 아래층에도 자라있음) 누적합
 * 	- 최솟값 구하기
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int[] up = new int[H+1];
		int[] down = new int[H+1];
		for (int i = 0; i < N; i++) {
			int barrier = Integer.parseInt(br.readLine());
			if(i%2==0) { // 석순
				up[barrier]++;
			}else { // 종유석
				down[barrier]++;
			}
		}

		for (int i = H-1; i >= 1; i--) {
			up[i]+=up[i+1];
			down[i]+=down[i+1];
		}
		int min = N;
		int cnt = 0;
		for (int i = 1; i <= H; i++) {
			int cntBarrier = up[i]+down[H-i+1];
			if(min > cntBarrier) {
				min = cntBarrier;
				cnt=1;
			}else if(min == cntBarrier){
				cnt++;
			}
		}
		StringBuilder sb = new StringBuilder();
		
		sb.append(min).append(" ").append(cnt);
		System.out.println(sb.toString());
	} // end of main
} // end of class
