import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class Boj17086 {

    public static void main(String[] args) throws IOException {
        boolean[][] map = getInput();
        List<Point> list = new ArrayList<>();

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j]) {
                    list.add(new Point(i, j));
                }
            }
        }

        System.out.println(getMaxDistance(map, list));
    }

    private static int getMaxDistance(boolean[][] map, List<Point> list) {
        int distance = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (!map[i][j]) {
                    distance = Math.max(distance, getDistance(list, i, j));
                }
            }
        }

        return distance;
    }

    private static int getDistance(List<Point> points, int x, int y) {
        int distance = Integer.MAX_VALUE;

        for (Point point : points) {
            int distX = Math.abs(point.x - x);
            int distY = Math.abs(point.y - y);
            distance = Math.min(distance, Math.min(distX, distY) + Math.abs(distX - distY));
        }
        return distance;
    }

    public static boolean[][] getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[][] map = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = st.nextToken().equals("1");
            }

        }

        return map;
    }
}

