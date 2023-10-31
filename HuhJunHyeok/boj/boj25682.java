import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * [boj] 25682. 체스판 다시 칠하기 2
 */
public class boj25682 {
    /**
     * N: 보드판의 세로 크기, M: 보드판의 가로 크기, K: 만들려는 체스판의 가로와 세로의 단위 크기
     */
    static int N, M, K, lastRow, lastCol, count;
    static char[][] board;
    static int[][] prefixSum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        lastRow = N - K + 1;
        lastCol = M - K + 1;

        board = new char[N][M];
        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        prefixSum = new int[N + 1][M + 1];

        System.out.println(Math.min(colorize('B'), colorize('W')));
    }

    /**
     * prefix sum 알고리즘 이용.
     * 색칠 필요한 칸 판단하는 식 = 이전 행에서 누적한 경우의 수 + 이번 행의 직전 열까지 누적한 경우의 수 - 중복 경우의 수
     * 누적 합을 구한 2차원 배열에서 K * K 구간 합의 최솟값 구하기
     * @param color
     * @return
     */
    public static int colorize(char color) {
        for(int i = 0; i <= N; i++) {
            Arrays.fill(prefixSum[i], 0);
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                // 같은 색상이면 색칠 안 해도 되는 경우 0, 다른 색상이면 색칠해야 하는 경우 1
                // A ? B ? C : D : E 는 A ? (B ? C : D) : E
                int val = ((i + j) % 2 == 0) ? (board[i][j] == color ? 0 : 1) : (board[i][j] == color ? 1 : 0);

                prefixSum[i + 1][j + 1] = prefixSum[i + 1][j] + prefixSum[i][j + 1] - prefixSum[i][j] + val;
            }
        }

        count = Integer.MAX_VALUE;

        for(int i = 1; i <= lastRow; i++) {
            for(int j = 1; j <= lastCol; j++) {
                int nowCount = prefixSum[i + K - 1][j + K - 1] - prefixSum[i + K - 1][j - 1] - prefixSum[i - 1][j + K - 1] + prefixSum[i - 1][j - 1];
                count = Math.min(count, nowCount);
            }
        }

        return count;
    }
}
