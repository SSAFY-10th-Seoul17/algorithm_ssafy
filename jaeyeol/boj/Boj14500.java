import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj14500 {
    static int[][] map;
    static int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    static int n, m;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                result = Math.max(result, dfs(i, j, 1));
                result = Math.max(result, spacialCase(i, j));
            }
        }

        System.out.println(result);
    }

    // 4칸 이동 경로
    private static int dfs(int i, int j, int depth) {
        if (depth > 3) {
            return map[i][j];
        }
        visited[i][j] = true;

        int max = 0;
        for (int[] dir : dirs) {
            int x = dir[0] + i;
            int y = dir[1] + j;

            if (x < 0 || y < 0 || x >= n || y >= m || visited[x][y]) {
                continue;
            }

            max = Math.max(max, dfs(x, y, depth + 1));
        }

        visited[i][j] = false;
        return map[i][j] + max;
    }

    // ㅗ 모양
    private static int spacialCase(int i, int j) {
        int sum = map[i][j];
        int min = Integer.MAX_VALUE;
        int count = 0;

        for (int[] dir : dirs) {
            int x = dir[0] + i;
            int y = dir[1] + j;

            if (x < 0 || y < 0 || x >= n || y >= m) {
                continue;
            }

            count++;
            sum += map[x][y];
            min = Math.min(min, map[x][y]);
        }

        return count < 3 ? 0 : (count == 3 ? sum : sum - min);
    }

}

