package study10월5주차목;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj24891_옥수수밭 {
    static int n, m, k;
    static int[][] field;
    static boolean[][] checked;
    static PriorityQueue<Corn> pq = new PriorityQueue<>();

    static final int[] dy = {0, 1, 0, -1};
    static final int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        field = new int[n][m];
        checked = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
                if (i == 0 || i == n-1 || j == 0 || j == m-1) {
                    pq.add(new Corn(field[i][j], i, j));
                    checked[i][j] = true;
                }
            }
        }
        k = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        while (!pq.isEmpty()) {
            Corn now = pq.poll();
            sb.append(now.y+1).append(" ").append(now.x+1).append("\n");
            if (--k <= 0)
                break;
            for (int d = 0; d < 4; d++) {
                int y = now.y + dy[d];
                int x = now.x + dx[d];
                if (0 <= y && y < n && 0 <= x && x < m && !checked[y][x]) {
                    pq.add(new Corn(field[y][x], y, x));
                    checked[y][x] = true;
                }
            }
        }
        System.out.println(sb);
    }

    static class Corn implements Comparable<Corn>{
        int val, y, x;

        public Corn(int val, int y, int x) {
            this.val = val;
            this.y = y;
            this.x = x;
        }

        @Override
        public int compareTo(Corn o) {
            return o.val - this.val;
        }
    }
}