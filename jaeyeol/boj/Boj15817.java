import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15817 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int size = Integer.parseInt(st.nextToken());
        int[] dp = new int[size + 1];
        dp[0] = 1;

        while (n-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int length = Integer.parseInt(st.nextToken());
            int amount = Integer.parseInt(st.nextToken());

            for (int i = size; i >= length; i--) {
                for (int j = 1; j <= amount; j++) {
                    int l = length * j;
                    if (l > i) {
                        break;
                    }
                    dp[i] += dp[i - l];
                }
            }
        }

        System.out.println(dp[size]);
    }

}

