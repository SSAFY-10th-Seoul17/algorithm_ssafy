import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [boj] 4179. 불!
 */
public class boj4179 {
    static class CoordinateTime {
        /**
         * r, c: 행과 열의 좌표값, time: 도달 시간
         */
        int r, c, time;

        public CoordinateTime(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
    /**
     * R: 미로 행의 수, C: 미로 열의 수, minEscapeTime: 가장 빠른 탈출 시간
     */
    static int R, C, minEscapeTime;
    /**
     * maze: 미로
     * (#: 벽
     * .: 지나갈 수 있는 공간
     * J: 지훈이의 미로에서의 초기위치 (지나갈 수 있는 공간) => 입력에서 하나만 주어진다
     * F: 불이 난 공간)
     */
    static char[][] maze;
    /**
     * dr, dc: 상하좌우 순서의 delta값.
     */
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    /**
     * jihoonQueue: 지훈이의 bfs 진행을 위한 큐, fireQueue: 불의 bfs 진행을 위한 큐
     */
    static Queue<CoordinateTime> jihoonQueue = new ArrayDeque<>(), fireQueue = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        maze = new char[R][C];
        for(int i = 0; i < R; i++) {
            maze[i] = br.readLine().toCharArray();
            for(int j = 0; j < C; j++) {
                if(maze[i][j] == 'F') { // 불의 위치인 경우
                    fireQueue.offer(new CoordinateTime(i, j, 0));
                } else if(maze[i][j] == 'J') { // 지훈이의 위치인 경우
                    jihoonQueue.offer(new CoordinateTime(i, j, 0));
                }
            }
        }

        System.out.println(bfs() ? minEscapeTime : "IMPOSSIBLE");
    }

    /**
     * 같은 시간에 들어간 불과 지훈이의 큐의 사이즈만큼만 bfs 진행.
     * @return
     */
    public static boolean bfs() {
        while(!jihoonQueue.isEmpty()) {
            // 1. 불이 먼저 확산.
            int queueSize = fireQueue.size();
            while(queueSize-- > 0) {
                CoordinateTime now = fireQueue.poll();

                for(int i = 0; i < 4; i++) {
                    int nextR = now.r + dr[i];
                    int nextC = now.c + dc[i];

                    if(!isInMaze(nextR, nextC)) { // 미로를 벗어남
                        continue;
                    }
                    if(maze[nextR][nextC] == '#' || maze[nextR][nextC] == 'F') { // 벽이거나 이미 불이 확산된 공간
                        continue;
                    }

                    maze[nextR][nextC] = 'F';
                    fireQueue.offer(new CoordinateTime(nextR, nextC, now.time + 1));
                }
            }

            // 2. 불을 피해 지훈이가 이동
            queueSize = jihoonQueue.size();
            while(queueSize-- > 0) {
                CoordinateTime now = jihoonQueue.poll();

                for(int i = 0; i < 4; i++) {
                    int nextR = now.r + dr[i];
                    int nextC = now.c + dc[i];

                    if(!isInMaze(nextR, nextC)) { // 미로를 벗어남 => 탈출 성공.
                        minEscapeTime = now.time + 1;
                        return true;
                    }

                    if(maze[nextR][nextC] == '#' || maze[nextR][nextC] == 'J' || maze[nextR][nextC] == 'F') { // 벽이거나 지나왔던 위치이거나 불인 경우
                        continue;
                    }

                    maze[nextR][nextC] = 'J';
                    jihoonQueue.offer(new CoordinateTime(nextR, nextC, now.time + 1));
                }
            }
        }

        return false;
    }

    public static boolean isInMaze(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }
}
