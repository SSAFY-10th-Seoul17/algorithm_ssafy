import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj4179 {
    static final int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static final char FIRE = 'F', WALL = '#', EMPTY = '.', PLAYER = 'J';


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        char[][] map = new char[r][c];
        Point start = null;
        Queue<Point> fires = new LinkedList<>();

        for (int i = 0; i < r; i++) {
            String str = br.readLine();
            for (int j = 0; j < c; j++) {
                map[i][j] = str.charAt(j);

                if (map[i][j] == PLAYER) {
                    start = new Point(i, j);
                } else if (map[i][j] == FIRE) {
                    fires.add(new Point(i, j));
                }
            }
        }

        int time = getMinEscapeTime(map, fires, start);
        System.out.println(time == -1 ? "IMPOSSIBLE" : time);
    }

    private static int getMinEscapeTime(char[][] map, Queue<Point> fires, Point start) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        int time = 0;

        while (!queue.isEmpty()) {
            time++;
            fires = moveFire(map, fires);
            Queue<Point> next = new LinkedList<>();

            while (!queue.isEmpty()) {
                Point current = queue.poll();

                for (int[] dir : dirs) {
                    int x = dir[0] + current.x;
                    int y = dir[1] + current.y;

                    if (isOutside(map, x, y)) { // escape!
                        return time;
                    } else if (map[x][y] != EMPTY) {
                        continue;
                    }

                    next.add(new Point(x, y));
                    map[x][y] = WALL;
                }
            }

            queue = next;
        }

        return -1;
    }

    private static Queue<Point> moveFire(char[][] map, Queue<Point> fires) {
        Queue<Point> nextFires = new LinkedList<>();

        while (!fires.isEmpty()) {
            Point fire = fires.poll();

            for (int[] dir : dirs) {
                int x = dir[0] + fire.x;
                int y = dir[1] + fire.y;

                if (isOutside(map, x, y) || map[x][y] != EMPTY) {
                    continue;
                }

                nextFires.add(new Point(x, y));
                map[x][y] = FIRE;
            }
        }

        return nextFires;
    }

    private static boolean isOutside(char[][] map, int x, int y) {
        return x < 0 || y < 0 || x >= map.length || y >= map[x].length;
    }

}

