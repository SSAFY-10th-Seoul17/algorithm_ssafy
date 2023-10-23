import java.io.*;
import java.util.*;

public class Boj13265 {
    static class Node {
        int root;
        Node next;

        public Node(int number, Node next) {
            this.root = number;
            this.next = next;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder result = new StringBuilder();

        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            Node[] nodes = new Node[n + 1];
            for (int i = 1; i <= n; i++) {
                nodes[i] = new Node(i, null);
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                nodes[a].next = new Node(b, nodes[a].next);
                nodes[b].next = new Node(a, nodes[b].next);
            }

            result.append(isPossible(nodes) ? "possible" : "impossible").append("\n");
        }

        System.out.println(result);
    }

    private static boolean isPossible(Node[] nodes) {
        int[] colors = new int[nodes.length];

        for (int i = 1; i < nodes.length; i++) {
            if (colors[i] != 0) {
                continue;
            }

            if (isCycle(nodes, colors, i, 1)) {
                return false;
            }
        }

        return true;
    }

    private static boolean isCycle(Node[] nodes, int[] colors, int node,int color) {
        colors[node] = color;

        for (Node next = nodes[node].next; next != null; next = next.next) {
            if (colors[next.root] == -color) {
                continue;
            }

            if (colors[next.root] == color || isCycle(nodes, colors, next.root, -color)) {
                return true;
            }
        }

        return false;
    }


}

