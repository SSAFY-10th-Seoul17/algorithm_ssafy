import java.io.*;
import java.util.*;

public class Boj30024 {
    static class Corn {
        int number;
        int x, y;

        public Corn(int number, int x, int y) {
            this.number = number;
            this.x = x;
            this.y = y;
        }
    }

    static final int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        PriorityQueue<Corn> queue = new PriorityQueue<>(Collections.reverseOrder(Comparator.comparingInt(c -> c.number)));
        int[][] map = new int[n + 2][m + 2];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(getResult(Integer.parseInt(br.readLine()), map, n, m));
    }

    private static String getResult(int q, int[][] map, int n, int m) {
        PriorityQueue<Corn> queue = getQueue(map, n, m);
        StringBuilder result = new StringBuilder();

        while (q-- > 0) {
            Corn corn = queue.remove();

            for (int[] dir : dirs) {
                int x = corn.x + dir[0];
                int y = corn.y + dir[1];
                if (map[x][y] > 0) {
                    queue.add(new Corn(map[x][y], x, y));
                    map[x][y] = 0;
                }
            }

            result.append(corn.x).append(' ').append(corn.y).append('\n');
        }

        return result.toString();
    }

    private static PriorityQueue<Corn> getQueue(int[][] map, int n, int m) {
        PriorityQueue<Corn> queue = new PriorityQueue<>(Collections.reverseOrder(Comparator.comparingInt(c -> c.number)));
        if (n == 1) {
            for (int i = 1; i <= m; i++) {
                queue.add(new Corn(map[1][i], 1, i));
                map[1][i] = 0;
            }
        } else if (m == 1) {
            for (int i = 1; i <= n; i++) {
                queue.add(new Corn(map[i][1], i, 1));
                map[i][1] = 0;
            }
        } else {
            for (int i = 1; i <= n; i++) {
                queue.add(new Corn(map[i][1], i, 1));
                queue.add(new Corn(map[i][m], i, m));
                map[i][1] = map[i][m] = 0;
            }
            for (int i = 2; i < m; i++) {
                queue.add(new Corn(map[1][i], 1, i));
                queue.add(new Corn(map[n][i], n, i));
                map[1][i] = map[n][i] = 0;
            }
        }
        return queue;
    }

}

