import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [boj] 15971. 두 로봇
 */
public class boj15971 {
    static class VertexEdge implements Comparable<VertexEdge> {
        int idx;
        int weight;
        int maxWeight; // dijkstra에서 지나온 경로 중 최장 경로.

        public VertexEdge(int idx, int weight, int maxWeight) {
            this.idx = idx;
            this.weight = weight;
            this.maxWeight = maxWeight;
        }

        @Override
        public int compareTo(VertexEdge o) {
            return this.weight < o.weight ? -1 : 1;
        }
    }

    /**
     * N: 동굴(정점)의 수, M: 정점 수로 도출된 간선(통로)의 수 N - 1.
     * R1,R2 : 두 로봇이 위치한 정점
     * answer: 두 로봇이 서로 통신하기 위해 현재 위치에서 이동해야 하는 거리 합의 최솟값
     */
    static int N, M, R1, R2, answer;
    /**
     * visited: 정점 방문 체크.
     */
    static boolean[] visited;
    /**
     * dist: 출발지로부터 각 정점의 거리 기록.
     */
    static int[] dist;
    /**
     * vertexInfo: 정점(Vertex)별로 간선의 정보를 나타내는 ArrayList
     */
    static ArrayList<VertexEdge>[] vertexInfo;
    static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = N -1;
        R1 = Integer.parseInt(st.nextToken());
        R2 = Integer.parseInt(st.nextToken());

        vertexInfo = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            vertexInfo[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            vertexInfo[a].add(new VertexEdge(b, c, 0));
            vertexInfo[b].add(new VertexEdge(a, c, 0));
        }

        // R1에서의 그래프 최단 경로를 다익스트라 알고리즘을 적용.
        // R1에서 R2까지의 이동거리의 값 - 그 과정에서의 최장 경로의 길이가 답이다.
        visited = new boolean[N + 1];
        dist = new int[N + 1];
        for(int i = 0; i <= N; i++) {
            dist[i] = MAX;
        }
        dijkstra();

        System.out.println(answer);
    }

    /**
     * R1을 시작 정점으로 하여 다익스트라 알고리즘 구현 함수.
     * 지나온 경로 중 최장 경로의 길이 저장 포함.
     */
    public static void dijkstra() {
        dist[R1] = 0; // 시작 정점의 dist 배열 값을 0으로 설정.

        PriorityQueue<VertexEdge> priorityQueue  = new PriorityQueue<>();
        priorityQueue.offer(new VertexEdge(R1, 0, 0));

        while(!priorityQueue.isEmpty()) {
            VertexEdge now = priorityQueue.poll();
            int nowVertex = now.idx;
            int nowMaxWeight = now.maxWeight;

            if(nowVertex == R2) {
                answer = now.weight - nowMaxWeight;
                break;
            }
            if(visited[nowVertex]) {
                continue;
            }
            visited[nowVertex] = true;

            for(VertexEdge next: vertexInfo[nowVertex]) {
                if(dist[next.idx] > dist[nowVertex] + next.weight) {
                    dist[next.idx] = dist[nowVertex] + next.weight;

                    priorityQueue.offer(new VertexEdge(next.idx, dist[next.idx], nowMaxWeight > next.weight ? nowMaxWeight : next.weight));
                }
            }
        }
    }
}
