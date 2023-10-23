package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
import java.awt.Point;

public class boj16234 {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    private static boolean[][] visited;
    private static int N;
    private static int L;
    private static int R;
    private static int[][] map;
    private static int[][] prevMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        prevMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 입력 종료

        int day = 0;

        while (true) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++)
                    prevMap[i][j] = map[i][j];
            }
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        bfs(j, i);
                    }
                }
            }
            if (!moveExist()) {//이동이 없으면 이전 이동일수 출력
                System.out.println(day);
                return;
            }
            day++;
        }
    }

    private static boolean moveExist() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (prevMap[i][j] != map[i][j]) {//이동이 일어난적 있을 때 false리턴
                    return true;
                }
            }
        }
        return false;
    }

    private static void bfs(int startX, int startY) {
        ArrayDeque<Point> q = new ArrayDeque<>();
        ArrayDeque<Point> union = new ArrayDeque<>();
        q.add(new Point(startX, startY));
        union.add(new Point(startX, startY));
        int sum = map[startY][startX];
        int count = 1;
        visited[startY][startX] = true;

        while (!q.isEmpty()) {
            Point cur = q.poll();

            int curX = cur.x;
            int curY = cur.y;
            for (int i = 0; i < 4; i++) {
                int cmpX = curX + dx[i];
                int cmpY = curY + dy[i];
                if (cmpX >= 0 && cmpX < N && cmpY >= 0 && cmpY < N && !visited[cmpY][cmpX]) {
                    if (isUnion(map[curY][curX], map[cmpY][cmpX])) {
                        q.add(new Point(cmpX, cmpY));
                        union.add(new Point(cmpX, cmpY));
                        visited[cmpY][cmpX] = true;
                        sum += map[cmpY][cmpX];
                        count++;
                    }
                }
            }
        }
        // 연합 파악완료

        int avg = sum / count;

        while (!union.isEmpty()) {
            Point cur = union.poll();
            map[cur.y][cur.x] = avg;
        }
    }

    private static boolean isUnion(int cur, int cmp) {
        int diff = Math.abs(cur - cmp);
        return diff >= L && diff <= R;
    }

}
