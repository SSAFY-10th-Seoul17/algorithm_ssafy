import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj18243 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] distances = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(distances[i], 1000);
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            distances[a][b] = distances[b][a] = 1;
        }

        System.out.println(whatIsNetwork(distances, n));
    }

    private static String whatIsNetwork(int[][] distances, int n) {
        for (int via = 1; via <= n; via++) {
            for (int i = 1; i <= n; i++) {
                if (via == i) {
                    continue;
                }

                for (int j = 1; j <= n; j++) {
                    if (distances[i][via] + distances[via][j] < distances[i][j]) {
                        distances[i][j] = distances[i][via] + distances[via][j];
                    }
                }
            }
        }

        int maxDistance = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = i + 1; j <= n; j++) {
                maxDistance = Math.max(maxDistance, distances[i][j]);
            }
        }

        return maxDistance > 6 ? "Big World!" : "Small World!";
    }

}
