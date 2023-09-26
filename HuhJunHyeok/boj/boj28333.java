import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * [boj] 28333. 화이트 칼라
 */
public class boj28333 {
    /**
     * T: 테스트 케이스의 수, N: 도시의 수, M: 도시 간에 연결된 길의 수
     * 2 <= N <= 1000
     * 1 <= M <= 50000
     */
    static int T, N, M;
    /**
     * distance: 1번 도시로부터 각 도시의 거리
     */
    static int[] distance;
    /**
     * visited: 역추적에서 사용할 방문 체크 배열
     */
    static boolean[] visited;
    /**
     * adjList: 정방향 도시 간 연결된 길
     * reverseAdjList: 역방향 도시 간 연결된 길
     */
    static ArrayList<Integer>[] adjList, reverseAdjList;
    /**
     * batchCity: 직원을 배치해야 되는 도시들
     */
    static ArrayList<Integer> batchCity;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            adjList = new ArrayList[M + 1];
            reverseAdjList = new ArrayList[M + 1];
            for(int i = 1; i <= M; i++) {
                adjList[i] = new ArrayList<>();
                reverseAdjList[i] = new ArrayList<>();
            }
            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                adjList[from].add(to); // 방향 그래프
                reverseAdjList[to].add(from);
            }

            bfs();
            backtrack();

            Collections.sort(batchCity);

            for(int city: batchCity) {
                sb.append(city).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    public static void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1); // 1번 도시가 시작 도시

        distance = new int[N + 1];
        for(int i = 2; i <= N; i++) {
            distance[i] = 1001;
        }

        while(!queue.isEmpty()) {
            int now = queue.poll();

            for(int next: adjList[now]) {
                if(distance[next] > distance[now] + 1) { // 갱신할 수 있는 경우
                    distance[next] = distance[now] + 1;
                    queue.add(next);
                }
            }
        }
    }

    public static void backtrack() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(N); // N번 도시에서 역추적

        visited = new boolean[N + 1];
        visited[N] = true;

        batchCity = new ArrayList<>();
        batchCity.add(N);

        int compareDistance = distance[N];
        while(!queue.isEmpty()) {
            compareDistance--;

            Queue<Integer> availableQueue = new ArrayDeque<>();
            while(!queue.isEmpty()) {
                int now = queue.poll();

                for(int next: reverseAdjList[now]) {
                    if(distance[next] == compareDistance && !visited[next]) {
                        batchCity.add(next);
                        availableQueue.offer(next);
                    }
                    visited[next] = true;
                }
            }

            queue = availableQueue;
        }
    }
}
