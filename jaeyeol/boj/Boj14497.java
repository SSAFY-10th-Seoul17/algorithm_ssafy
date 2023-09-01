import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        Point culprit = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        Point junan = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        char[][] map = new char[n + 2][m + 2];
        for (int i = 1; i <= n; i++) {
            String str = br.readLine();
            for (int j = 1; j <= m; j++) {
                map[i][j] = str.charAt(j - 1);
            }
        }

        System.out.println(getMinJump(map, junan));
    }

    static final int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    private static int getMinJump(char[][] map, Point junan) {
        Queue<Point> queue = new LinkedList<>();
        queue.add(junan);
        map[junan.x][junan.y] = 0;
        int jump = 0;

        while (!queue.isEmpty()) {
            Queue<Point> next = new LinkedList<>();
            jump++;
            while (!queue.isEmpty()) {
                Point cur = queue.poll();

                for (int[] dir : dirs) {
                    int x = cur.x + dir[0];
                    int y = cur.y + dir[1];

                    if (map[x][y] == 0) {
                        continue;
                    }
                    
                    switch (map[x][y]) {
                        case '0':
                            queue.add(new Point(x, y));
                            break;
                        case '1':
                            next.add(new Point(x, y));
                            break;
                        default:
                            return jump;
                    }
                    map[x][y] = 0;
                }
            }

            queue = next;
        }

        return jump;
    }

}
