package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.awt.Point;
/*
양
 */
public class boj3184 {

    private static boolean[][] flag;
    private static char[][] map;
    private static int R;
    private static int C;
    private static int[] dx = {0, -1, 1, 0};
    private static int[] dy = {-1, 0, 0, 1};
    private static int wolfCount = 0;
    private static int sheepCount = 0;
    private static int sheepTotalCount = 0;
    private static int wolfTotalCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        flag = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = input.charAt(j);
                if (map[i][j] == 'v') {
                    wolfTotalCount++;
                }

                if (map[i][j] == 'o') {
                    sheepTotalCount++;
                }
            }
        }//input 종료

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!flag[i][j] && map[i][j] != '#') {
                    sheepCount = 0;
                    wolfCount = 0;
                    bfs(i, j);

                    if (sheepCount > wolfCount)
                        wolfTotalCount -= wolfCount;
                    else
                        sheepTotalCount -= sheepCount;
                }
            }
        }

        System.out.println(sheepTotalCount + " " + wolfTotalCount);

    }//end of main

    private static void bfs(int y, int x) {
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(x, y));

        while (!q.isEmpty()) {
            Point cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;
            flag[curY][curX] = true;

            if (map[curY][curX] == 'v') {
                wolfCount++;
            }
            if (map[curY][curX] == 'o') {
                sheepCount++;
            }

            for (int i = 0; i < 4; i++) {
                int cmpX = curX + dx[i];
                int cmpY = curY + dy[i];

                if (cmpX >= 0 && cmpX < C && cmpY >= 0 && cmpY < R//좌표 유효성
                        && map[cmpY][cmpX] != '#'//울타리 여부
                        && !flag[cmpY][cmpX]) {//재방문 여부
                    q.add(new Point(cmpX, cmpY));
                    flag[cmpY][cmpX] = true;
                }
            }
        }
    }
}

