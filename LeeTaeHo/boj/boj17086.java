import java.io.*;
import java.util.*;

public class boj17086 {
    static boolean[][] map;
    static int n, m;
    static final int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = st.nextToken().equals("1") ? true : false;
            }
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!map[i][j]) {
                    max = Math.max(max, bfs(i, j));
                }
            }
        }
        System.out.println(max);
    }

    public static int bfs(int x, int y) {
        int count = 0;
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> q = new LinkedList<int[]>();
        q.offer(new int[]{x, y, count});
        while (!q.isEmpty()) {
            int[] current = q.poll();
            int cnX = current[0];
            int cnY = current[1];
            int cnCount = current[2];
            for (int dr[] : dir) {
                int nextX = cnX + dr[0];
                int nextY = cnY + dr[1];
                if (0 <= nextX && nextX < n && 0 <= nextY && nextY < m && !visited[nextX][nextY]) {
                    if (map[nextX][nextY]) {
                        return cnCount + 1;
                    }
                    visited[nextX][nextY] = true;
                    q.offer(new int[]{nextX, nextY, cnCount + 1});
                }
            }
        }
        return -1;
    }
}
