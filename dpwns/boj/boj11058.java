import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[] dp = new long[n+1];
        for (int i = 1; i <= 6 && i <= n; i++) {
            dp[i] = i;
        }
        for (int i = 7; i <= n; i++) {
            for (int j = 3; j < i; j++) {   // 3번째 뒤부터 새로운 복사 vs 그 이전꺼를 복사해서 재사용
                dp[i] = Math.max(dp[i], dp[i-j] * (j - 1));
            }
        }
        System.out.println(dp[n]);
        br.close();
    }
}
