package boj;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//2229번
public class 조짜기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		//나이 순서대로 점수 입력
		int[] score = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < score.length; i++) {
			score[i] = Integer.parseInt(st.nextToken());
		}

		//각각의 조가 잘 짜여진 정도 = 가장 높은 학생 - 가장 낮은 학생
		//전체적으로 잘 짜여진 정도 = 각각의 잘 짜여진 정도의 합
		
		int[] dp = new int[n+1]; // dp[i] : i번째로 잘 짜여진 조, dp[1] = 0
		for (int i = 1; i < dp.length; i++) {
			for (int j = i; j >=1; j--) {
				int max = Math.max(score[i-1], score[j-1]);
				int min = Math.min(score[i-1], score[j-1]);
				
				dp[i] = Math.max(dp[i], dp[j-1] + max - min);
			}
		}
		System.out.println(dp[n]);
	}
}