import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [boj] 20046. Road Reconstruction
 */
public class boj20046 {
    /**
     * m: 행, n: 열
     */
    static int m, n;
    /**
     * map: 도시를 의미하는 격자
     * 각 열의 정보는 정수 0, 1, 2, -1 로 나타내며
     * 0 은 단위도로가 이미 존재하는 것을, 즉, 도로에 유실이 없는 상태,
     * 1 은 단위 도로가 없고 1 의 비용으로 도로를 건설할 수 있는 단위 격자,
     * 2 는 단위 도로가 없고 2 의 비용으로 도로를 건설할 수 있는 단위 격자를 의미한다.
     * -1 은 X로 표시된 단위 격자로 그 위치에 단위 도로를 건설할 수 없는 상태를 의미한다.
     *
     * cost: 필요 비용
     */
    static int[][] map, cost;
    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};
    static final int MAX = Integer.MAX_VALUE;
    static class Vertex implements Comparable<Vertex> {
        int r;
        int c;
        int cost;

        public Vertex(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[m + 1][n + 1];
        cost = new int[m + 1][n + 1];
        for(int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                cost[i][j] = MAX;
            }
        }

        if(map[1][1] == - 1 || map[m][n] == -1) {
            System.out.println(-1);
            return;
        }

        dijkstra();

        System.out.println(cost[m][n] == MAX ? -1 : cost[m][n]);
    }

    public static void dijkstra() {
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        pq.offer(new Vertex(1, 1, map[1][1]));

        while(!pq.isEmpty()) {
            Vertex now = pq.poll();

            if(cost[now.r][now.c] < now.cost) {
                continue;
            }

            if(now.r == m && now.c == n) {
                return;
            }

            for(int i = 0; i < 4; i++) {
                int nextR = now.r + dr[i];
                int nextC = now.c + dc[i];

                if(!isIn(nextR, nextC) || map[nextR][nextC] == -1) {
                    continue;
                }

                if(cost[nextR][nextC] > now.cost + map[nextR][nextC]) {
                    cost[nextR][nextC] = now.cost + map[nextR][nextC];

                    pq.offer(new Vertex(nextR, nextC, cost[nextR][nextC]));
                }
            }
        }
    }

    public static boolean isIn(int r, int c) {
        return 1 <= r && r <= m && 1 <= c && c <= n;
    }
}
