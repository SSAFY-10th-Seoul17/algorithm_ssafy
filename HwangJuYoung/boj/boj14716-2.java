import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int M;
    private static boolean[] visited;
    private static int[][] banner;
    private static int[][] dir = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 },
            { 1, 1 } };
    private static int cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sBuilder = new StringBuilder();

        StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine(), " ");

        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());

        banner = new int[N + 2][M + 2];
        visited = new boolean[(N + 2) * (M + 2)];

        for (int i = 1; i <= N; i++) {
            stringTokenizer = new StringTokenizer(bReader.readLine(), " ");
            for (int j = 1; j <= M; j++) {
                banner[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        cnt = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (banner[i][j] == 1 && !visited[i * M + j]) {
                    cnt++;
                    BFS(i, j);
                }
            }
        }

        System.out.println(cnt);

    } // end of main

    private static void BFS(int x, int y) {
        Queue<Integer> queue = new ArrayDeque<>();
        int i = x * M + y;
        queue.add(i);
        visited[i] = true;

        while (!queue.isEmpty()) {
            int v = queue.poll();
            int vx = v / M;
            int vy = v % M;
            if (vy == 0) {
                vx -= 1;
                vy += 1;
            }

            for (int j = 0; j < 8; j++) {
                int nx = vx + dir[j][0];
                int ny = vy + dir[j][1];
                i = nx * M + ny;

                if (banner[nx][ny] != 0 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }
} // end of class