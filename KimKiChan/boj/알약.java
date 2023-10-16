package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;

//4811번
public class 알약 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		//알약의 개수
		int n = Integer.parseInt(br.readLine());
//		i일, 남은 알약의 수, 남은 반쪽 알약의 수
//		long[][][] len = new long[61][31][31];
		long[][][] len;
		
		while(n!=0) {
			len = new long[2*n+1][n+1][n+1];
			len[0][n][0] = 1;
			for(int day = 1; day <= 2*n; day++) {
				for(int whole = n; whole >= 0; whole--) {
					for(int half = 0; half <= n-whole; half++) {
						//half를 먹음 -> half만 1 사라짐
						if(half+1<=n) {
							len[day][whole][half] += len[day-1][whole][half+1];
						}
						//whole를 먹음 -> whole은 하나 줄어들고, half는 하나 는다
						if(whole+1<=n && half-1>=0) {
							len[day][whole][half] += len[day-1][whole+1][half-1];
						}
					}
				}
			}
			
			sb.append(len[2*n][0][0]).append("\n");
			n = Integer.parseInt(br.readLine());
		}
		
		System.out.println(sb);
	}
}
