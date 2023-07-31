import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj17484 {
    static int N, M;
    static int[][] board;
    static int[][][] dp;
    public static void main(String[] args) {
        solution();
    }
    static void solution() {
        Scanner sc = new Scanner(System.in);
        int answer = 100000000;
        N = sc.nextInt();
        M = sc.nextInt();
        board = new int[N][M];
        dp = new int[N][M][3];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] = sc.nextInt();
            }
        }
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < 3; j++) {
                dp[0][i][j] = board[0][i];
            }
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = safeCompare(new int[]{i-1, j-1, 2}, new int[]{i-1,j,1})+board[i][j];
                dp[i][j][1] = safeCompare(new int[]{i-1, j-1, 2}, new int[]{i-1,j+1,0})+board[i][j];
                dp[i][j][2] = safeCompare(new int[]{i-1, j, 1}, new int[]{i-1,j+1,0})+board[i][j];
            }
        }
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < 3; j++) {
                answer = Math.min(answer, dp[N - 1][i][j]);
            }
        }
        System.out.println(answer);
    }

    static int safeCompare(int[] a, int[] b) {
        int min = 100000000;
        try {
            min = Math.min(min, dp[a[0]][a[1]][a[2]]);
        } catch (Exception e) {

        }
        try {
            min = Math.min(min, dp[b[0]][b[1]][b[2]]);
        } catch (Exception e) {

        }
        return min;
    }

}
