import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [boj] 22865. 가장 먼 곳
 */
public class boj22865 {
    static class Edge implements Comparable<Edge> {
        int vertex;
        int distance;

        public Edge(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.distance, o.distance);
        }
    }
    /**
     * N: 자취할 땅 후보의 수, M: 도로의 수
     */
    static int N, M, result;
    /**
     * friendHome: 친구 A/B/C가 사는 위치
     */
    static int[] friendHome = new int[3], finalDistance, distance;
    static ArrayList<Edge>[] list;
    static final int MAX = 1_000_000_000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < 3; i++) {
            friendHome[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        list = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int landD = Integer.parseInt(st.nextToken());
            int landE = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            list[landD].add(new Edge(landE, dist));
            list[landE].add(new Edge(landD, dist));
        }

        finalDistance = new int[N + 1];
        Arrays.fill(finalDistance, MAX);
        distance = new int[N + 1];
        for(int startPoint: friendHome) {
            Arrays.fill(distance, MAX);

            dijkstra(startPoint);

            for(int i = 1; i <= N; i++) {
                finalDistance[i] = Math.min(finalDistance[i], distance[i]);
            }
        }

        result = 1;
        for(int i = 2; i <= N; i++) {
            if(finalDistance[result] < finalDistance[i]) {
                result = i;
            }
        }

        System.out.println(result);
    }

    public static void dijkstra(int startPoint) {
        distance[startPoint] = 0;

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Edge(startPoint, 0));

        while(!priorityQueue.isEmpty()) {
            Edge now = priorityQueue.poll();

            if(distance[now.vertex] < now.distance) {
                continue;
            }

            for(Edge next: list[now.vertex]) {
                if(distance[next.vertex] > next.distance + now.distance) {
                    distance[next.vertex] = next.distance + now.distance;
                    priorityQueue.offer(new Edge(next.vertex, distance[next.vertex]));
                }
            }
        }
    }
}
