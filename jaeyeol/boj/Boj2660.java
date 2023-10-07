import java.io.*;
import java.util.*;

public class Boj2660 {
    static final int MAX_DIST = 1_000_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] dist = new int[n + 1][n + 1];

        Arrays.fill(dist[0], MAX_DIST);
        for (int i = 1; i <= n; i++) {
            System.arraycopy(dist[0], 0, dist[i], 0, dist[0].length);
        }

        String read;
        while (!(read = br.readLine()).equals("-1 -1")) {
            StringTokenizer st = new StringTokenizer(read);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            dist[a][b] = dist[b][a] = 1;
        }

        floydWarshall(dist, n);

        System.out.println(getResult(n, dist));
    }

    private static String getResult(int n, int[][] dist) {
        int[] maxDistances = new int[n + 1];
        int minDistance = MAX_DIST;
        int count = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (dist[i][j] != MAX_DIST) {
                    maxDistances[i] = Math.max(maxDistances[i], dist[i][j]);
                }
            }
            if (minDistance > maxDistances[i]) {
                minDistance = maxDistances[i];
                count = 1;
            } else if (minDistance == maxDistances[i]) {
                count++;
            }
        }

        StringBuilder result = new StringBuilder();
        result.append(minDistance).append(" ").append(count).append("\n");
        for (int i = 1; i <= n; i++) {
            if (maxDistances[i] == minDistance) {
                result.append(i).append(" ");
            }
        }

        return result.toString();
    }

    private static void floydWarshall(int[][] dist, int n) {
        for (int via = 1; via <= n; via++) {

            for (int from = 1; from <= n; from++) {
                if (via == from) {
                    continue;
                }

                for (int to = 1; to <= n; to++) {
                    if (from != to) {
                        dist[from][to] = Math.min(dist[from][to], dist[from][via] + dist[via][to]);
                    }
                }
            }
        }

    }

}

