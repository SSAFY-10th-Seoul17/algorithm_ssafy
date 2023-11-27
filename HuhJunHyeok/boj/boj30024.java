import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [boj] 30024. 옥수수밭
 */
public class boj30024 {
    static class Corn implements Comparable<Corn> {
        int r;
        int c;
        int value;

        public Corn(int r, int c, int value) {
            this.r = r;
            this.c = c;
            this.value = value;
        }

        @Override
        public int compareTo(Corn o) {
            return Integer.compare(o.value, this.value);
        }
    }
    static int N, M, K;
    static int[][] cornFarm;
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    static PriorityQueue<Corn> cornPriorityQueue = new PriorityQueue<>();
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cornFarm = new int[N][M];
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++) {
                cornFarm[i][j] = Integer.parseInt(st.nextToken());

                if(isFarmBoundary(i, j)) {
                    cornPriorityQueue.offer(new Corn(i, j, cornFarm[i][j]));
                    visited[i][j] = true;
                }
            }
        }

        K = Integer.parseInt(br.readLine());

        bfs();

        System.out.print(sb.toString());
    }

    public static void bfs() {
        while(K-- > 0) {
            Corn now = cornPriorityQueue.poll();

            sb.append(now.r + 1).append(" ").append(now.c + 1).append("\n");

            for(int i = 0; i < 4; i++) {
                int nextR = now.r + dr[i];
                int nextC = now.c + dc[i];

                if(!isInFarm(nextR, nextC) || visited[nextR][nextC]) {
                    continue;
                }

                cornPriorityQueue.offer(new Corn(nextR, nextC, cornFarm[nextR][nextC]));
                visited[nextR][nextC] = true;
            }
        }
    }

    public static boolean isFarmBoundary(int r, int c) {
        return r == 0 || r == N - 1 || c == 0 || c == M - 1;
    }

    public static boolean isInFarm(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}
