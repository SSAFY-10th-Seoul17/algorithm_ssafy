import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//상범 빌딩
public class Boj06593 {
    static class Point {
        int x, y, z;

        public Point(int z, int x, int y) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String read;
        StringBuilder result = new StringBuilder();

        while (!(read = br.readLine()).equals("0 0 0")) {
            StringTokenizer st = new StringTokenizer(read, " ");
            int l = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            char[][][] map = new char[l][n][m];
            Point person = null, exit = null;

            for (int f = 0; f < l; f++) {

                for (int i = 0; i < n; i++) {
                    String str = br.readLine();
                    for (int j = 0; j < m; j++) {
                        map[f][i][j] = str.charAt(j);

                        if (map[f][i][j] == 'S') {
                            person = new Point(f, i, j);
                        } else if (map[f][i][j] == 'E') {
                            exit = new Point(f, i, j);
                        }
                    }
                }
                br.readLine();
            }

            result.append(simulate(map, l, n, m, person, exit)).append("\n");
        }

        System.out.println(result);
    }

    static final int[][] dirs = {{0, 1, 0}, {0, 0, 1}, {0, -1, 0}, {0, 0, -1}, {-1, 0, 0}, {1, 0, 0}};
    static final char WALL = '#', EXIT = 'E';

    private static String simulate(char[][][] map, int l, int n, int m, Point person, Point exit) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(person);
        int time = 0;

        while (!queue.isEmpty()) {
            time++;
            Queue<Point> next = new LinkedList<>();

            while (!queue.isEmpty()) {
                Point cur = queue.poll();

                for (int[] dir : dirs) {
                    int z = cur.z + dir[0];
                    int x = cur.x + dir[1];
                    int y = cur.y + dir[2];

                    if (x < 0 || y < 0 || x >= n || y >= m || z < 0 || z >= l || map[z][x][y] == WALL) {
                        continue;
                    } else if (map[z][x][y] == EXIT) {
                        return "Escaped in " + time + " minute(s).";
                    }

                    map[z][x][y] = WALL;
                    next.add(new Point(z, x, y));
                }
            }

            queue = next;
        }

        return "Trapped!";
    }


}

