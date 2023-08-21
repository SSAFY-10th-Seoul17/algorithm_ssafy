import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj14716 {

    static int M, N;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dx = {0, -1, 0, 1, -1, 1, -1, 1};
    static int[] dy = {-1, 0, 1, 0, -1, -1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        graph = new int[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j] == 1 && !visited[i][j]) {
                    cnt += 1;
                    dfs(i, j);
                }
            }
        }

        System.out.println(cnt);

    }

    static void dfs(int r, int c) {
        if (!inGraph(r, c)) {
            return;
        }
        for (int i = 0; i < 8; i++) {
            int nr = r + dx[i];
            int nc = c + dy[i];
            if (inGraph(nr, nc)) {
                if (!visited[nr][nc] && graph[nr][nc] == 1) {
                    visited[nr][nc] = true;
                    dfs(nr, nc);
                }
            }
        }
    }

    static boolean inGraph(int r, int c) {
        return 0 <= r && r < M && 0 <= c && c < N;
    }
}
