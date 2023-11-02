import java.io.*;
import java.util.*;

public class boj18223 {

    static List<Node>[] graph;
    static int v;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int p = Integer.parseInt(st.nextToken());

        graph = new ArrayList[v + 1];

        for (int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, weight));
            graph[to].add(new Node(from, weight));
        }

        int[] dist = dijkstra(1);
        int startToGoal = dist[v];
        int toGunwoo = dist[p];
        int gunwooToGoal = dijkstra(p)[v];

        System.out.println(startToGoal >= toGunwoo + gunwooToGoal ? "SAVE HIM" : "GOOD BYE");
    }

    static int[] dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
        int[] dist = new int[v + 1];
        Arrays.fill(dist, 20000);
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