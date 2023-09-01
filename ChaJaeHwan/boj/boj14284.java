import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class boj14284 {

    static class Node {
        int index,distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

    }
    static int n, m, a, b, c, s, t;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    public static int[] d;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }
        Arrays.fill(d, Integer.MAX_VALUE);

        st = new StringTokenizer(br.readLine());

        s = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        dijkstra(s, t);


    }

    static void dijkstra(int start, int end){
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> n1.distance-n2.distance);
        pq.offer(new Node(start, 0));

        d[start] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            int dist = node.distance;
            int now = node.index;

            if (d[now] < dist){
                continue;
            }

            for (Node n : graph.get(now)) {
                int cost = d[now] + n.distance;

                if (cost < d[n.index]){
                    d[n.index] = cost;
                    pq.offer(new Node(n.index, cost));
                }
            }
        }

        System.out.println(d[end]);
    }
}
