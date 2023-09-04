import java.io.*;
import java.util.*;

public class Main {
    public static int[] coin = new int[20];
    public static int[] dp = new int[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int test_case = 0; test_case < T; test_case++) {
            Arrays.fill(coin, 0);
            Arrays.fill(dp, 0);
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < n; i++) {
                coin[i] = Integer.parseInt(st.nextToken());
            }
            int m = Integer.parseInt(br.readLine());
            dp[0] = 1;  // 1원을 1원 동전으로 채울 때 필요한 초기값 설정
            for (int i = 0; i < n; i++) {
                for (int j = 1; j <= m; j++) {  // i번 째 동전으로 m원을 만드는 방법 수를 누적
                    if(j - coin[i] >= 0) dp[j] += dp[j-coin[i]]; // 코인을 넣을 수 있다
                }
            }
            sb.append(dp[m]).append("\n");
        }
        System.out.print(sb);
        br.close();
    }
}
