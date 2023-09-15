import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj1584 {


    static class Node implements Comparable<Node> {
        int r, c, life;

        public Node(int r, int c, int life) {
            this.r = r;
            this.c = c;
            this.life = life;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.life, o.life);
        }
    }

    static int N, M, answer = Integer.MAX_VALUE;
    static int[][] graph = new int[501][501];
    static boolean[][] visited = new boolean[501][501];
    static int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int j = Math.min(x1, x2); j <= Math.max(x1, x2); j++) {
                for (int k = Math.min(y1, y2); k <= Math.max(y1, y2); k++) {
                    graph[j][k] = 1;
                }
            }
        }

        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int j = Math.min(x1, x2); j <= Math.max(x1, x2); j++) {
                for (int k = Math.min(y1, y2); k <= Math.max(y1, y2); k++) {
                    graph[j][k] = 2;
                }
            }
        }

        bfs();

        System.out.println(Integer.MAX_VALUE == answer ? -1 : answer);

    }

    static void bfs() {
        PriorityQueue<Node> que = new PriorityQueue<>();
        que.add(new Node(0, 0, 0));
        visited[0][0] = true;

        while (!que.isEmpty()) {
            Node poll = que.poll();
            for (int j = 0; j < 4; j++) {
                int nr = poll.r + dir[j][0];
                int nc = poll.c + dir[j][1];

                if (inGraph(nr, nc) && !visited[nr][nc] && graph[nr][nc] != 2) {
                    que.add(new Node(nr, nc, poll.life + graph[nr][nc]));
                    visited[nr][nc] = true;
                    if (nr == 500 && nc == 500) {
                        answer = Math.min(answer, poll.life + graph[nr][nc]);
                    }
                }
            }
        }

    }
    static boolean inGraph(int r, int c) {
        return 0 <= r && r <= 500 && 0 <= c && c <= 500;
    }

}
