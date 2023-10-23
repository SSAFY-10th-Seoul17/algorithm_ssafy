import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj17086 {

    static class Node {
        int r, c;

        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static int N, M;
    static int[][] graph;
    static List<Node> sharks;
    static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        sharks = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int t = Integer.parseInt(st.nextToken());
                graph[i][j] = t;
                if (t == 1) sharks.add(new Node(i, j));
            }
        }

        int max = Integer.MIN_VALUE;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (graph[r][c] == 0) {
                    max = Math.max(max, bfs(new Node(r, c)));
                }
            }
        }

        System.out.println(max);

    }

    static int bfs(Node start) {
        Queue<Node> que = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        que.add(start);
        visited[start.r][start.c] = true;
        int cnt = 0;
        while (!que.isEmpty()) {
            int qsize = que.size();
            cnt += 1;
            for (int i = 0; i < qsize; i++) {
                Node pop = que.poll();
                for (int j = 0; j < 8; j++) {
                    int nr = pop.r + dr[j];
                    int nc = pop.c + dc[j];
                    if (inGraph(nr, nc) && !visited[nr][nc] && graph[nr][nc] == 0){
                        visited[nr][nc] = true;
                        que.add(new Node(nr, nc));
                    }
                    if (inGraph(nr, nc) && !visited[nr][nc] && graph[nr][nc] == 1){
                        return cnt;
                    }
                }
            }
        }
        return cnt;
    }

    static boolean inGraph(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }

}
