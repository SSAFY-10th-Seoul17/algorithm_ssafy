package boj;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.*;

public class boj17086 {

    private static int n;
    private static int m;
    private static ArrayList<safeAndPoint> sharkPosition;
    private static int[][] arr;
    private static Queue<safeAndPoint> q = new LinkedList<>();
    private static int[] dx = new int[]{-1, 0, 0, 1, 1, 1, -1, -1};
    private static int[] dy = new int[]{0, 1, -1, 0, 1, -1, 1, -1};


    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        sharkPosition = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    sharkPosition.add(new safeAndPoint(j, i, 0));
                }
            }
        }//입력 종료
        for (safeAndPoint p : sharkPosition) {
            q.add(p);
        }
        bfs();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (max < arr[i][j])
                    max = arr[i][j];
            }
        }
        System.out.println(max);


    }

    private static void bfs() {
        while (!q.isEmpty()) {
            safeAndPoint cur = q.poll();
            int curX = cur.x;
            int curY = cur.y;
            int curSafe = cur.safe;
            for (int i = 0; i < 8; i++) {
                int cmpX = curX + dx[i];
                int cmpY = curY + dy[i];
                int cmpSafe = curSafe + 1;

                if (cmpX >= 0 && cmpX < m && cmpY >= 0 && cmpY < n
                        && arr[cmpY][cmpX] == 0) {
                    arr[cmpY][cmpX] = cmpSafe;
                    q.add(new safeAndPoint(cmpX, cmpY, cmpSafe));
                }
            }

        }
    }
}

class safeAndPoint {
    int x;
    int y;
    int safe;

    public safeAndPoint(int x, int y, int safe) {
        this.x = x;
        this.y = y;
        this.safe = safe;
    }
}
