import java.io.*;
import java.util.StringTokenizer;

public class Boj17484 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] fuels = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < m; j++) {
                fuels[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(getMinFuel(m, fuels, n));
    }

    static int getMinFuel(int m, int[][] fuels, int n) {
        int minFuel = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            minFuel = Math.min(minFuel, dfs(fuels, n - 1, i, 0));
        }

        return minFuel;
    }


    static int dfs(int[][] fuels, int x, int y, int dir) {
        if (x == 0) {
            return fuels[x][y];
        }

        int minFuel = Integer.MAX_VALUE;
        if (y > 0 && dir != 1) {
            minFuel = Math.min(minFuel, dfs(fuels, x - 1, y - 1, 1));
        }

        if (dir != 2) {
            minFuel = Math.min(minFuel, dfs(fuels, x - 1, y, 2));
        }

        if (y < fuels[0].length - 1 && dir != 3) {
            minFuel = Math.min(minFuel, dfs(fuels, x - 1, y + 1, 3));
        }

        return fuels[x][y] + minFuel;
    }

}
