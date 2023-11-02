import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [boj] 18223. 민준이와 마산 그리고 건우
 */
public class boj18223 {
    static class Edge implements Comparable<Edge> {
        int v;
        int dist;

        public Edge(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.dist, o.dist);
        }
    }
    /**
     * V: 정점의 수, E: 간선의 수, P: 건우가 위치한 정점
     */
    static int V, E, P;
    static ArrayList<Edge>[] adjList;
    static boolean[] visited;
    static int[] dist;
    static PriorityQueue<Edge> priorityQueue;
    static final int MAX = Integer.MAX_VALUE / 2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[V + 1];
        for(int i = 1; i <= V; i++) {
            adjList[i] = new ArrayList<>();
        }
        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            adjList[from].add(new Edge(to, dist));
            adjList[to].add(new Edge(from, dist));
        }

        System.out.println(dijkstra(1, V) == dijkstra(1, P) + dijkstra(P, V) ? "SAVE HIM" : "GOOD BYE");

    }

    public static int dijkstra(int startVertex, int endVertex) {
        visited = new boolean[V + 1];
        dist = new int[V + 1];
        Arrays.fill(dist, MAX);
        dist[startVertex] = 0;

        priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Edge(startVertex, dist[startVertex]));

        while(!priorityQueue.isEmpty()) {
            Edge now = priorityQueue.poll();

            if(visited[now.v]) {
                continue;
            }
            visited[now.v] = true;

            for(Edge next: adjList[now.v]) {
                if(!visited[next.v] && dist[next.v] > dist[now.v] + next.dist) {
                    dist[next.v] = dist[now.v] + next.dist;
                    priorityQueue.offer(new Edge(next.v, dist[next.v]));
                }
            }
        }

        return dist[endVertex];
    }
}
