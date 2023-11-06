import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj25682 {
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int square = k * k;
        map = new int[n + 1][m + 1];
        int result = Integer.MAX_VALUE;

        for (int i = 1; i <= n; i++) {
            String str = br.readLine();
            int accSum = 0;

            for (int j = 1; j <= m; j++) {
                accSum += ((i + j) % 2 == 0) == (str.charAt(j - 1) == 'B') ? 0 : 1;
                map[i][j] = accSum + map[i - 1][j];
            }
        }

        for (int i = k; i <= n; i++) {
            for (int j = k; j <= m; j++) {
                result = Math.min(result, getMinCount(i, j, k, square));
            }
        }

        System.out.print(result);
    }

    private static int getMinCount(int i, int j, int k, int square) {
        int sum = map[i][j] - map[i - k][j] - map[i][j - k] + map[i - k][j - k];
        return Math.min(sum, square - sum);
    }

}
