import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bReader.readLine());

        int[] soldiers = new int[N];
        StringTokenizer st = new StringTokenizer(bReader.readLine(), " ");
        for (int i = 0; i < soldiers.length; i++) {
            soldiers[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];

        dp[0] = 0;

        for (int i = 0; i < dp.length - 1; i++) {
            if (soldiers[i] >= soldiers[i+1]) {
                dp[i+1] = dp[i];
            } else {
                int tmp = i;
                while (tmp > 0 && soldiers[tmp] < soldiers[i+1]) tmp--;
                dp[i+1] = Math.max(dp[tmp] + 1, dp[i] + 1);
            }
        }

        System.out.println(dp[N-1]);

    } // end of main
} // end of class