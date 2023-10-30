import java.io.*;
import java.util.*;

/**
 * 아이디어 : 주어진 재료(한정됨 1개)로 목표액(H)를 만들 수 있는 경우의 수 => 완탐의 경우, 시간초과 => 냅색문제
 */
public class boj18427 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		List<List<Integer>> ingredients = new ArrayList<>();
		for(int i=0; i<N; i++) ingredients.add(new ArrayList<>());
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			while(st.hasMoreTokens()) ingredients.get(i).add(Integer.parseInt(st.nextToken()));
		}

		// solution
		int[] dp = new int[H+1]; //N명의 학생이 동일한 공간(0-H)까지의 각 결과를 채우는 경우의 수(이전의 결과 저장)를 공유할 것
		dp[0] = 1; //경우의 수를 구하는 경우 0을 만드는 경우의 수를 1로 가정
		for(int i=0; i<N; i++) { //주어진 재료
			for(int j=H; j>=1; j--) { //만들어야 하는 결과까지의 과정(이전의 결과 저장) -> 한정된 수라면 뒤에서부터 저장
				for(int ingredient : ingredients.get(i)) { //재료마다 주어진 재료
					if(j < ingredient) continue;
					dp[j] += dp[j-ingredient];
					dp[j] %= 10007;
				}
			}
		}

		// output
		System.out.println(dp[H]);
	}
}

