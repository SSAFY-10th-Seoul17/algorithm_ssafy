import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * [boj] 20007. 떡 돌리기
 */
public class boj20007 {
    static class Vertex implements Comparable<Vertex> {
        int idx;
        int distance;

        public Vertex(int idx, int distance) {
            this.idx = idx;
            this.distance = distance;
        }

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.distance, o.distance);
        }
    }
    /**
     * N: 집의 수(=> 이웃집의 수는 N - 1), M: 양방향 도로의 수, X: 하루에 이동 가능한 최대 거리, Y: 탐색 시작이 되는 집(성현이의 집)
     * (2 ≤ N ≤ 1,000, 1 ≤ M ≤ 100,000, 1 ≤ X ≤ 10,000,000, 0 ≤ Y < N)
     * oneWayDistance: 하루에 이동 가능한 거리 / 2
     * minDays: 이웃집 모두에 떡을 돌리기 위한 최소 일
     */
    static int N, M, X, Y, oneWayDistance, minDays;
    /**
     * adjList: 인접 리스트
     */
    static ArrayList<Vertex>[] adjList;
    /**
     * dis: 출발지로부터 각 도착지의 거리
     */
    static int[] dist;
    /**
     * visited: 방문 체크
     */
    static boolean[] visited;
    static final int MAX = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        oneWayDistance = X / 2;

        adjList = new ArrayList[N];
        for(int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adjList[a].add(new Vertex(b, c));
            adjList[b].add(new Vertex(a, c));
        }

        dijkstra();

        solution();

        System.out.println(minDays);
    }

    public static void dijkstra() {
        visited = new boolean[N];
        dist = new int[N];
        for(int i = 0; i < N; i++) {
            dist[i] = MAX;
        }

        dist[Y] = 0;

        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        priorityQueue.offer(new Vertex(Y, 0));

        while(!priorityQueue.isEmpty()) {
            int nowVertex = priorityQueue.poll().idx;

            if(visited[nowVertex]) {
                continue;
            }
            visited[nowVertex] = true;

            for(Vertex next: adjList[nowVertex]) {
                if(dist[next.idx] > dist[nowVertex] + next.distance) {
                    dist[next.idx] = dist[nowVertex] + next.distance;

                    priorityQueue.offer(new Vertex(next.idx, dist[next.idx]));
                }
            }
        }
    }

    public static void solution() {
        Arrays.sort(dist);

        if(dist[N - 1] > oneWayDistance) {
            minDays = -1;
            return;
        }

        int idx = 1; // 정렬 후의 배열에서 0번 인덱스는 출발점 ~ 출발점.
        while(idx < N) {
            int nowDist = 0;
            while(idx < N && nowDist + dist[idx] <= oneWayDistance) {
                nowDist += dist[idx++];
            }
            minDays++;
        }
    }
}
