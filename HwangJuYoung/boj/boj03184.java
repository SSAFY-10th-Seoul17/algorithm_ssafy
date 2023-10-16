package BOJ;

import java.io.*;
import java.util.*;

public class boj03184 {
    private static int R, C, vCnt = 0, oCnt = 0;
    private static char[][] ground;
    private static boolean[][] visited;
    private static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        ground = new char[R][C];
        // v 늑대, o 양, # 울타리
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                ground[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (ground[i][j] != '#' && !visited[i][j]) {
                    dfs(i, j);
                }
            }
        }

        sb.append(oCnt).append(" ").append(vCnt).append("\n");
        System.out.println(sb.toString());
    } // end of main

    private static void dfs(int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();

        visited[x][y] = true;
        queue.add(new int[]{x, y});

        int vCur = 0;
        int oCur = 0;
        while (!queue.isEmpty()) {
            int[] vArr = queue.poll();
            int vx = vArr[0];
            int vy = vArr[1];

            if (ground[vx][vy] == 'o') oCur++;
            else if (ground[vx][vy] == 'v') vCur++;

            for (int i = 0; i < dir.length; i++) {
                int nx = vx + dir[i][0];
                int ny = vy + dir[i][1];

                if (!(nx >= 0 && nx < R && ny >= 0 && ny < C && !visited[nx][ny] && ground[nx][ny] != '#')) continue;

                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny});
            }
        }
        if (oCur > vCur) oCnt += oCur;
        else vCnt += vCur;
    }
} // end of class
