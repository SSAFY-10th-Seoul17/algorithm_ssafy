import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj20058 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        int[][] map = new int[1 << n][1 << n];
        for (int i = 0; i < map.length; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < map.length; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        int[][] newMap = new int[map.length][map.length];
        for (int i = 0; i < q; i++) {
            fireStorm(map, newMap, Integer.parseInt(st.nextToken()));
            int[][] temp = newMap;
            newMap = map;
            map = temp;

            downIce(map, newMap);
            temp = newMap;
            newMap = map;
            map = temp;
        }

        printResult(map);
    }

    private static void downIce(int[][] map, int[][] newMap) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] == 0) {
                    newMap[i][j] = 0;
                    continue;
                }
                int count = 0;
                for (int[] dir : dirs) {
                    int x = dir[0] + i;
                    int y = dir[1] + j;

                    if (x < 0 || y < 0 || x >= map.length || y >= map.length || map[x][y] == 0) {
                        continue;
                    }
                    count++;
                }

                newMap[i][j] = (count < 3) ? map[i][j] - 1 : map[i][j];
            }
        }

    }

    private static void printResult(int[][] map) {
        int totalSum = 0;
        int maxCount = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                totalSum += map[i][j];
            }
        }

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                if (map[i][j] > 0) {
                    maxCount = Math.max(maxCount, dfs(map, i, j));
                }
            }
        }

        System.out.println(totalSum + "\n" + maxCount);
    }

    static final int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    private static int dfs(int[][] map, int i, int j) {
        int count = 1;
        map[i][j] = 0;
        for (int[] dir : dirs) {
            int x = dir[0] + i;
            int y = dir[1] + j;

            if (x < 0 || y < 0 || x >= map.length || y >= map.length || map[x][y] == 0) {
                continue;
            }

            count += dfs(map, x, y);
        }

        return count;
    }

    private static void fireStorm(int[][] map, int[][] newMap, int size) {
        int length = 1 << size;

        for (int i = 0; i < map.length; i++) {

            for (int j = 0; j < map.length; j+= length) {
                int row = (i / length) * length;
                int col = j + (length - (i % length) - 1);

                for (int k = 0; k < length; k++) {
                    newMap[row + k][col] = map[i][j + k];
                }
            }
        }

    }

}

