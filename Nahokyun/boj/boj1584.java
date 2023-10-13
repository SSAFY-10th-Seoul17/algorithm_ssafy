package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;
import java.awt.Point;

public class boj1584 {
    static int[] dx = { 0, 1, -1, 0 };
    static int[] dy = { 1, 0, 0, -1 };
    private static int[][] map;

    static int[][] dp;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        map = new int[501][501];
        dp = new int[501][501];

        for (int i = 1; i <= 500; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int smallerX = Math.min(x1, x2);
            int biggerX = Math.max(x1, x2);
            int smallerY = Math.min(y1, y2);
            int biggerY = Math.max(y1, y2);

            for (int j = smallerY; j <= biggerY; j++) {
                for (int k = smallerX; k <= biggerX; k++) {
                    map[j][k] = -1;
                }
            }

        }

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int smallerX = Math.min(x1, x2);
            int biggerX = Math.max(x1, x2);
            int smallerY = Math.min(y1, y2);
            int biggerY = Math.max(y1, y2);

            for (int j = smallerY; j <= biggerY; j++) {
                for (int k = smallerX; k <= biggerX; k++) {
                    map[j][k] = -2;
                }
            }

        }
        // 맵 초기화,
        boolean[][] visited = new boolean[501][501];

        visited[0][0] = true;
        dp[0][0] = 0;
        move(0, 0, 0, visited);

        System.out.println(dp[500][500] != Integer.MAX_VALUE ? dp[500][500] : -1);

    }

    private static void move(int curx, int cury, int life, boolean[][] visited) {

        PriorityQueue<Position> q = new PriorityQueue<>();

        q.add(new Position(new Point(curx, cury), 0));

        while (!q.isEmpty()) {
            Position cur = q.poll();

            int x = cur.sejun.x;
            int y = cur.sejun.y;
            int curLife = cur.life;

            for (int i = 0; i < 4; i++) {
                int cmpX = x + dx[i];
                int cmpY = y + dy[i];
                if (cmpX >= 0 && cmpX <= 500 && cmpY >= 0 && cmpY <= 500 && map[cmpY][cmpX] != -2
                        && (!visited[cmpY][cmpX] || dp[cmpY][cmpX] > curLife)) {

                    visited[cmpY][cmpX] = true;

                    if (map[cmpY][cmpX] == -1) {
                        if(curLife+1==dp[cmpY][cmpX]) {
                            continue;
                        }
                        dp[cmpY][cmpX] = curLife + 1;
                    } else {
                        dp[cmpY][cmpX] = curLife;
                    }

                    q.add(new Position(new Point(cmpX, cmpY), dp[cmpY][cmpX]));
                }
            }

        } // end of while

    }

    static class Position implements Comparable<Position> {
        Point sejun;
        int life;

        public Position(Point cur, int life) {
            this.sejun = cur;
            this.life = life;
        }

        @Override
        public int compareTo(Position o) {

            return this.life - o.life;
        }
    }
}
