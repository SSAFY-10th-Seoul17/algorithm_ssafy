import java.util.*;
public class boj14606 {
    static int N;
    static int[] dp;
    static boolean[] visited;
    public static void main(String[] args) {
        init();
        System.out.println(joy(N));
    }

    static void init() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        dp = new int[N + 1];
        visited = new boolean[N + 1];
    }

    static int joy(int N) {
        if (visited[N]) {
            return dp[N];
        }
        int half = N/2;
        visited[N]=true;
        return dp[N] = joy(half) + joy(N - half) +(half*(N-half));
    }
}
