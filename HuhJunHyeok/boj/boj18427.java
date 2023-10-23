import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * [boj] 18427. 함께 블록 쌓기
 */
public class boj18427 {
    /**
     * N: 학생 수, M: 한 학생이 가질 수 있는 블록 수의 최댓값, H: 만들려는 탑의 높이
     */
    static int N, M, H;
    /**
     * studentBlocks: 각 학생이 가진 블록의 높이들
     */
    static ArrayList<Integer>[] studentBlocks;
    static int[][] dp;
    static final int MOD_VALUE = 10_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        studentBlocks = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            studentBlocks[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine(), " ");
            while(st.hasMoreTokens()) {
                int block = Integer.parseInt(st.nextToken());
                studentBlocks[i].add(block);
            }
        }

        dp = new int[N + 1][H + 1];
        dp[0][0] = 1;
        for(int i = 1; i <= N; i++) {
            dp[i][0] = 1; // 높이가 0인 탑을 만드는 경우는 모든 학생이 블록을 쓰지 않는 경우 한 가지.
            for(int height: studentBlocks[i]) { // 현재 학생이 블록을 사용하는 경우
                for(int h = height; h <= H; h++) {
                    dp[i][h] += dp[i - 1][h - height];
                    if(dp[i][h] >= MOD_VALUE) {
                        dp[i][h] %= MOD_VALUE;
                    }
                }
            }
            // 현재 학생이 블록을 사용하지 않는 경우
            for(int j = 1; j <= H; j++) {
                dp[i][j] += dp[i - 1][j];
                if(dp[i][j] >= MOD_VALUE) {
                    dp[i][j] %= MOD_VALUE;
                }
            }

//            for(int j = 1; j <= H; j++) {
//                // 현재 학생이 블록을 사용하지 않는 경우
//                dp[i][j] += dp[i - 1][j];
//                for(int height: studentBlocks[i]) { // 현재 학생이 블록을 사용하는 경우
//                    if(j - height >= 0) {
//                        dp[i][j] += dp[i - 1][j - height];
//                    }
//                }
//                if(dp[i][j] >= MOD_VALUE) {
//                    dp[i][j] %= MOD_VALUE;
//                }
//            }
        }

        System.out.println(dp[N][H]);
    }
}
