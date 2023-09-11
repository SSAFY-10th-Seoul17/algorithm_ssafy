import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 위험 구역과 죽음 구역을 담고있는 이차원 배열을 만들어서 모든 경우를 다 해본 후에 최솟값을 구하면 된다고 생각했습니다.
 * 모든 경우의 수를 해보려고 dfs를 시도했으나, 오버플로우가 나서 해당 방법으로는 풀지 못한다는 것을 깨달았습니다.
 * 이후에는 가중치를 가지는 그래프에서 최솟값을 구하는 문제라고 생각해서 다익스트라에서 사용하는 pq를 사용하면 좋겠다는 생각을 했습니다.
 * 노드 생성자를 잘못 작성하고, 기저조건의 위치를 잘못 선택하는 이슈가 있었으나 ,, 조언을 얻어 해결할 수 있었습니다.
 */

public class BOJ1584_게임 {
    static int N, M, result;
    static int[][] graph = new int[501][501];

    static class Node implements Comparable<Node> {
        int r, c, val;

        public Node(int r, int c, int val) {
            this.r = r;
            this.c = c;
            this.val = val;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.val, o.val);
        }
    }

    public static void main(String[] args) throws Exception {
        // 죽음의 구역, 위험 구역 -1
        // 잃는 생명의 최솟값
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // danger : 1
        N = Integer.parseInt(br.readLine());
        int startDangerx, startDangery, endDangerx, endDangery;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            startDangerx = Math.min(x1, x2);
            startDangery = Math.min(y1, y2);
            endDangerx = Math.max(x1, x2);
            endDangery = Math.max(y1, y2);

            for (int j = startDangerx; j <= endDangerx; j++) {
                for (int k = startDangery; k <= endDangery; k++) {
                    graph[j][k] = 1;
                }
            }
        }

        // death : 2
        M = Integer.parseInt(br.readLine());
        int startDeathx, startDeathy, endDeathx, endDeathy;
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            startDeathx = Math.min(x1, x2);
            startDeathy = Math.min(y1, y2);
            endDeathx = Math.max(x1, x2);
            endDeathy = Math.max(y1, y2);

            for (int j = startDeathx; j <= endDeathx; j++) {
                for (int k = startDeathy; k <= endDeathy; k++) {
                    graph[j][k] = 2;
                }
            }
        }

        result = Integer.MAX_VALUE;
        // 0: 갈 수 있음 | 1 : 갈 수 있으나 , -1 | 2 : 갈 수 없음.
        visited = new boolean[501][501];

        bfs(0, 0, 0);
        if (flag) {
            System.out.println(result);
        } else {
            System.out.println(-1);
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static boolean[][] visited;
    static boolean flag;
    private static void bfs(int r, int c, int val) {
        flag = false;
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(r, c, val));

        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.r == 500 && cur.c == 500) {
                flag = true;
                result = cur.val;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                int nval = cur.val;
                if (nr >= 0 && nr < 501 && nc >= 0 && nc < 501 && !visited[nr][nc] && graph[nr][nc] != 2) {
                    visited[nr][nc] = true;
                    if (graph[nr][nc] == 1) {
                        nval++;
                        q.offer(new Node(nr, nc, nval));
                    } else {
                        q.offer(new Node(nr, nc, nval));
                    }
                }
            }
        }
    }
}
