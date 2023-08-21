import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 트리의 지름
public class Boj01167 {

    static class Node {
        int root;
        int distance;
        Node next;

        public Node(int root, int distance, Node next) {
            this.root = root;
            this.distance = distance;
            this.next = next;
        }
    }

    static int result;
    static Node[] graphs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        graphs = new Node[v + 1];
        for (int i = 1; i <= v; i++) {
            graphs[i] = new Node(i, 0, null);
        }
        visited = new boolean[v + 1];

        for (int i = 0; i < v; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d;
            while ((d = Integer.parseInt(st.nextToken())) != -1) {
                int w = Integer.parseInt(st.nextToken());
                graphs[s].next = new Node(d, w, graphs[s].next);
            }
        }

        System.out.println(Math.max(dfs(1, 0), result));
    }

    static boolean[] visited;

    private static int dfs(int root, int w) {
        visited[root] = true;

        int maxChild = 0;
        int secondMaxChild = 0;

        for (Node next = graphs[root].next; next != null; next = next.next) {
            if (visited[next.root]) {
                continue;
            }

            int childWeight = dfs(next.root, next.distance);
            if (childWeight > maxChild) {
                secondMaxChild = maxChild;
                maxChild = childWeight;
            } else {
                secondMaxChild = Math.max(secondMaxChild, childWeight);
            }
        }

        result = Math.max(result, maxChild + secondMaxChild); // 연결된 노드 중 가장 긴 노드 2개의 합
        return maxChild + w; // 연결된 노드 중 가장 긴 노드 반환
    }

}

