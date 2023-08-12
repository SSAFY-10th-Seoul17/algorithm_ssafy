import java.io.*;
import java.util.*;

//현수막
public class Boj14716 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[][] map = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = st.nextToken().equals("1");
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j]) {
                    dfs(map, i, j);
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    static int[][] dirs = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1},
            {1, -1}, {1, 0}, {1, 1}};

    private static void dfs(boolean[][] map, int i, int j) {
        map[i][j] = false;

        for (int[] dir : dirs) {
            int x = i + dir[0];
            int y = j + dir[1];

            if (x < 0 || y < 0 || x >= map.length || y >= map[x].length || !map[x][y]) {
                continue;
            }
            dfs(map, x, y);
        }
    }


}

