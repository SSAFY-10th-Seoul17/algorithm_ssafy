import java.io.*;
import java.util.*;

public class Main{
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        long[] dp = new long[N + 1];
        for(int i = 0; i <= N; i++){
            dp[i] = i;
            for(int j = i - 2; j >= 2; j--) dp[i] = Math.max(dp[i], dp[j] * (i - j - 1));
        }
        System.out.println(dp[N]);
    }
}