import java.io.*;
import java.util.*;

public class boj18427 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st.nextToken();
        int h = Integer.parseInt(st.nextToken());

        int[] dp = new int[h + 1];

        List<Integer>[] block = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            block[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()){
                block[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = h; j >= 0; j--) {
                for (int k : block[i]) {
                    if(j - k >= 0){
                        dp[j] += dp[j - k];
                        dp[j] %= 10007;
                    }
                }
            }
        }

        System.out.println(dp[h]);
    }
}
