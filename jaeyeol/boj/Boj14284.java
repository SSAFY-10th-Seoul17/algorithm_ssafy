import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj14284 {
    static class Edge implements Comparable<Edge> {
        int to;
        int weight;
        Edge next;

        public Edge(int to, int weight, Edge next) {
            this.to = to;
            this.weight = weight;
            this.next = next;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Edge[] edges = new Edge[n + 1];
        for (int i = 1; i <= n; i++) {
            edges[i] = new Edge(i, 0, null);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges[a].next = new Edge(b, w, edges[a].next);
            edges[b].next = new Edge(a, w, edges[b].next);
        }

        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        System.out.println(getDistance(edges, n, s, t));
    }

    private static int getDistance(Edge[] edges, int n, int s, int t) {
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];
        queue.add(edges[s]);

        while (!queue.isEmpty()) {
            Edge current = queue.poll();
            if (current.to == t) {
                return current.weight;
            }
            if (visited[current.to]) {
                continue;
            }
            visited[current.to] = true;

            for (Edge next = edges[current.to].next; next != null; next = next.next) {
                if (!visited[next.to]) {
                    next.weight += current.weight;
                    queue.add(next);
                }
            }
        }

        return -1;
    }

}

