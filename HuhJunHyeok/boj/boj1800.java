import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [boj] 1800. 인터넷 설치
 */
public class boj1800 {
    static class Vertex implements Comparable<Vertex> {
        int adjVertex;
        int weight;

        public Vertex(int adjVertex, int weight) {
            this.adjVertex = adjVertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
    /**
     * N: 학생들의 번호, P: 케이블 선의 수, K: 공짜로 제공하는 케이블선의 수
     * maxWeight: 주어진 케이블의 금액(가중치) 중 가장 큰 값
     * minMoney: 원장 선생님이 내게 되는 돈의 최솟값 (불가능한 경우 -1)
     */
    static int N, P, K, maxWeight, minMoney = -1;
    /**
     * adjList: 인접 리스트
     */
    static ArrayList<Vertex>[] adjList;
    static int[] dist, prevVertex;
    static boolean[] visited;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<>();
        }
        for(int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adjList[from].add(new Vertex(to, weight));
            adjList[to].add(new Vertex(from, weight));

            maxWeight = maxWeight > weight ? maxWeight : weight;
        }

        prevVertex = new int[N + 1];

        parametricSearch();

        System.out.println(minMoney);

    }

    public static void parametricSearch() {
        int low = 0;
        int high = maxWeight;

        while(low <= high) {
            int mid = (low + high) / 2;

            if(dijkstra(mid)) {
                minMoney = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
    }

    /**
     * 1번 정점을 시작 정점으로 다익스트라
     */
    public static boolean dijkstra(int mid) {
        visited = new boolean[N + 1];
        dist = new int[N + 1];
        for(int i = 0; i <= N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        dist[1] = 0; // 시작 정점의 dist 배열 값을 0으로 설정.

        PriorityQueue<Vertex> priorityQueue  = new PriorityQueue<>();
        priorityQueue.offer(new Vertex(1, 0));

        while(!priorityQueue.isEmpty()) {
            Vertex nowVertex = priorityQueue.poll();
            int nowAdjVertex = nowVertex.adjVertex;


            if(visited[nowAdjVertex]) {
                continue;
            }
            visited[nowAdjVertex] = true;

            for(Vertex next: adjList[nowAdjVertex]) {
                int nextWeight = nowVertex.weight;

                if(next.weight > mid) {
                    nextWeight++;
                }

                if(dist[next.adjVertex] > nextWeight) {
                    dist[next.adjVertex] = nextWeight;

                    priorityQueue.offer(new Vertex(next.adjVertex, dist[next.adjVertex]));
                }
            }
        }
//        System.out.println(dist[N]);
        return dist[N] <= K;
    }
}
