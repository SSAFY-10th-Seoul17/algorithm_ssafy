import java.util.*;
import java.io.*;
// 📝 

// 잘 모르겠어서 블로그 찾아봤습니다!
// 문제를 읽고 dp로 풀어야겠다는 생각을 하긴했습니다. 
// dp[i-3]*2, dp[i-4]*3 까지 밖에 생각하지 못했습니다. dp를 long 배열로 선언하는 것도 생각하지 못했던 것같습니다.

// 테스트 케이스를 보지 않고 long으로 선언해야겠다는 판단을 어떻게 하는지 궁금합니다. 

public class BOJ11058_크리보드 {
	static int n;
	static long[] dp;
	public static void main(String[] args) throws Exception {
		// 버튼 4개, 버퍼가 비어있지 않은 경우에 
		// n번 눌러서 출력된 a의 개수 최대
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		dp = new long[n+1];
		
		for (int i=1; i<=n; i++) {
			dp[i] = dp[i-1]+1;
			
			if (i>6) {
				for (int j=2; j<5; j++) {
					dp[i] = Math.max(dp[i], dp[i-(j+1)]*j);
				}
			}
		}
		System.out.println(dp[n]);
	}
}
