package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.awt.Point;

public class boj30024 {

    private static boolean[][] reachable;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        reachable = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        PriorityQueue<Corn> pq = init(map, n, m);
        int k = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 입력 종료

        while (k-- > 0) {
            Corn cur = pq.poll();
            sb.append(cur.pos.y + 1).append(' ').append(cur.pos.x + 1).append('\n');
            for (int i = 0; i < 4; i++) {
                int cmpX = cur.pos.x + dx[i];
                int cmpY = cur.pos.y + dy[i];

                if (cmpX >= 0 && cmpX < m && cmpY >= 0 && cmpY < n && !reachable[cmpY][cmpX]) {
                    pq.add(new Corn(new Point(cmpX, cmpY), map[cmpY][cmpX]));
                    reachable[cmpY][cmpX] = true;
                }
            }
        }

        System.out.println(sb);
    }

    private static PriorityQueue<Corn> init(int[][] map, int n, int m) {
        PriorityQueue<Corn> start = new PriorityQueue<>();

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(i==0||i==n-1||j==0||j==m-1) {
                    start.add(new Corn(new Point(j,i),map[i][j]));
                    reachable[i][j]=true;
                }
            }
        }

        return start;
    }

    static class Corn implements Comparable<Corn> {
        Point pos;
        int value;

        public Corn(Point pos, int value) {
            super();
            this.pos = pos;
            this.value = value;
        }

        @Override
        public int compareTo(Corn o) {
            return o.value - this.value;
        }
    }

}

