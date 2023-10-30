import java.io.*;
import java.util.*;

public class Boj22865 {
    static class Node {
        int to;
        int distance;

        public Node(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] house = new int[3];
        for (int i = 0; i < house.length; i++) {
            house[i] = Integer.parseInt(st.nextToken());
        }

        List<List<Node>> graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }


        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, l));
            graph.get(b).add(new Node(a, l));
        }

        System.out.println(getShortestLand(graph, house));
    }

    static final int MAX_DISTANCE = 1_000_000_000;

    private static int getShortestLand(List<List<Node>> graph, int[] house) {
        int[][] distances = new int[house.length][graph.size()];
        for (int i = 0; i < house.length; i++) {
            distances[i] = dijkstra(graph, house[i]);
        }

        int distance = 0;
        int node = 0;

        for (int i = 1; i < graph.size(); i++) {
            int minDistance = Math.min(distances[0][i], Math.min(distances[1][i], distances[2][i]));

            if (minDistance > distance) {
                distance = minDistance;
                node = i;
            }
        }

        return node;
    }

    private static int[] dijkstra(List<List<Node>> graph, int start) {
        int[] distance = new int[graph.size()];
        Arrays.fill(distance, MAX_DISTANCE);
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        distance[start] = 0;
        queue.add(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.distance > distance[cur.to]) {
                continue;
            }

            for (Node next : graph.get(cur.to)) {
                if (cur.distance + next.distance < distance[next.to]) {
                    distance[next.to] = cur.distance + next.distance;
                    queue.add(new Node(next.to, distance[next.to]));
                }
            }
        }

        return distance;
    }

}

