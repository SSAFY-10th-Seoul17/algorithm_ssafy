import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Boj14502 {
    static List<Point> virus;
    static int[][] map;
    static int n, m, virusCount = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        virus = new ArrayList<>();

        int totalArea = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) {
                    totalArea++;
                } else if (map[i][j] == 2) {
                    virus.add(new Point(i, j));
                }
            }
        }

        dfs(0, 0, 0);
        System.out.println(totalArea - virusCount - 3);
    }

    private static void dfs(int depth, int x, int y) {
        if (depth == 3) {
            virusCount = Math.min(virusCount, bfs());
            return;
        }

        for (int i = x; i < map.length; i++) {
            for (int j = (i == x ? y : 0); j < map[i].length; j++) {
                if (map[i][j] <= 0) {
                    map[i][j] = 1;
                    dfs(depth + 1, i, j);
                    map[i][j] = 0;
                }
            }
        }
    }

    static int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int season;

    static Queue<Point> queue = new ArrayDeque<>();
    private static int bfs() {
        season--;
        queue.addAll(virus);

        int count = 0;
        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            for (int[] dir : dirs) {
                int x = dir[0] + cur.x;
                int y = dir[1] + cur.y;

                if (x < 0 || y < 0 || x >= n || y >= m || map[x][y] > 0 || map[x][y] == season) {
                    continue;
                }
                map[x][y] = season;
                queue.add(new Point(x, y));
                count++;
            }

            if (count >= virusCount) {
                queue.clear();
                return count;
            }
        }

        return count;
    }

}

