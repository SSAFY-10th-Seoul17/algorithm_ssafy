import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * [boj] 16947. 서울 지하철 2호선
 */
public class boj16947 {
    /**
     * N 역의 수
     */
    static int N;
    /**
     * roadList 역별 노선 연결 리스트 배열
     */
    static ArrayList<Integer>[] edgeList;
    /**
     * isCycle 역이 순환선에 속하는지 여부를 나타내는 배열
     */
    static boolean[] isCycle;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        edgeList = new ArrayList[N + 1]; // 역 번호는 1번부터 시작.
        for(int i = 1; i <= N; i++) {
            edgeList[i] = new ArrayList<Integer>();
        }
        for(int i = 1; i <= N; i++) { // edgeList 정보 넣기
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int station1 = Integer.parseInt(st.nextToken());
            int station2 = Integer.parseInt(st.nextToken());
            edgeList[station1].add(station2);
            edgeList[station2].add(station1);
        }

        for(int i = 1; i <= N; i++) { // 순환선 여부 체크
            isCycle = new boolean[N + 1];
            if(cycleCheck(i, i, i)) { // 순환 노선 발생
                break;
            }
        }

        for(int i = 1; i <= N; i++) {
            sb.append(isCycle[i] ? 0 : bfs(i));
            if(i != N) {
                sb.append(" ");
            }
        }
        sb.append("\n");

        System.out.print(sb.toString());
    }

    /**
     * 순환 노드(역)인지 확인.
     * 1. 현재 노드에 해당하는 isCycle 배열의 값을 true로 설정.
     * 2. 현재 노드와 연결된 노드를 탐색.
     * 3. 연결된 노드 중에 아직 순환 여부 체크하지 않은 노드가 있다면 해당 노드로 탐색 진행.
     * 4. 노드가 바로 이전에 방문한 노드가 아니고(양방향 연결이기 때문) 연결된 노드가 시작 노드 -> 처음 노드 재방문한 것 -> 순환 발생.
     * @param startNode 시작 노드(역)
     * @param prevNode 이전 노드
     * @param nowNode 현재 노드
     */
    public static boolean cycleCheck(int startNode, int prevNode, int nowNode) {
        isCycle[nowNode] = true;

        ArrayList<Integer> list = edgeList[nowNode];
        int size = list.size();
        for(int i = 0; i < size; i++) {
            int nextNode = list.get(i);

            if(!isCycle[nextNode] && cycleCheck(startNode, nowNode, nextNode)) {
                return true;
            } else if(nextNode != prevNode && nextNode == startNode) {
                return true;
            }
        }
        isCycle[nowNode] = false;
        return false;
    }

    /**
     * 순환 발생 노드까지의 거리 확인 bfs
     * @param node bfs 시작 노드(역)
     */
    public static int bfs(int node) {
        boolean[] visited = new boolean[N + 1];
        visited[node] = true;

        Queue<Integer> nodeQueue = new LinkedList<>();
        Queue<Integer> distanceQueue = new LinkedList<>();
        nodeQueue.offer(node);
        distanceQueue.offer(0);

        while(!nodeQueue.isEmpty()) {
            int nowNode = nodeQueue.poll();
            int nowDistance = distanceQueue.poll();

            if(isCycle[nowNode]) {
                return nowDistance;
            }

            ArrayList<Integer> list = edgeList[nowNode];
            int size = list.size();
            for(int i = 0; i < size; i++) {
                int nextNode = list.get(i);

                if(!visited[nextNode]) {
                    visited[nextNode] = true;
                    nodeQueue.add(nextNode);
                    distanceQueue.add(nowDistance + 1);
                }
            }
        }
        return 0;
    }
}
