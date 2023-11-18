package study10월2주차목;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj16234_인구_이동 {
    static int n, l, r;
    static int[][] cells;
    static boolean[][] visited;
    static Queue<Integer> values;
    static Queue<Queue<int[]>> locs;

    static final int[] dy = {0, 1, 0, -1};
    static final int[] dx = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        cells = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                cells[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        while (true) {
            visited = new boolean[n][n];
            values = new LinkedList<>();
            locs = new LinkedList<>();
            boolean moved = false;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j])
                        moved |= check(i, j);
                }
            }

            if (!moved)
                break;
            else
                day++;

            while (!values.isEmpty()) {
                int value = values.poll();
                Queue<int[]> union = locs.poll();

                while(!union.isEmpty()) {
                    int y = union.peek()[0];
                    int x = union.peek()[1];
                    union.poll();
                    cells[y][x] = value;
                }
            }
        }
        System.out.println(day);
    }

    private static boolean check(int i, int j) {
        Queue<int[]> loc = new LinkedList<>();
        Queue<int[]> tmp = new LinkedList<>();
        loc.add(new int[]{i, j});
        visited[i][j] = true;
        int sum = cells[i][j];

        while (!loc.isEmpty()) {
            int y = loc.peek()[0];
            int x = loc.peek()[1];
            tmp.add(loc.poll());

            for (int d = 0; d < 4; d++) {
                int yy = y + dy[d];
                int xx = x + dx[d];

                if (yy < 0 || n <= yy || xx < 0 || n <= xx || visited[yy][xx])
                    continue;
                int diff = Math.abs(cells[y][x] - cells[yy][xx]);
                if (l <= diff && diff <= r) {
                    sum += cells[yy][xx];
                    loc.add(new int[]{yy, xx});
                    visited[yy][xx] = true;
                }
            }
        }
        if (tmp.size() == 1) {
            return false;
        } else {
            locs.add(tmp);
            values.add(sum / tmp.size());
            return true;
        }
    }
} // end of class