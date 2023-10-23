import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [boj] 13424. 비밀 모임
 */
public class boj13424 {
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
     * T: 테스트 케이스의 수, N: 방(정점)의 수, M: 비밀통로의 수(간선리스트의 간선 수), K: 친구의 수
     */
    static int T, N, M, K;
    /**
     * visited: 정점 방문 체크.(dijkstra)
     */
    static boolean[] visited;
    /**
     * dist: 출발지로부터 각 정점의 거리 기록.(dijkstra) 모든 정점에서 다른 정점까지의 최단 거리 기록.(floydWarshall)
     */
    static int[][] dist;
    /**
     * friend: 친구들이 현재 위치해 있는 방의 정보.
     */
    static int[] friend;
    /**
     * vertexInfo: 정점(Vertex)별로 간선의 정보를 나타내는 ArrayList. (dijkstra)
     */
    static ArrayList<VertexEdge>[] vertexInfo;
//    static final int MAX = Integer.MAX_VALUE; // dijkstra
    static final int MAX = 1_000_000_000; // floydWarshall.
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

//            // dijkstra
//            vertexInfo = new ArrayList[N + 1];
//            for(int i = 1; i <= N; i++) {
//                vertexInfo[i] = new ArrayList<>();
//            }

            // floydWarshall
            dist = new int[N + 1][N + 1];
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    dist[i][j] = i == j ? 0 : MAX;
                }
            }

            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

//                // dijkstra
//                vertexInfo[a].add(new VertexEdge(b, c));
//                vertexInfo[b].add(new VertexEdge(a, c));

                // floydWarshall
                dist[a][b] = c;
                dist[b][a] = c;
            }

            K = Integer.parseInt(br.readLine());
            friend = new int[K];
            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < K; i++) {
                friend[i] = Integer.parseInt(st.nextToken());
            }

//            dijkstra();
            floydWarshall();

            findRoom();
        }
        System.out.print(sb.toString());
    }

    /**
     * 친구들이 있는 방을 시작 정점으로 하여 다익스트라 적용.
     */
    public static void dijkstra() {
        dist = new int[K][N + 1];
        for(int i = 0; i < K; i++) {
            for(int j = 0; j <= N; j++) {
                dist[i][j] = MAX;
            }
        }

        for(int i = 0; i < K; i++) {
            visited = new boolean[N + 1];

            int start = friend[i];

            dist[i][start] = 0; // 시작 정점의 dist 배열 값을 0으로 설정.

            PriorityQueue<VertexEdge> priorityQueue  = new PriorityQueue<>();
            priorityQueue.offer(new VertexEdge(start, 0));

            while(!priorityQueue.isEmpty()) {
                int nowVertex = priorityQueue.poll().idx;

                if(visited[nowVertex]) {
                    continue;
                }
                visited[nowVertex] = true;

                for(VertexEdge next: vertexInfo[nowVertex]) {
                    if(dist[i][next.idx] > dist[i][nowVertex] + next.weight) {
                        dist[i][next.idx] = dist[i][nowVertex] + next.weight;

                        priorityQueue.offer(new VertexEdge(next.idx, dist[i][next.idx]));
                    }
                }
            }
        }
    }

    /**
     * 플로이드 워셜 알고리즘 적용.
     * 점화식: D[i][j] = min(D[i][j], D[i][k] + D[k][j])
     */
    public static void floydWarshall() {
        for(int k = 1; k <= N; k++) { // 중간 정점이라 생각.
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    dist[i][j] = dist[i][j] < dist[i][k] + dist[k][j]
                            ? dist[i][j] : dist[i][k] + dist[k][j];
                }
            }
        }

//        // 확인용
//        for(int i = 1; i <= N; i++) {
//            for(int j = 1; j <= N; j++) {
//                System.out.print(dist[i][j] + " ");
//            }
//            System.out.println();
//        }
    }

    /**
     * 친구들의 이동 거리의 총합이 최소가 되도록 하는 모임 장소의 방 번호 결정.
     */
    public static void findRoom() {
        int ansRoom = -1;
        int ansDist = MAX;

        for(int i = 1; i <= N; i++) {
            int roomDistSum = 0;
            for(int j = 0; j < K; j++) {
//                roomDistSum += dist[j][i]; // dijkstra
                roomDistSum += dist[friend[j]][i]; // floydWarshall
            }

            if(roomDistSum < ansDist) {
                ansDist = roomDistSum;
                ansRoom = i;
            }
        }

        sb.append(ansRoom).append("\n");
    }
}
