import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj2982 {
    static class Edge {
        int to;
        int time;

        public Edge(int to, int weight) {
            this.to = to;
            this.time = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        int[] path = new int[g];
        for (int i = 0; i < g; i++) {
            path[i] = Integer.parseInt(st.nextToken());
        }

        List<List<Edge>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Edge(b, l));
            graph.get(b).add(new Edge(a, l));
        }

        System.out.println(getShortestPath(start, end, graph, getPaths(n, k, path, graph)));
    }

    private static int[][][] getPaths(int n, int k, int[] path, List<List<Edge>> graph) {
        int[][][] paths = new int[n + 1][n + 1][2];
        int time = -k;
        int prev = path[0];

        for (int i = 1; i < path.length; i++) {
            for (Edge edge : graph.get(prev)) {
                if (edge.to == path[i]) {
                    paths[prev][edge.to][0] = paths[edge.to][prev][0] = time;
                    paths[edge.to][prev][1] = paths[prev][edge.to][1] = time += edge.time;
                    prev = edge.to;
                    break;
                }
            }
        }
        return paths;
    }

    private static int getShortestPath(int start, int end, List<List<Edge>> graph, int[][][] paths) {
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(e -> e.time));
        int[] distances = new int[graph.size()];
        Arrays.fill(distances, 1000_000_000);
        queue.add(new Edge(start, 0));
        distances[start] = 0;

        while (!queue.isEmpty()) {
            Edge cur = queue.poll();
            if (cur.time > distances[cur.to]) {
                continue;
            } else if (cur.to == end) {
                break;
            }

            for (Edge edge : graph.get(cur.to)) {
                int time = cur.time + edge.time;
                if (cur.time >= paths[cur.to][edge.to][0] && cur.time <= paths[cur.to][edge.to][1]) {
                    time += paths[cur.to][edge.to][1] - cur.time;
                }

                if (time < distances[edge.to]) {
                    distances[edge.to] = time;
                    queue.add(new Edge(edge.to, time));
                }
            }
        }


        return distances[end];
    }

}
