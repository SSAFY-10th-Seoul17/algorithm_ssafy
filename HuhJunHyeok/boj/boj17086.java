import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [boj] 17086. 아기 상어 2
 */
public class boj17086 {
    static int N, M, maxSafeDistance = 0;
    static int[][] map;
    static boolean[][] visited;
    static Queue<int[]> queue;

    // 상 하 좌 우 좌상 우상 좌하 우하
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        queue = new LinkedList<int[]>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < M; j++) {
                int value = Integer.parseInt(st.nextToken());
                map[i][j] = value;
                if(value == 1) {
                    queue.offer(new int[] {i, j, 0});
                }
            }
        }

        bfs();

        System.out.println(maxSafeDistance);
    }

    public static void bfs() {
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];
            int nowDistance = now[2];

            for(int i = 0; i < 8; i++) {
                int nextX = nowX + dx[i];
                int nextY = nowY + dy[i];
                int nextDistance = nowDistance + 1;

                if(!isInBoundary(nextX, nextY) || visited[nextX][nextY] || map[nextX][nextY] == 1) {
                    continue;
                }

                visited[nextX][nextY] = true;
                maxSafeDistance = maxSafeDistance > nextDistance ? maxSafeDistance : nextDistance;
                queue.offer(new int[] {nextX, nextY, nextDistance});
            }
        }
    }

    public static boolean isInBoundary(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}


//public class boj17086 {
//    static int N, M, maxSafeDistance = 0;
//    static int[][] map;
//    static boolean[][] visited;
//
//    // 상 하 좌 우 좌상 우상 좌하 우하
//    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
//    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};
//
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//        map = new int[N][M];
//        visited = new boolean[N][M];
//        for(int i = 0; i < N; i++) {
//            st = new StringTokenizer(br.readLine(), " ");
//            for(int j = 0; j < M; j++) {
//                map[i][j] = Integer.parseInt(st.nextToken());
//            }
//        }
//
//        for(int i = 0; i < N; i++) {
//            for(int j = 0; j < M; j++) {
//                if(map[i][j] == 0) {
//                    bfs(i, j, 1);
//                }
//            }
//        }
//
//        System.out.println(maxSafeDistance);
//    }
//
//    public static void bfs(int x, int y, int count) {
//        for(int i = 0; i <= N; i++) {
//            for(int j = 0; j < M; j++) {
//                visited[i][j] = false;
//            }
//        }
//        Queue<int[]> queue = new LinkedList<>();
//        queue.offer(new int[] {x, y, count});
//        visited[x][y] = true;
//
//        while(!queue.isEmpty()) {
//            int[] now = queue.poll();
//            int nowX = now[0];
//            int nowY = now[1];
//            int nowCount = now[2];
//
//            for(int i = 0; i < 8; i++) {
//                int nextX = nowX + dx[i];
//                int nextY = nowY + dy[i];
//
//                if(!isInBoundary(nextX, nextY) || visited[nextX][nextY]) {
//                    continue;
//                }
//
//                if(map[nextX][nextY] == 1) {
//                    maxSafeDistance = nowCount > maxSafeDistance ? nowCount : maxSafeDistance;
//                    return;
//                }
//
//                queue.add(new int[] {nextX, nextY, nowCount + 1});
//                visited[nextX][nextY] = true;
//            }
//        }
//    }
//
//    public static boolean isInBoundary(int x, int y) {
//        return 0 <= x && x < N && 0 <= y && y < M;
//    }
//}
