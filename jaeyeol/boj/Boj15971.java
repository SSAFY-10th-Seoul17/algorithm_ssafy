import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj15971 {
    static class Edge implements Comparable<Edge> {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int robot1 = Integer.parseInt(st.nextToken());
        int robot2 = Integer.parseInt(st.nextToken());
        if (robot1 == robot2) {
            System.out.println(0);
            return;
        }

        List<List<Edge>> graph = new ArrayList<>(n);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, w));
            graph.get(b).add(new Edge(a, w));
        }

        System.out.println(getMeetingDistance(graph, robot1, robot2));
    }

    static final int MAX_DISTANCE = 1_000_000_000;

    private static int getMeetingDistance(List<List<Edge>> graph, int robot1, int robot2) {
        int[] distance1 = getDistance(graph, robot1);
        int[] distance2 = getDistance(graph, robot2);

        int meetingDistance = MAX_DISTANCE;

        for (int n = 1; n < graph.size(); n++) {
            for (Edge edge : graph.get(n)) {
                meetingDistance = Math.min(meetingDistance, distance1[n] + distance2[edge.to]);
            }
        }

        return meetingDistance;
    }

    private static int[] getDistance(List<List<Edge>> graph, int robot) {
        int[] distances = new int[graph.size()];
        Arrays.fill(distances, MAX_DISTANCE);
        distances[robot] = 0;

        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(robot, 0));
        distances[robot] = 0;

        while (!queue.isEmpty()) {
            Edge cur = queue.poll();

            if (cur.weight > distances[cur.to]) {
                continue;
            }

            for (Edge edge : graph.get(cur.to)) {
                if (cur.weight + edge.weight < distances[edge.to]) {
                    distances[edge.to] = cur.weight + edge.weight;
                    queue.add(new Edge(edge.to, distances[edge.to]));
                }
            }
        }

        return distances;
    }

}

