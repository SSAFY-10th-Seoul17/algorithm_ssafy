import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1584 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] map = new int[503][503];

        int n = Integer.parseInt(br.readLine());
        printMap(n, br, map, 1);

        int m = Integer.parseInt(br.readLine());
        printMap(m, br, map, -1);

        for (int i = 0; i < map.length; i++) {
            map[0][i] = map[i][0] = map[i][502] = map[502][i] = -1;
        }

        System.out.println(getMinLife(map));
    }

    static final int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static final int END = 501;

    private static int getMinLife(int[][] map) {
        if (map[END][END] == -1) {
            return -1;
        }

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(1, 1));
        map[1][1] = -1;

        while (!queue.isEmpty()) {
            Queue<Point> next = new LinkedList<>();
            while (!queue.isEmpty()) {
                Point cur = queue.poll();

                for (int[] dir : dirs) {
                    int x = dir[0] + cur.x;
                    int y = dir[1] + cur.y;

                    if (map[x][y] < 0) {
                        continue;
                    }

                    if (map[x][y] == 0) {
                        queue.add(new Point(x, y));
                        map[x][y] = map[cur.x][cur.y];
                    } else {
                        next.add(new Point(x, y));
                        map[x][y] = map[cur.x][cur.y] - 1;
                    }
                }
            }

            if (map[END][END] < 0) {
                return -(map[END][END] + 1);
            }
            queue = next;
        }

        return -1;
    }

    private static void printMap(int n, BufferedReader br, int[][] map, int type) throws IOException {
        while (n-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x1 = Integer.parseInt(st.nextToken()) + 1;
            int y1 = Integer.parseInt(st.nextToken()) + 1;
            int x2 = Integer.parseInt(st.nextToken()) + 1;
            int y2 = Integer.parseInt(st.nextToken()) + 1;

            int x = Math.max(x1, x2);
            int y = Math.max(y1, y2);

            for (int i = Math.min(x1, x2); i <= x; i++) {
                for (int j = Math.min(y1, y2); j <= y; j++) {
                    map[i][j] = type;
                }
            }
        }
    }


}
