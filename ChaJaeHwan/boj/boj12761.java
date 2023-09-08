import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class boj12761 {

    static int A, B, N, M;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new int[100001];

        System.out.println(bfs(N));

    }

    static int bfs(int n) {
        Queue<Integer> que = new ArrayDeque<>();
        que.add(n);
        while (!que.isEmpty()) {
            int poll = que.poll();
            if (inRange(poll - 1) && dp[poll - 1] == 0) {
                dp[poll - 1] = dp[poll] + 1;
                que.add(poll - 1);
            }
            if (inRange(poll + 1) && dp[poll + 1] == 0) {
                dp[poll + 1] = dp[poll] + 1;
                que.add(poll + 1);
            }
            if (inRange(poll + A) && dp[poll + A] == 0) {
                dp[poll + A] = dp[poll] + 1;
                que.add(poll + A);
            }
            if (inRange(poll - A) && dp[poll - A] == 0) {
                dp[poll - A] = dp[poll] + 1;
                que.add(poll - A);
            }
            if (inRange(poll + B) && dp[poll + B] == 0) {
                dp[poll + B] = dp[poll] + 1;
                que.add(poll + B);
            }
            if (inRange(poll - B) && dp[poll - B] == 0) {
                dp[poll - B] = dp[poll] + 1;
                que.add(poll - B);
            }
            if (inRange(poll * A) && dp[poll * A] == 0) {
                dp[poll * A] = dp[poll] + 1;
                que.add(poll * A);
            }
            if (inRange(poll * B) && dp[poll * B] == 0) {
                dp[poll * B] = dp[poll] + 1;
                que.add(poll * B);
            }
        }

        return dp[M];
    }

    static int findMin(int n) {
        List<Integer> list = new ArrayList<>();
        if (n - 1 > 0) {
            list.add(dp[n - 1] + 1);
        }
        if (n + 1 <= 100000) {
            list.add(dp[n + 1] + 1);
        }
        if (n - A > 0) {
            list.add(dp[n - A] + 1);
        }
        if (n + A <= 100000) {
            list.add(dp[n + A] + 1);
        }
        if (n - B > 0) {
            list.add(dp[n - B] + 1);
        }
        if (n + B <= 100000) {
            list.add(dp[n + B] + 1);
        }
        if (n % A == 0) {
            list.add(dp[n / A] + 1);
        }
        if (n % B == 0) {
            list.add(dp[n / B] + 1);
        }
        return list.stream().min(Integer::compareTo).get();
    }

    static boolean inRange(int n) {
        return 1 <= n && n <= 100000;
    }
}
