import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj17484 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];

        for (int row = 0; row < N; row++) {
            st = new StringTokenizer(br.readLine());
            for (int column = 0; column < M; column++) {
                map[row][column] = Integer.parseInt(st.nextToken());
            }
        }
        solve(map);
    }

    private static void solve(int[][] map) {
        int[][][] dp = new int[map.length][map[0].length][3];
        // 현재 최소인 값을 고른다고, 최종 결과가 최소라는 보장이 없다.
        // 따라서, 모든 경우의 수를 dp에 저장해서 풀이한다?

        /**
         * 6 4
         * 5 8 5 1
         * 3 5 8 4
         * 9 77 65 5
         * 2 1 5 2
         * 5 98 1 5
         * 4 95 67 58
         */

        // 첫 줄은 5 8 5 1 로 채운다.
        for (int column = 0; column < map[0].length; column++) {
            dp[0][column][0] = dp[0][column][1] = dp[0][column][2] = map[0][column];
        }

        for (int row = 1; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {
                dp[row][column][0] = dp[row][column][1] = dp[row][column][2] = Integer.MAX_VALUE;
            }
        }

        // 둘째 줄부터 시작한다.
        for (int row = 1; row < map.length; row++) {
            for (int column = 0; column < map[0].length; column++) {

                if (column > 0) {
                    dp[row][column][0] = map[row][column] + Math.min(dp[row - 1][column - 1][1], dp[row - 1][column - 1][2]);
                }
                if (column < map[0].length - 1) {
                    dp[row][column][2] = map[row][column] + Math.min(dp[row - 1][column + 1][0], dp[row - 1][column + 1][1]);
                }
                dp[row][column][1] = map[row][column] + Math.min(dp[row - 1][column][0], dp[row - 1][column][2]);
            }
        }

        int min = Arrays.stream(dp[map.length - 1]).flatMapToInt(Arrays::stream).min().getAsInt();
        System.out.println(min);
    }
}
