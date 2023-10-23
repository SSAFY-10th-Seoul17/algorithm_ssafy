import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [boj] 16197. 두 동전
 */
public class boj16197 {
    static class Coin {
        int r;
        int c;

        public Coin(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    static class CoinsPositionCount {
        int r1;
        int c1;
        int r2;
        int c2;
        int pressCount;

        public CoinsPositionCount(int r1, int c1, int r2, int c2, int pressCount) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.pressCount = pressCount;
        }
    }
    /**
     * N: 보드의 세로 크기, M: 보드의 가로 크기
     */
    static int N, M, minPressCount = -1;
    /**
     * map: N*M 크기의 보드
     */
    static char[][] map;
    /**
     * coins: 코인
     */
    static Coin[] coins = new Coin[2];
    static boolean[][][][] visited;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        int coinIdx = 0;
        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);

                if(map[i][j] == 'o') {
                    coins[coinIdx++] = new Coin(i, j);
                }
            }
        }

        bfs();

        System.out.println(minPressCount);
    }

    public static void bfs() {
        Queue<CoinsPositionCount> queue = new ArrayDeque<>();
        queue.offer(new CoinsPositionCount(coins[0].r, coins[0].c, coins[1].r, coins[1].c, 0));

        visited = new boolean[N][M][N][M];
        visited[coins[0].r][coins[0].c][coins[1].r][coins[1].c] = true;

        while(!queue.isEmpty()) {
            CoinsPositionCount now = queue.poll();

            if(now.pressCount >= 10) {
                return;
            }

            for(int i = 0; i < 4; i++) {
                int nextR1 = now.r1 + dr[i];
                int nextC1 = now.c1 + dc[i];
                int nextR2 = now.r2 + dr[i];
                int nextC2 = now.c2 + dc[i];

                int dropCount = 0; // 떨어진 않은 동전의 수
                if(isInMap(nextR1, nextC1) && map[nextR1][nextC1] == '#') {
                    nextR1 = now.r1;
                    nextC1 = now.c1;
                }
                if(!isInMap(nextR1, nextC1)) {
                    dropCount++;
                }
                if(isInMap(nextR2, nextC2) && map[nextR2][nextC2] == '#') {
                    nextR2 = now.r2;
                    nextC2 = now.c2;
                }
                if(!isInMap(nextR2, nextC2)) {
                    dropCount++;
                }

                if(dropCount == 1) {
                    minPressCount = now.pressCount + 1;
                    return;
                }
                if(dropCount == 0 && !visited[nextR1][nextC1][nextR2][nextC2]) {
                    queue.offer(new CoinsPositionCount(nextR1, nextC1, nextR2, nextC2, now.pressCount + 1));
                    visited[nextR1][nextC1][nextR2][nextC2] = true;
                }
            }
        }
    }

    public static boolean isInMap(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}
