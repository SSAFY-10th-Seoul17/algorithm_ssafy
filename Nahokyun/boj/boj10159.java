package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj10159 {

    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        int m = Integer.parseInt(br.readLine());

        int[][] dist = new int[n + 1][n + 1];
        int[][] dist2 = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], 10000000);
            Arrays.fill(dist2[i], 10000000);
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            dist[first][second] = 1;
            dist2[second][first] = 1;
        }

        floyd(dist);
        floyd(dist2);

        int[] result = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i != j) {
                    if (dist[i][j] != 10000000)
                        result[i]++;
                    if (dist2[i][j] != 10000000)
                        result[i]++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(n - result[i] - 1).append('\n');
        }

        System.out.println(sb);
    }

    private static void floyd(int[][] dist) {
        for (int k = 1; k <= n; k++) {

            for (int i = 1; i <= n; i++) {
                if (k == i) {
                    continue;
                }
                for (int j = 1; j <= n; j++) {
                    if (i == j || j == k) {
                        continue;
                    }

                    dist[i][j] = Math.min(dist[i][k] + dist[k][j], dist[i][j]);
                }
            }
        }

    }

}
