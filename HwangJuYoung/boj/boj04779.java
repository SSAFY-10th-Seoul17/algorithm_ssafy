import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";
        String[] dp = new String[13];
        dp[0] = "-";
        while ((str = br.readLine()) != null) {
            int N = Integer.parseInt(str);

            for (int i = 0; i < N; i++) {
                StringBuilder sb = new StringBuilder();

                int num = (int) Math.pow(3,i+1);

                for (int j = 0; j < num/3; j++) {
                    sb.append(" ");
                }

                String blank = sb.toString();

                dp[i+1] = dp[i] + blank + dp[i];
            }

            System.out.println(dp[N]);

        }
    }
}