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

        banner = new int[N][M];
        visited = new boolean[N * M];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bReader.readLine(), " ");
            for (int j = 0; j < M; j++) {
                banner[i][j] = Integer.parseInt(stringTokenizer.nextToken());
            }
        }

        cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
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


            for (int j = 0; j < 8; j++) {
                int nx = vx + dir[j][0];
                int ny = vy + dir[j][1];
                i = nx * M + ny;

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && banner[nx][ny] != 0 && !visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }
} // end of class