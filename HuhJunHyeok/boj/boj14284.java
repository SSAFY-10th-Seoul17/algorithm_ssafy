import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [boj] 14284. 간선 이어가기 2
 */
public class boj14284 {
    static class VertexEdge implements Comparable<VertexEdge> {
        int idx;
        int weight;

        public VertexEdge(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }

        @Override
        public int compareTo(VertexEdge o) {
            return this.weight < o.weight ? -1 : 1;
        }
    }
    /**
     * n: 정점의 수, m: 간선리스트의 간선 수
     * s,t : 간선 추가를 멈추는 정점의 정보
     */
    static int n, m, s, t;
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

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        vertexInfo = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            vertexInfo[i] = new ArrayList<>();
        }
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            vertexInfo[a].add(new VertexEdge(b, c));
            vertexInfo[b].add(new VertexEdge(a, c));
        }

        st = new StringTokenizer(br.readLine(), " ");
        s = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        // s에서의 그래프 최단 경로를 다익스트라 알고리즘을 적용.
        visited = new boolean[n + 1];
        dist = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            dist[i] = MAX;
        }
        dijkstra();

        // 문제에서 요구하는 s와 t가 연결되는 시점의 간선의 가중치 합의 최솟값은 dist[t]
        System.out.println(dist[t]);
    }

    /**
     * 정점 s를 시작 정점으로 하여 그래프의 최단 경로를 구하는 다익스트라 알고리즘 구현 함수
     */
    public static void dijkstra() {
        dist[s] = 0; // 시작 정점의 dist 배열 값을 0으로 설정.

        PriorityQueue<VertexEdge> priorityQueue  = new PriorityQueue<>();
        priorityQueue.offer(new VertexEdge(s, 0));

        while(!priorityQueue.isEmpty()) {
            int nowVertex = priorityQueue.poll().idx;

            if(visited[nowVertex]) {
                continue;
            }
            visited[nowVertex] = true;

            for(VertexEdge next: vertexInfo[nowVertex]) {
                if(dist[next.idx] > dist[nowVertex] + next.weight) {
                    dist[next.idx] = dist[nowVertex] + next.weight;

                    priorityQueue.offer(new VertexEdge(next.idx, dist[next.idx]));
                }
            }
        }

//        // 확인용
//        for(int val : dist) {
//            if(val == MAX) {
//                System.out.print(0 + " ");
//            } else {
//                System.out.print(val + " ");
//            }
//        }
    }
}
