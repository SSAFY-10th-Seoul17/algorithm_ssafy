package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj6593 {

    static int[] dx = new int[] { -1, 0, 0, 1, 0, 0 };
    static int[] dy = new int[] { 0, 1, -1, 0, 0, 0 };
    static int[] dz = new int[] { 0, 0, 0, 0, 1, -1 };
    static char[][][] building;
    static int[][][] time;
    static boolean[][][] flag;
    static Dist start;
    static Dist end;
    static int L;
    static int R;
    static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String test;
        while ((test = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(test);
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            if (L == R && R == C && L == C && L == 0) {
                return;
            }

            building = new char[L][R][C];
            flag = new boolean[L][R][C];
            time = new int[L][R][C];

            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String s = br.readLine();
                    for (int k = 0; k < C; k++) {
                        building[i][j][k] = s.charAt(k);
                        if (s.charAt(k) == 'S')
                            start = new Dist(i, j, k);
                        if (s.charAt(k) == 'E')
                            end = new Dist(i, j, k);
                    }
                }
                br.readLine();
            }

            bfs(start);
            if (flag[end.z][end.y][end.x])
                System.out.println("Escaped in "+ time[end.z][end.y][end.x]+" minute(s).");
            else
                System.out.println("Trapped!");
        } // while 종료

    }

    private static void bfs(Dist start) {
        flag[start.z][start.y][start.x] = true;

        Queue<Dist> q = new LinkedList<>();
        q.add(start);
        time[start.z][start.y][start.x] = 0;

        while (!q.isEmpty()) {
            Dist cur = q.poll();
            int curtime=time[cur.z][cur.y][cur.x];
            for (int i = 0; i < 6; i++) {
                int cmpY = cur.y + dy[i];
                int cmpX = cur.x + dx[i];
                int cmpZ = cur.z + dz[i];

                if (cmpY >= 0 && cmpY < R && cmpZ >= 0 && cmpZ < L && cmpX < C && cmpX >= 0
                        && building[cmpZ][cmpY][cmpX] != '#') {
                    if (!flag[cmpZ][cmpY][cmpX] || time[cmpZ][cmpY][cmpX] > curtime + 1) {
                        q.add(new Dist(cmpZ, cmpY, cmpX));
                        time[cmpZ][cmpY][cmpX]=curtime+1;
                        flag[cmpZ][cmpY][cmpX] = true;
                    }

                }
            }
        }

    }
}

class Dist {
    int x;
    int y;
    int z;

    public Dist(int z, int y, int x) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

}
