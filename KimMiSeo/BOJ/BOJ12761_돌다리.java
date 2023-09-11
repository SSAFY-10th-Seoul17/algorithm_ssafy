import java.rmi.server.ExportException;
import java.util.*;
import java.io.*;

/**
 * 처음에는 dp로 풀려고 했으나 ,, 답이 계속 나오지 않아서 메모이제이션만 하고, bfs를 사용하는 것으로 노선을 변경하였습니다.
 * 하지만 아직까지도 dp로 왜 불가능한 것인지 알지 못합니다. 🥲
 */
public class BOJ12761_돌다리 {
    static int A,B,N,M; // 힘 , 동규, 주미 위치
    static int[] dp;
    public static void main(String[] args) throws Exception {
        // +1, -1, +a +b , -a, -b *a, *b
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new int[100001];
        Arrays.fill(dp, 200000);
        dp[N] = 0;

        Queue<Integer> q = new ArrayDeque<>();
        q.add(N);

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == M) {
                break;
            }

            int[] moveTo = new int[] {cur + 1, cur - 1, cur + A, cur - A, cur + B, cur - B, cur * A, cur * B};
            for (int i = 0; i < 8; i++) {
                if (moveTo[i] >= 0 && moveTo[i] <= 100000 && dp[moveTo[i]] > dp[cur] + 1){
                    dp[moveTo[i]] = dp[cur] + 1;
                    q.add(moveTo[i]);
                }
            }
        }
        System.out.println(dp[M]);
    }
}
