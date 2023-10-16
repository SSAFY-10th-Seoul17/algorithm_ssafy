package study10월3주차월;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj16197_두동전 {
    static int r, c;
    static char[][] cells;
    static int[][] coins = new int[2][2];

    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {1, 0, -1, 0};

    static Set<CoinLoc> visited = new HashSet<>();

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        cells = new char[r][];
        int coinIdx = 0;
        for (int i = 0; i < r; i++) {
            cells[i] = br.readLine().toCharArray();
            for (int j = 0; j < c; j++) {
                if (cells[i][j] == 'o') {
                    cells[i][j] = '.';
                    coins[coinIdx][0] = i;
                    coins[coinIdx++][1] = j;
                }
            }
        }


        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<CoinLoc> status = new LinkedList<>();
        Queue<Integer> distances = new LinkedList<>();
        status.add(new CoinLoc(coins[0][0], coins[0][1], coins[1][0], coins[1][1]));
        distances.add(0);


        while (!status.isEmpty()) {
            CoinLoc stt = status.poll();
            int y1 = stt.y1;
            int x1 = stt.x1;
            int y2 = stt.y2;
            int x2 = stt.x2;
            int dist = distances.poll() + 1;
            if (dist > 10) return -1;

            for (int d = 0; d < 4; d++) {
                int yy1 = y1 + dy[d];
                int xx1 = x1 + dx[d];
                int yy2 = y2 + dy[d];
                int xx2 = x2 + dx[d];

                boolean is1out = (yy1 < 0 || r <= yy1) || (xx1 < 0 || c <= xx1);
                boolean is2out = (yy2 < 0 || r <= yy2) || (xx2 < 0 || c <= xx2);
                if (is1out && is2out) {}
                else if (is1out) {
                    return dist;
                }
                else if (is2out) {
                    return dist;
                }
                else {
                    if (cells[yy1][xx1] == '#') {
                        yy1 = y1;
                        xx1 = x1;
                        if (yy1 == yy2 && xx1 == xx2) {
                            yy2 = y2;
                            xx2 = x2;
                        }
                    }
                    if (cells[yy2][xx2] == '#') {
                        yy2 = y2;
                        xx2 = x2;
                        if (yy1 == yy2 && xx1 == xx2) {
                            yy1 = y1;
                            xx1 = x1;
                        }
                    }

                    if (yy1 == y1 && xx1 == x1 && yy2 == y2 && xx2 == x2) {
                    }
                    else {
                        CoinLoc next = new CoinLoc(yy1, xx1, yy2, xx2);
                        if (!visited.contains(next)) {
                            visited.add(next);
                            status.add(next);
                            distances.add(dist);
                        }
                    }
                }
            }
        }
        return -1;
    }

    static class CoinLoc {
        int y1, x1, y2, x2;
        public CoinLoc(int y1, int x1, int y2, int x2) {
            this.y1 = y1;
            this.x1 = x1;
            this.y2 = y2;
            this.x2 = x2;
        }

        // visited 체크용
        @Override
        public boolean equals(Object obj) {
            CoinLoc o = (CoinLoc) obj;
            return (this.y1==o.y1 && this.x1==o.x1 && this.y2==o.y2 && this.x2==o.x2) ||
                    (this.y1==o.y2 && this.x1==o.x2 && this.y2==o.y1 && this.x2==o.x1);
        }

        @Override
        public int hashCode() {
            return Objects.hash(y1, x1, y2, x2);
        }

        @Override
        public String toString() {
            return "{" +
                    y1 +
                    ", " + x1 +
                    ", " + y2 +
                    ", " + x2 +
                    '}';
        }
    }
}