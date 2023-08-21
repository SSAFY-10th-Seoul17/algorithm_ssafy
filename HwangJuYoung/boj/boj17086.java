import java.io.*;
import java.util.*;

public class Main {
    static int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
    private static boolean[][] visited;
    private static int N;
    private static int M;
    private static int[][] map;
    private static int safeMax = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bReader.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bReader.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    visited = new boolean[N][M];
                    BFS(i, j);
                }
            }
        }
        System.out.println(safeMax);
    } // end of main

    private static void BFS(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();

        queue.add(new int[] {x, y, 0});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int v[] = queue.poll();
            int vx = v[0];
            int vy = v[1];

            for (int i = 0; i < dir.length; i++) {
                int nx = vx + dir[i][0];
                int ny = vy + dir[i][1];

                if (!(nx >= 0 && nx < N && ny >= 0 && ny < M && visited[nx][ny] == false)) {
                    continue;
                }
                if (map[nx][ny] == 1) {
                    safeMax  = Math.max(safeMax, v[2] + 1);
                    return;
                }
                queue.add(new int[] {nx, ny, v[2]+1});
                visited[nx][ny] = true;
            }
        }
    }
} // end of class