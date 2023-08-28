import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj1162 {
    static class Edge implements Comparable<Edge>{
        int to;
        long time;
        int k;

        public Edge(int to, long time, int k) {
            this.to = to;
            this.time = time;
            this.k = k;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.time, o.time);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<List<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Edge(b, time, 0));
            graph.get(b).add(new Edge(a, time, 0));
        }


        System.out.println(getMinTime(graph, n, k));
    }

    static final long MAX_TIME = 1_000_000_000_000L;
    private static long getMinTime(List<List<Edge>> graph, int n ,int k) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0, k));

        long[][] times = new long[n + 1][k + 1];
        for (long[] time : times) {
            Arrays.fill(time, MAX_TIME);
        }
        Arrays.fill(times[1], 0);

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (cur.to == n) {
                return cur.time;
            }
            if (cur.time > times[cur.to][cur.k]) {
                continue;
            }

            for (Edge edge : graph.get(cur.to)) {
                long sumTime = cur.time + edge.time;

                if (sumTime < times[edge.to][cur.k]) { // 미포장
                    times[edge.to][cur.k] = sumTime;
                    pq.add(new Edge(edge.to, sumTime, cur.k));
                }

                int curK = cur.k - 1;
                if (cur.k > 0 && cur.time < times[edge.to][curK]) { // 포장
                    times[edge.to][curK] = cur.time;
                    pq.add(new Edge(edge.to, cur.time, curK));
                }
            }
        }

        return -1;
    }

}

