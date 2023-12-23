import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 1068. 트리
 */
public class boj1068 {
    /**
     * N: 트리의 노드 수, rootNode: 루트 노드의 번호, removeNode: 지울 노드의 번호, leafNodeCount: 리프 노드의 수
     */
    static int N, rootNode, removeNode, leafNodeCount;
    /**
     * parents: 각 노드의 부모 노드
     */
    static int[] parents;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        parents = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            parents[i] = Integer.parseInt(st.nextToken());

            if(parents[i] == -1) {
                rootNode = i;
            }
        }

        removeNode = Integer.parseInt(br.readLine());

        remove(removeNode);
        visited = new boolean[N];
        countLeafNode(rootNode);

        System.out.println(leafNodeCount);
    }


    public static void remove(int removeNode) {
        parents[removeNode] = -2; // 제거한 노드의 부모 노드를 사용할 수 없는 수인 -2로 변경.

        for(int i = 0; i < N; i++) {
            if(parents[i] == removeNode) {
                remove(i);
            }
        }
    }

    public static void countLeafNode(int node) {
        visited[node] = true;
        boolean isLeafNode = true;

        if(parents[node] != -2) {
            for(int i = 0; i < N; i++) {
                if(parents[i] != node || visited[i]) {
                    continue;
                }
                countLeafNode(i);
                isLeafNode = false;
            }

            if(isLeafNode) {
                leafNodeCount++;
            }
        }
    }
}
