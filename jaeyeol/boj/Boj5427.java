import java.awt.*;
import java.io.*;
import java.util.*;

// 불
public class Boj5427 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            char[][] map = new char[n][m];

            Queue<Point> fires = new LinkedList<>();
            Point me = null;
            for (int i = 0; i < n; i++) {
                String str = br.readLine();
                for (int j = 0; j < m; j++) {
                    map[i][j] = str.charAt(j);

                    if (map[i][j] == '*') {
                        fires.add(new Point(i, j));
                    } else if (map[i][j] == '@') {
                        me = new Point(i, j);
                    }
                }
            }

            result.append(simulate(map, fires, me)).append("\n");
        }

        System.out.println(result);
    }

    static int[][] dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    private static String simulate(char[][] map, Queue<Point> fires, Point person) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(person);
        int time = 0;

        while (!queue.isEmpty()) {
            Queue<Point> nextFires = new LinkedList<>();
            Queue<Point> next = new LinkedList<>();
            time++;

            moveFires(map, fires, nextFires);

            if (movePerson(map, queue, time, next)) {
                return String.valueOf(time);
            }

            queue = next;
            fires = nextFires;
        }

        return "IMPOSSIBLE";
    }

    private static boolean movePerson(char[][] map, Queue<Point> queue, int time, Queue<Point> next) {
        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            for (int[] dir : dirs) {
                int x = dir[0] + cur.x;
                int y = dir[1] + cur.y;

                if (x < 0 || y < 0 || x >= map.length || y >= map[x].length) { // 탈출
                    return true;
                } else if (map[x][y] != '.') {
                    continue;
                }

                map[x][y] = '#';
                next.add(new Point(x, y));
            }
        }

        return false;
    }

    private static void moveFires(char[][] map, Queue<Point> fires, Queue<Point> nextFires) {
        while (!fires.isEmpty()) {
            Point fire = fires.poll();
            for (int[] dir : dirs) {
                int x = dir[0] + fire.x;
                int y = dir[1] + fire.y;

                if (x < 0 || y < 0 || x >= map.length || y >= map[x].length || map[x][y] != '.') {
                    continue;
                }

                map[x][y] = '*';
                nextFires.add(new Point(x, y));
            }
        }
    }

}

