import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj3184 {
    static final char SHEEP = 'o', WOLF = 'v', WALL = '#';
    static int totalWolf, totalSheep;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] map = new char[n + 2][m + 2];

        for (int i = 1; i <= n; i++) {
            String str = br.readLine();
            for (int j = 1; j <= m; j++) {
                map[i][j] = str.charAt(j - 1);
            }
        }

        for (int i = 0; i < n; i++) {
            map[i][0] = WALL;
            map[i][m] = WALL;
        }
        for (int i = 0; i < m; i++) {
            map[0][i] = WALL;
            map[n][i] = WALL;
        }

        for (int i = 2; i < n; i++) {
            for (int j = 2; j < m; j++) {
                if (map[i][j] != WALL) {
                    bfs(map, i, j);
                }
            }
        }

        System.out.println(totalSheep + " " + totalWolf);
    }

    static final int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static Queue<Point> queue = new ArrayDeque<>();
    private static void bfs(char[][] map, int i, int j) {
        int wolf = 0;
        int sheep = 0;
        queue.add(new Point(i, j));
        map[i][j] = WALL;
        if (map[i][j] == SHEEP) {
            sheep++;
        } else if (map[i][j] == WOLF) {
            wolf++;
        }
        
        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            for (int[] dir : dirs) {
                int x = dir[0] + cur.x;
                int y = dir[1] + cur.y;

                if (map[x][y] == WALL) {
                    continue;
                }

                if (map[x][y] == SHEEP) {
                    sheep++;
                } else if (map[x][y] == WOLF) {
                    wolf++;
                }
                map[x][y] = WALL;
                queue.add(new Point(x, y));
            }
        }

        if (sheep > wolf) {
            totalSheep += sheep;
        } else {
            totalWolf += wolf;
        }
    }

}

