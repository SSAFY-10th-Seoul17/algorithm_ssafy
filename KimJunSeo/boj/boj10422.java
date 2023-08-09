
import java.util.*;

public class boj10422 {
    static int test_case;
    static int[] cases;
    static int[] dp;
    static int mod = 1_000_000_007;
    public static void main(String[] args) {
        init();
        for(int tc:cases){
            System.out.println(solution(tc));
        }
    }
    static void init() {
        Scanner sc = new Scanner(System.in);
        test_case = sc.nextInt();
        cases = new int[test_case];
        dp = new int[5001];
        for (int i = 0; i < test_case; i++) {
            cases[i] = sc.nextInt();
        }
        dp[0] = 1;
        dp[2] = 1;
        for (int i = 4; i < 5001; i+=2) {
            long sum = 0;
            for (int j = 1; j <=i/2; j ++) {
                sum += (long)(dp[i-2*j]) * (long)(dp[2*j-2]);
                sum%=mod;
            }
            dp[i] = (int)sum;
        }
    }

    static int solution(int N) {
        if (N % 2 == 1) {
            return 0;
        }
        return dp[N];
    }

}