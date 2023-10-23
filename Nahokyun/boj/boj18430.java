package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj18430 {

    private static int[][] map;
    private static boolean[][] visited;
    private static int[][] dx = {{0, -1}, {0, 1}, {0, 1}, {0, -1}};
    private static int[][] dy = {{-1, 0}, {-1, 0}, {1, 0}, {1, 0}};
    private static int n;
    private static int m;
    private static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 입력 종료

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                track(i, j, 0);
            }
        }

        System.out.println(max != Integer.MIN_VALUE ? max : 0);

    }

    private static void track(int y, int x, int sum) {
        if (visited[y][x]) {
            int tmp = m * y + x + 1;
            int nxtY = tmp / m;
            int nxtX = tmp % m;

            if (nxtY >= 0 && nxtY < n && nxtX < m && nxtX >= 0) {
                track(nxtY, nxtX, sum);
            }
            return;
        }

        for (int i = 0; i < 4; i++) {

            int cmpX_1 = x + dx[i][0];
            int cmpY_1 = y + dy[i][0];

            int cmpX_2 = x + dx[i][1];
            int cmpY_2 = y + dy[i][1];

            if (cmpX_1 >= 0 && cmpX_1 < m && cmpY_1 >= 0 && cmpY_1 < n && !visited[cmpY_1][cmpX_1] && cmpX_2 >= 0
                    && cmpX_2 < m && cmpY_2 >= 0 && cmpY_2 < n && !visited[cmpY_2][cmpX_2]) {
                visited[cmpY_1][cmpX_1] = true;
                visited[cmpY_2][cmpX_2] = true;
                visited[y][x] = true;
                int tmp = m * y + x + 1;
                int nxtY = tmp / m;
                int nxtX = tmp % m;
                max = Math.max(max, sum + map[y][x] * 2 + map[cmpY_1][cmpX_1] + map[cmpY_2][cmpX_2]);

                if (nxtY >= 0 && nxtY < n && nxtX < m && nxtX >= 0) {
                    track(nxtY, nxtX, sum + map[y][x] * 2 + map[cmpY_1][cmpX_1] + map[cmpY_2][cmpX_2]);
                }

                visited[cmpY_1][cmpX_1] = false;
                visited[cmpY_2][cmpX_2] = false;
                visited[y][x] = false;
                continue;
            }
        }
        int tmp = m * y + x + 1;
        int nxtY = tmp / m;
        int nxtX = tmp % m;

        if (nxtY >= 0 && nxtY < n && nxtX < m && nxtX >= 0) {
            track(nxtY, nxtX, sum);
        }
    }

}
