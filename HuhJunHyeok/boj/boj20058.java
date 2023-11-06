import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [boj] 20058. 마법사 상어와 파이어스톰
 */
public class boj20058 {
    static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    /**
     * N: 격자판의 크기는 2^N * 2^N
     * Q: 파이어스톰 시전 횟수
     * boardSize: 격자판 줄의 수
     * fireStormLevel: 파이어스톰 단계
     * fireStormSize: 파이어스톰 단계에 따름 범위
     * totalIceCount: 남아있는 얼음의 합
     * biggestIceCoverage: 가장 큰 얼음 덩어리가 차지하는 칸의 수
     */
    static int N, Q, boardSize = 1, fireStormLevel, fireStormSize, totalIceCount, biggestIceCoverage;
    static int[][] board;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
            boardSize *= 2;
        }
        board = new int[boardSize][boardSize];
        for(int i = 0; i < boardSize; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < boardSize; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < Q; i++) {
            fireStormSize = 1;
            fireStormLevel = Integer.parseInt(st.nextToken());

            board = divideAndRotateBoard(fireStormLevel);
            board = meltIce();
        }

        getTotalIceCountAndBiggestIceCoverage();

        sb.append(totalIceCount).append("\n").append(biggestIceCoverage).append("\n");

        System.out.print(sb.toString());
    }

    public static int[][] divideAndRotateBoard(int level) {
        int[][] tempBoard = new int[boardSize][boardSize];

        for(int i = 0; i < level; i++) {
            fireStormSize *= 2;
        }

        for(int i = 0; i < boardSize; i += fireStormSize) {
            for(int j = 0; j < boardSize; j += fireStormSize) {
                rotateBoard(tempBoard, i, j, fireStormSize);
            }
        }

        return tempBoard;
    }

    public static void rotateBoard(int[][] tempBoard, int r, int c, int size) {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                tempBoard[r + i][c + j] = board[r + size - 1 - j][c + i];
            }
        }
    }

    public static int[][] meltIce() {
        int[][] tempBoard = new int[boardSize][boardSize];
        for(int i = 0; i < boardSize; i++) {
            System.arraycopy(board[i], 0, tempBoard[i], 0, boardSize);
        }

        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                int existIceCount = 0;

                if(board[i][j] == 0) {
                    continue;
                }

                for(int k = 0; k < 4; k++) {
                    int nextR = i + dr[k];
                    int nextC = j + dc[k];

                    if(!isInBoard(nextR, nextC) || board[nextR][nextC] == 0) {
                        continue;
                    }

                    existIceCount++;
                }

                if(existIceCount < 3) {
                    tempBoard[i][j]--;
                }
            }
        }

        return tempBoard;
    }

    public static void getTotalIceCountAndBiggestIceCoverage() {
        visited = new boolean[boardSize][boardSize];

        Queue<Point> queue = new ArrayDeque<>();

        for(int i = 0; i < boardSize; i++) {
            for(int j = 0; j < boardSize; j++) {
                totalIceCount += board[i][j];

                if(board[i][j] == 0 || visited[i][j]) {
                    continue;
                }

                queue.offer(new Point(i, j));
                visited[i][j] = true;

                int nowIceCoverage = 1;
                while(!queue.isEmpty()) {
                    Point now = queue.poll();

                    for(int k = 0; k < 4; k++) {
                        int nextR = now.r + dr[k];
                        int nextC = now.c + dc[k];

                        if(!isInBoard(nextR, nextC) || board[nextR][nextC] == 0 || visited[nextR][nextC]) {
                            continue;
                        }

                        queue.offer(new Point(nextR, nextC));
                        visited[nextR][nextC] = true;
                        nowIceCoverage++;
                    }
                }
                biggestIceCoverage = Math.max(biggestIceCoverage, nowIceCoverage);
            }
        }
    }

    public static boolean isInBoard(int r, int c) {
        return 0 <= r && r < boardSize && 0 <= c && c < boardSize;
    }
}
