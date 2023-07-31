import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj10422 {

    static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int testCase = 0; testCase < T; testCase++) {
            int L = Integer.parseInt(br.readLine());

            dp = new long[L+1];
            System.out.println(solve(L) % 1_000_000_007);
        }
    }////

    private static long solve(int L) {

        if(L == 1){
            return 0;
        }
        if(L == 2){
            return 1;
        }

        if(dp[L] != 0 ){
            return dp[L];
        }

        dp[L] = solve(L-2) * 3 - 1 ;
        return dp[L];
    }
}
