import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [boj] 14497. 주난의 난
 */
public class boj14497 {
    static class Wave implements Comparable<Wave> {
        /**
         * x: x 좌표
         */
        int x;
        /**
         * y: y 좌표
         */
        int y;
        /**
         * count: 해당 파동이 몇 번째 점프의 파동인지
         */
        int count;

        public Wave(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Wave o) {
            return Integer.compare(this.count, o.count);
        }
    }
    /**
     * N, M: 교실의 크기. 각과 행과 열.
     * startX, startY: 주난이의 위치 좌표
     * endX, endY: 초코바의 위치 좌표
     */
    static int N, M, startX, startY, endX, endY;
    /**
     * map: 교실
     */
    static char[][] map;
    /**
     * visited: 방문 체크
     */
    static boolean[][] visited;
    /**
     * dx, dy: 상하좌우 순서의 delta값.
     */
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        startX = Integer.parseInt(st.nextToken()) - 1;
        startY = Integer.parseInt(st.nextToken()) - 1;
        endX = Integer.parseInt(st.nextToken()) - 1;
        endY = Integer.parseInt(st.nextToken()) - 1;

        map = new char[N][M];
        for(int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        System.out.println(bfs());
    }

    /**
     * 주난이의 위치를 시작점으로 하는 상하좌우 방향의 bfs.
     * 단, 우선순위 큐를 사용하여 count값이 작은 것부터 탐색 진행.
     * 0인 칸을 만나면 위치 값만 수정하여 우선순위 큐에 넣음.
     * 1인 칸을 만나면 위치 값 수정 + count값 1 증가하여 우선순위 큐에 넣음.
     * #인 칸을 만나면 그 때의 count값이 답.(또는 endX, endY를 이용한 좌표 비교로 도출)
     */
    public static int bfs() {
        visited = new boolean[N][M];
        visited[startX][startY] = true;

        PriorityQueue<Wave> pq = new PriorityQueue<>();
        pq.offer(new Wave(startX, startY, 1));

        while(!pq.isEmpty()) {
            Wave nowWave = pq.poll();

            for(int i = 0; i < 4; i++) {
                int nextX = nowWave.x + dx[i];
                int nextY = nowWave.y + dy[i];

                if(!isInBoundary(nextX, nextY) || visited[nextX][nextY]) {
                    continue;
                }

                if(map[nextX][nextY] == '0') {
                    pq.offer(new Wave(nextX, nextY, nowWave.count));
                } else if(map[nextX][nextY] == '1') {
                    pq.offer(new Wave(nextX, nextY, nowWave.count + 1));
                } else if(map[nextX][nextY] == '#') {
                    return nowWave.count;
                }
                visited[nextX][nextY] = true;
            }
        }

        return -1;
    }

    public static boolean isInBoundary(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
