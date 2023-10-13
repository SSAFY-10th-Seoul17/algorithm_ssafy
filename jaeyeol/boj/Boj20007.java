import java.io.*;
import java.util.*;

public class Boj20007 {
    static class Edge{
        int to;
        int distance;

        public Edge(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int distance = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());

        List<List<Edge>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, d));
            graph.get(b).add(new Edge(a, d));
        }

        System.out.println(getDays(graph, distance >> 1, start));
    }

    static final int MAX_DIST = 1_000_000_000;
  
    private static int getDays(List<List<Edge>> graph, int limit, int start) {
        int[] distances = new int[graph.size()];
        Arrays.fill(distances, MAX_DIST);
        distances[start] = 0;
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparingInt(e -> e.distance));
        queue.add(new Edge(start, 0));

        while (!queue.isEmpty()) {
            Edge cur = queue.poll();

            if (cur.distance > distances[cur.to]) {
                continue;
            }

            for (Edge edge : graph.get(cur.to)) {
                if (cur.distance + edge.distance < distances[edge.to]) {
                    distances[edge.to] = cur.distance + edge.distance;
                    queue.add(new Edge(edge.to, distances[edge.to]));
                }
            }
        }

        return getDayCount(distances, limit);
    }

    private static int getDayCount(int[] distances, int limit) {
        Arrays.sort(distances);
        if (distances[distances.length - 1] > limit) {
            return -1;
        }
        
        int days = 1;
        int walkingDistance = 0;
        for (int distance : distances) {
            walkingDistance += distance;
            if (walkingDistance > limit) {
                days++;
                walkingDistance = distance;
            }
        }

        return days;
    }

}

