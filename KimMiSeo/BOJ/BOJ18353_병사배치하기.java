// 04:16 -
// 📝 

// dp인 것은 인지했지만 정확한 해결 방안이 떠오르지 않았음
// 열외해야하는 병사에 집중하기보다는 오름차순으로 최대의 길이를 구하도록 하는 것이 포인트인 문제였다,, 
import java.util.*;
import java.io.*;
public class BOJ18353_병사배치하기 {
	static int n;
	static int[] powers;
	static int[] dp;
	public static void main(String[] args) throws Exception {
		// n명의 병사, 전투력이 높은 병사가 앞에 오도록 - 내림차순
		// 특정한 위치에 있는 병사를 열외시키는 방법이용
		// 남아있는 병사가 최대가 되도록
		// 열외해야하는 병사의 수 출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		powers = new int[n];
		dp = new int[n];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=n-1; i>=0; i--) {
			powers[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1; // 모두 1로 초기화 시켜는 것도 ,,
		}
		
		for(int i=1; i<n; i++) {
			for (int j=0; j<i; j++) {
				if (powers[i] > powers[j]) { 
					dp[i] = Math.max(dp[j]+1,dp[i]); // j와 나를 포함한 개수 중 max
				}
			}
		}
		
		int max = dp[0];
		for (int i=1; i<n; i++) {
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(n-max);
	}

}
