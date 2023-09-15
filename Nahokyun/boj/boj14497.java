package boj;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
import java.util.*;

public class boj14497 {
    public static int[] dx = {0, 0, -1, 1};
    public static int[] dy = {1, -1, 0, 0};
    private static int n;
    private static int m;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int startY = Integer.parseInt(st.nextToken()) - 1;
        int startX = Integer.parseInt(st.nextToken()) - 1;
        Point start = new Point(startX, startY);
        Point end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        char[][] map = new char[n][m];
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
            }
        }//입력종료

        System.out.println(jump(start, map, visited));

    }

    private static int jump(Point start, char[][] map, boolean[][] visited) {
        Queue<Point> q = new ArrayDeque<>();

        q.add(start);
        int jumpCount = 0;

        while (!q.isEmpty()) {
            Queue<Point> wall = new ArrayDeque<>();
            jumpCount++;
            while (!q.isEmpty()) {
                Point cur = q.poll();
                visited[cur.y][cur.x] = true;

                for (int i = 0; i < 4; i++) {
                    int cmpX = cur.x + dx[i];
                    int cmpY = cur.y + dy[i];

                    if (cmpX >= 0 && cmpX < m && cmpY >= 0 && cmpY < n && !visited[cmpY][cmpX]) {
                        if (map[cmpY][cmpX] == '#') {
                            return jumpCount;
                        }
                        if (map[cmpY][cmpX] == '1') {//벽을 만났을 경우 파괴되므로 0으로 변경
                            wall.add(new Point(cmpX, cmpY));
                            map[cmpY][cmpX] = '0';
                        } else {//0일때 계속 진행
                            q.add(new Point(cmpX, cmpY));
                        }
                        visited[cmpY][cmpX] = true;
                    }

                }


            }//벽 만나기전에 0들 탐색

            q = wall;
        }

        return jumpCount;

    }
}
