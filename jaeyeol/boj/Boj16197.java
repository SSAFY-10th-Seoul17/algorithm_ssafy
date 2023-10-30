import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16197 {
    static class Coins {
        int cx1, cy1;
        int cx2, cy2;

        public Coins() {}

        public Coins(int cx1, int cy1, int cx2, int cy2) {
            this.cx1 = cx1;
            this.cy1 = cy1;
            this.cx2 = cx2;
            this.cy2 = cy2;
        }

        public void setVisit(boolean[][][][] visited) {
            visited[cx1][cy1][cx2][cy2] = true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][m];
        Coins coins = new Coins();
        int count = 0;

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                map[i][j] = str.charAt(j);

                if (map[i][j] == 'o') {
                    if (count++ == 0) {
                        coins.cx1 = i;
                        coins.cy1 = j;
                    } else {
                        coins.cx2 = i;
                        coins.cy2 = j;
                    }
                }
            }
        }

        System.out.println(getButtonCount(map, coins));
    }

    static final int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    static final char WALL = '#';

    private static int getButtonCount(char[][] map, Coins initCoins) {
        Queue<Coins> queue = new LinkedList<>();
        queue.add(initCoins);
        boolean[][][][] visited = new boolean[map.length][map[0].length][map.length][map[0].length];
        initCoins.setVisit(visited);
        int time = 0;

        while (!queue.isEmpty()) {
            time++;
            Queue<Coins> next = new LinkedList<>();

            while (!queue.isEmpty()) {
                Coins coins = queue.poll();

                for (int[] dir : dirs) {
                    int x1 = dir[0] + coins.cx1;
                    int y1 = dir[1] + coins.cy1;
                    int x2 = dir[0] + coins.cx2;
                    int y2 = dir[1] + coins.cy2;

                    int count = 0;
                    if (x1 < 0 || y1 < 0 || x1 >= map.length || y1 >= map[0].length) {
                        count++;
                    } else if (map[x1][y1] == WALL) {
                        x1 = coins.cx1;
                        y1 = coins.cy1;
                    }
                    if (x2 < 0 || y2 < 0 || x2 >= map.length || y2 >= map[0].length) {
                        count++;
                    } else if (map[x2][y2] == WALL) {
                        x2 = coins.cx2;
                        y2 = coins.cy2;
                    }

                    if (count == 1) {
                        return time;
                    } else if (count == 0 && !visited[x1][y1][x2][y2]) {
                        Coins newCoins = new Coins(x1, y1, x2, y2);
                        next.add(newCoins);
                        newCoins.setVisit(visited);
                    }
                }
            }

            queue = next;
            if (time == 10) {
                break;
            }
        }

        return -1;
    }

}

