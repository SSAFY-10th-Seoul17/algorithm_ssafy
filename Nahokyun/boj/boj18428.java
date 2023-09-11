package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.awt.Point;

public class boj18428 {
    private static int n;
    private static char[][] map;
    private static ArrayList<Point> teacher;
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };
    static boolean flag = false;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        teacher = new ArrayList<Point>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = st.nextToken().charAt(0);
                if (map[i][j] == 'T') {
                    teacher.add(new Point(j, i));
                }
            }
        }
        wall(0, 0, 0);

        // flag가 true이면 누구 안만난거
        System.out.println(!flag ? "NO" : "YES");
    }

    private static void wall(int cur, int stx, int sty) {
        if (flag)
            return;
        if (cur == 3) {
            check();
            return;
        }
        for (int i = sty; i < n; i++) {
            for (int j = stx; j < n; j++) {
                if (map[i][j] == 'X') {
                    map[i][j] = 'O';
                    wall(cur + 1, j, i);
                    map[i][j] = 'X';
                }
                if (j == n - 1 && i != n - 1) {
                    i++;
                    j = -1;
                }
            }
        }
    }

    private static void check() {
        for (Point cur:teacher) {
            for (int i = 0; i < 4; i++) {
                int cmpX = cur.x + dx[i];
                int cmpY = cur.y + dy[i];
                while (isValid(cmpX, cmpY)) {
                    if (map[cmpY][cmpX] == 'O'||map[cmpY][cmpX]=='T') {
                        break;
                    } else if (map[cmpY][cmpX] == 'S') {
                        flag = false;
                        return;
                    }else if (map[cmpY][cmpX] == 'X') {
                        cmpX += dx[i];
                        cmpY += dy[i];
                    }
                }
            }
        }
        flag = true;
    }

    static boolean isValid(int x, int y) {
        if (x < n && y < n && x >= 0 && y >= 0) {
            return true;
        }
        return false;
    }
}

