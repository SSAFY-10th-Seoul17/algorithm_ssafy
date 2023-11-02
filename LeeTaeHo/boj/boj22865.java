import java.io.*;
import java.util.*;

public class boj22865 {
    static int n;
    static List<Node>[] graph;
    static int[] friends = new int[3];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            friends[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, weight));
            graph[to].add(new Node(from, weight));
        }

        int[][] dist = new int[3][];

        for (int i = 0; i < 3; i++) {
            dist[i] = dijkstra(friends[i]);
        }

        int max = -1;
        int idx = -1;

        for (int i = 1; i <= n; i++) {
            int a = dist[0][i];
            int b = dist[1][i];
            int c = dist[2][i];

            int min = Math.min(a, Math.min(b, c));

            if(max < min){
                max = min;
                idx = i;
            }
        }

        System.out.println(idx);

    }

    public static int[] dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;
        pq.offer(new Node(start, 0));
        while(!pq.isEmpty()){
            Node now = pq.poll();
            if(dist[now.idx] < now.weight) continue;
            for (Node next : graph[now.idx]) {
                if(dist[now.idx] + next.weight < dist[next.idx]){
                    dist[next.idx] = dist[now.idx] + next.weight;
                    pq.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }
        return dist;
    }

    static class Node{
        int idx;
        int weight;

        public Node(int idx, int weight){
            this.idx = idx;
            this.weight = weight;
        }
    }
}