import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj14699 {
    static class Node {
        int number;
        Node next;

        public Node(int number, Node next) {
            this.number = number;
            this.next = next;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] heights = new int[n + 1];
        Node[] graph = new Node[n + 1];
        
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= n; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
            graph[i] = new Node(i, null);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (heights[a] < heights[b]) {
                graph[a].next = new Node(b, graph[a].next);
            } else {
                graph[b].next = new Node(a, graph[b].next);
            }
        }

        System.out.print(maxPathCounts(graph));
    }

    private static String maxPathCounts(Node[] graph) {
        int[] dp = new int[graph.length];

        for (int i = 1; i < graph.length; i++) {
            if (dp[i] == 0) {
                dp[i] = dfs(graph, dp, i);
            }
        }
        
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < graph.length; i++) {
            result.append(dp[i]).append('\n');
        }
        return result.toString();
    }

    private static int dfs(Node[] graph, int[] dp, int node) {
        if (dp[node] > 0) {
            return dp[node];
        }
        
        int count = 0;
        for (Node next = graph[node].next; next != null; next = next.next) {
            count = Math.max(count, dfs(graph, dp, next.number));
        }

        return dp[node] = count + 1;
    }

}
