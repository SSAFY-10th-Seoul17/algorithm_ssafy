// 06:09 - 
//📝📝 

// 모든 경우를 다 탐색해야겠다는 생각으로 구현하려 했으나 구현하지 못했습니다.
// 검색을 해보고 구현해보았지만 명확한 이해가 되지 않은 상태입니다🥲 
import java.util.*;
import java.io.*;

public class BOJ2624_동전바꿔주기 {
	  static int t, k;
	  static int[][] coins;
	  static boolean[] isSelected;
	  static int[] dp;
	  static int count = 0;
	  private static int[][] dp2;

	  public static void main(String[] args) throws Exception {
	    // k가지 동전, t원의 지폐 -> 동전
	    // 교환 방법 가지수
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    t = Integer.parseInt(br.readLine()); // 금액
	    k = Integer.parseInt(br.readLine()); // 동전의 가지수

	    coins = new int[k][2]; // 동전하나의 금액, 개수 를 저장할 배열
	    dp2 = new int[k + 1][t + 1];
	    dp2[0][0] = 1;
	    for (int i = 1; i <= k; i++) { // 동전 종류
	      StringTokenizer st = new StringTokenizer(br.readLine());
	      int coin = Integer.parseInt(st.nextToken()); // 동전 금액
	      int num = Integer.parseInt(st.nextToken()); // 동전 개수

	      for (int j = 0; j <= t; j++) { // 1~t금액까지
	        dp2[i][j] = dp2[i - 1][j];

	        if (j < coin) { // 현재 금액이 동전 금액보다 작을 때 - 낼 수 없으니까
	          continue;
	        }
	        for (int a = 1; a <= num; a++) { // 동전 개수 만큼
	          if (j - (coin * a) >= 0) {
	            dp2[i][j] = dp2[i][j] + dp2[i - 1][j - (coin * a)];
	          }
	        }
	      }
	    }

	    System.out.println(dp2[k][t]);
	  }

}
