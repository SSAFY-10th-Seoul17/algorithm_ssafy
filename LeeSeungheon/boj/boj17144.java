import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj17144 {

    static final int[] dx = {0, -1, 0, 1};
    static final int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken()); // 초

        int[][] map = new int[R][C];
        int sum = 2;
        int cleanerRow = 0;

        for (int row = 0; row < R; row++) {
            st = new StringTokenizer(br.readLine());

            for (int column = 0; column < C; column++) {
                sum += map[row][column] = Integer.parseInt(st.nextToken());
            }

            if (map[row][0] == -1) {
                cleanerRow = row;
            }
        }

        System.out.println(sum - solve(map, T, cleanerRow - 1));
    }

    private static int solve(int[][] map, int t, int cleanerRow) {

        int clean = 0;
        int[][] tempMap = new int[map.length][map[0].length];

        while (t-- > 0) {

            for (int row = 0; row < map.length; row++) {
                System.arraycopy(map[row], 0, tempMap[row], 0, map[0].length);
            }

            for (int row = 0; row < map.length; row++) {
                for (int column = 0; column < map[0].length; column++) {
                    if (map[row][column] <= 4) {
                        continue;
                    }
                    int diff = map[row][column] / 5;

                    for (int i = 0; i < 4; i++) {
                        int nextRow = row + dy[i];
                        int nextColumn = column + dx[i];

                        if (nextRow >= map.length || nextRow < 0 || nextColumn >= map[0].length || nextColumn < 0
                                || (nextColumn == 0 && (nextRow == cleanerRow || nextRow == cleanerRow + 1))) {
                            continue;
                        }

                        tempMap[row][column] -= diff;
                        tempMap[nextRow][nextColumn] += diff;
                    }
                }
            }

            // 상단
            System.arraycopy(tempMap[0], 1, map[0], 0, map[0].length - 1);
            System.arraycopy(tempMap[cleanerRow], 1, map[cleanerRow], 2, map[0].length - 2);

            for (int row = 1; row < cleanerRow; row++) {
                map[row][0] = tempMap[row - 1][0];
                map[row][map[0].length - 1] = tempMap[row + 1][map[0].length - 1];
                System.arraycopy(tempMap[row], 1, map[row], 1, map[0].length - 2);
            }
            clean += tempMap[cleanerRow - 1][0];
            map[0][map[0].length - 1] = tempMap[1][map[0].length - 1];
            map[cleanerRow][1] = 0;

            //하단
            System.arraycopy(tempMap[cleanerRow + 1], 1, map[cleanerRow + 1], 2, map[0].length - 2);
            System.arraycopy(tempMap[map.length - 1], 1, map[map.length - 1], 0, map[0].length - 1);

            for (int row = cleanerRow + 2; row < map.length - 1; row++) {
                map[row][map[0].length - 1] = tempMap[row - 1][map[0].length - 1];
                map[row][0] = tempMap[row + 1][0];
                System.arraycopy(tempMap[row], 1, map[row], 1, map[0].length - 2);
            }

            clean += tempMap[cleanerRow + 2][0];
            map[map.length - 1][map[0].length - 1] = tempMap[map.length - 2][map[0].length - 1];
            map[cleanerRow + 1][1] = 0;

        }
        return clean;
    }
}
