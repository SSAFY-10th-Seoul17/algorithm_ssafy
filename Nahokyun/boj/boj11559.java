package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.awt.Point;

public class boj11559 {
    static int[] dx = { 1, 0, 0, -1 };
    static int[] dy = { 0, 1, -1, 0 };
    private static boolean[][] visited;
    private static char[][] arr;
    static ArrayDeque<Point> boomArray = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        arr = new char[12][6];

        for (int i = 11; i >= 0; i--) {
            String s = br.readLine();
            for (int j = 0; j < 6; j++) {
                arr[i][j] = s.charAt(j);
            }
        }
        // 입력 종료

        int count = 0;
        boolean anyBoom = true;// 더이상 터질게 없을경우 탈출
        while (anyBoom) {
            visited = new boolean[12][6];

            anyBoom = find();// 뭐하나라도 터지면 true 리턴
            if (anyBoom) {
                count++;
                Boom(boomArray);
                down();
            }
        }
        System.out.println(count);
    }// end of main

    private static void down() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (arr[i][j] == '.') {
                    int pullDistance = exist(i, j);
                    if (pullDistance == -1) {
                        continue;
                    } else {
                        arr[i][j] = arr[i + pullDistance][j];
                        arr[i + pullDistance][j] = '.';
                    }
                }
            }
        }
    }

    private static int exist(int i, int j) {
        int count = 1;
        while (i + count < 12) {
            if (arr[i + count][j] != '.') {
                return count;
            } else {
                count++;
            }
        }
        return -1;
    }

    private static boolean find() {
        boolean anyBoomExist = false;
        for (int i = 0; i < 12; i++) {
            boolean flag = false;// 한줄이라도 ......이면 더 탐색 x(공이 있는 곳보다 높게 탐색하지 않기위해)
            for (int j = 0; j < 6; j++) {
                if (arr[i][j] != '.') {
                    flag = true;
                }
                if (!visited[i][j] && arr[i][j] != '.') {
                    if (isContained4(i, j)) {
                        anyBoomExist = true;
                        continue;
                    }
                }
            }
            if (!flag && !anyBoomExist) {
                return false;
            }
        }

        return anyBoomExist;
    }

    private static boolean isContained4(int startY, int startX) {
        ArrayDeque<Point> q = new ArrayDeque<>();
        ArrayDeque<Point> boomTmp = new ArrayDeque<>();
        visited[startY][startX] = true;
        Point start = new Point(startX, startY);
        q.add(start);
        boomTmp.add(start);

        while (!q.isEmpty()) {
            Point cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;

            for (int i = 0; i < 4; i++) {
                int cmpX = curX + dx[i];
                int cmpY = curY + dy[i];
                if (cmpX >= 0 && cmpX < 6 && cmpY >= 0 && cmpY < 12 && !visited[cmpY][cmpX]
                        && arr[cmpY][cmpX] == arr[startY][startX]) {
                    visited[cmpY][cmpX] = true;
                    Point tmp = new Point(cmpX, cmpY);
                    q.add(tmp);
                    boomTmp.add(tmp);
                }
            }
        }

        if (boomTmp.size() >= 4) {
            boomArray.addAll(boomTmp);
            return true;
        } else
            return false;

    }

    private static void Boom(ArrayDeque<Point> boomTmp) {
        while (!boomTmp.isEmpty()) {
            Point cur = boomTmp.poll();
            arr[cur.y][cur.x] = '.';
        }
    }

}
