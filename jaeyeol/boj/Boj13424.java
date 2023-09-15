import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj13424 {
    static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder result = new StringBuilder();

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            Edge[] edges = new Edge[m];
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                edges[i] = new Edge(a, b, w);
            }

            int k = Integer.parseInt(br.readLine());
            int[] rooms = new int[k];
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < k; i++) {
                rooms[i] = Integer.parseInt(st.nextToken());
            }

            result.append(getRoomNumber(edges, n, rooms)).append("\n");
        }

        System.out.println(result);
    }

    static final int MAX_DISTANCE = 10_000_000;
    private static int getRoomNumber(Edge[] edges, int n, int[] rooms) {
        int[][] distances = getInitDistance(edges, n);

        for (int via = 1; via <= n; via++) {

            for (int from = 1; from <= n; from++) {
                if (via == from || distances[from][via] == MAX_DISTANCE) {
                    continue;
                }

                for (int to = from + 1; to <= n; to++) {
                    if (distances[from][via] + distances[via][to] < distances[from][to]) {
                        distances[to][from] = distances[from][to] = distances[from][via] + distances[via][to];
                    }
                }
            }
        }

        return getRoomNumber(n, rooms, distances);
    }

    private static int[][] getInitDistance(Edge[] edges, int n) {
        int[][] distances = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(distances[i], MAX_DISTANCE);
            distances[i][i] = 0;
        }

        for (Edge edge : edges) {
            distances[edge.from][edge.to] = distances[edge.to][edge.from] = edge.weight;
        }
        return distances;
    }

    private static int getRoomNumber(int n, int[] rooms, int[][] distances) {
        int roomNumber = 0;
        int totalDistance = MAX_DISTANCE;

        for (int i = 1; i <= n; i++) {
            int distanceSum = 0;
            for (int room : rooms) {
                distanceSum += distances[room][i];
            }

            if (distanceSum < totalDistance) {
                roomNumber = i;
                totalDistance = distanceSum;
            }
        }
        return roomNumber;
    }


}

