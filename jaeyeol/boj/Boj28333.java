import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj28333 {
    static class Node {
        int to;
        Node next;

        public Node(int to, Node next) {
            this.to = to;
            this.next = next;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            Node[] graph = new Node[n + 1];
            Node[] reverseGraph = new Node[n + 1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new Node(i, null);
                reverseGraph[i] = new Node(i, null);
            }
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].next = new Node(b, graph[a].next);
                reverseGraph[b].next = new Node(a, reverseGraph[b].next);
            }

            boolean[] visited = getShortestPath(n, graph, reverseGraph);
            for (int i = 1; i < visited.length; i++) {
                if(visited[i]) {
                    result.append(i).append(' ');
                }
            }
            result.append('\n');
        }

        System.out.print(result);
    }

    static final Queue<Integer> queue = new ArrayDeque<>();
    private static boolean[] getShortestPath(int n, Node[] graph, Node[] reverseGraph) {
        int[] distances = new int[n + 1];
        Arrays.fill(distances, n + 1);
        distances[1] = 0;
        queue.add(1);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (Node next = graph[cur].next; next != null; next = next.next) {
                if (distances[cur] + 1 < distances[next.to]) {
                    distances[next.to] = distances[cur] + 1;
                    queue.add(next.to);
                }
            }
        }

        boolean[] visited = new boolean[n + 1];
        dfs(reverseGraph, distances, visited, n);

        return visited;
    }

    private static void dfs(Node[] graph, int[] distances, boolean[] visited, int cur) {
        visited[cur] = true;

        for (Node next = graph[cur].next; next != null; next = next.next) {
            if (distances[next.to] == distances[cur] - 1 && !visited[next.to]) { // 이 경로로 온거면
                dfs(graph, distances, visited, next.to);
            }
        }
    }

}
