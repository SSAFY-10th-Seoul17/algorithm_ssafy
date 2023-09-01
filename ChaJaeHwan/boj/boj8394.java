import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj8394 {

    static int n;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dp = new int[n + 1];
        if (n == 1) {
            System.out.println(0);
        } else if (n <= 3){
            System.out.println(n);
        } else{
            dp[2] = 2;
            dp[3] = 3;
            for (int i = 4; i <= n ; i++) {
                dp[i] = (dp[i - 1]%10 + dp[i - 2]%10)%10;
            }
            System.out.println(dp[n]);
        }
    }


}
