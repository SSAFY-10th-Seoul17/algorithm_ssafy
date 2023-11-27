package study11월2주차목;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class boj14226_이모티콘 {
    static int s;
    static int[][] dp;
    static Queue<int[]> q = new LinkedList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s = Integer.parseInt(br.readLine());
        dp = new int[s+1][s+1];
        for (int i = 0; i < dp.length; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);

        dp[1][0] = 0;
        // {현재길이, 클립보드길이, 시간}
        q.add(new int[] {1, 0, 0});
        int len, clen, time;
        int ans = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            int[] now = q.poll();
            len = now[0];
            clen = now[1];
            time = now[2];

            // 클립보드 저장
            if (dp[len][len] > time + 1) {
                dp[len][len] = time + 1;
                q.add(new int[] {len, len, time + 1});
            }
            // -1
            if (len - 1 >= 0) {
                if (dp[len - 1][clen] > time + 1) {
                    dp[len - 1][clen] = time + 1;
                    q.add(new int[]{len - 1, clen, time + 1});
                }
            }
            // 클립보드 붙여넣기
            if (len + clen >= s) {
                ans = Math.min(ans, time + 1 + (len + clen - s));
            } else if (dp[len + clen][clen] > time + 1) {
                dp[len + clen][clen] = time + 1;
                q.add(new int[] {len + clen, clen, time + 1});
            }
        }
        System.out.println(ans);
    }
}