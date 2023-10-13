import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [boj] 1584. 게임
 */
public class boj1584 {
    static class Zone implements Comparable<Zone> {
        int x;
        int y;
        int lifeCost; // 해당 Zone을 방문하면 소모하는 생명 값.

        public Zone(int x, int y, int lifeCost) {
            this.x = x;
            this.y = y;
            this.lifeCost = lifeCost;
        }

        @Override
        public int compareTo(Zone o) {
            return Integer.compare(this.lifeCost, o.lifeCost);
        }
    }

    /**
     * N: 위험한 구역의 수
     * M: 죽음의 구역의 수
     */
    static int N, M;
    /**
     * WARNING_ZONE, DEATH_ZONE: 위험한 구역, 죽음의 구역에 대한 상수값.
     */
    static final int WARNING_ZONE = 1, DEATH_ZONE = -1;
    static int[][] map = new int[501][501];
    static boolean[][] visited = new boolean[501][501];
    /**
     * dx, dy: 상하좌우 순서의 delta값.
     */
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static BufferedReader br;

    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));

        // 위험한 구역 설정
        N = Integer.parseInt(br.readLine());
        setZone(N, WARNING_ZONE);

        // 죽음의 구역 설정
        M = Integer.parseInt(br.readLine());
        setZone(M, DEATH_ZONE);

        bfs();

        // 도착지에 방문하지 못했으면 -1 출력.
        System.out.println(visited[500][500] ? map[500][500] : -1);
    }

    /**
     * Zone의 특성(위험, 죽음) 설정을 위한 함수.
     * @param count
     * @param zoneStatus
     * @throws Exception
     */
    public static void setZone(int count, int zoneStatus) throws Exception {
        for(int i = 0; i < count; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            if(x1 > x2) {
                int temp = x1;
                x1 = x2;
                x2 = temp;
            }
            if(y1 > y2) {
                int temp = y1;
                y1 = y2;
                y2 = temp;
            }

            for(int row = x1; row <= x2; row++) {
                for(int col = y1; col <= y2; col++) {
                    map[row][col] = zoneStatus;
                }
            }
        }
    }

    /**
     * 우선순위 큐를 활용한 bfs
     */
    public static void bfs() {
        visited[0][0] = true;
//        map[0][0] = 0;

        PriorityQueue<Zone> pq = new PriorityQueue<>();
        pq.offer(new Zone(0, 0, 0));

        while(!pq.isEmpty()) {
            Zone now = pq.poll();
            int nowLifeCost = now.lifeCost;

            for(int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                // 범위 밖 or 방문한 곳 or 죽음의 구역인 경우 continue
                if(!isInBoundary(nextX, nextY) || visited[nextX][nextY] || map[nextX][nextY] == DEATH_ZONE) {
                    continue;
                }

                visited[nextX][nextY] = true;
                map[nextX][nextY] += nowLifeCost;
                pq.offer(new Zone(nextX, nextY, map[nextX][nextY]));
            }
        }
    }

    public static boolean isInBoundary(int x, int y) {
        return 0 <= x && x < 501 && 0 <= y && y < 501;
    }
}
