package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj17616 {
    public static int low;
    public static int high;
    public static boolean[] flag;
    private static Node[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        graph = new Node[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new Node(null, null, i);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].lower = new Node(null, graph[a].lower, b);
            graph[b].upper = new Node(graph[b].upper, null, a);
        }
        // 입력 종료

        flag = new boolean[n + 1];
        lowDfs(x);
        Arrays.fill(flag, false);
        highDfs(x);

        System.out.println((high + 1) + " " + (n - low));
    }

    private static void lowDfs(int cur) {
        flag[cur] = true;

        for (Node next = graph[cur].lower; next != null; next = next.lower) {
            if (!flag[next.nodeNum]) {
                flag[next.nodeNum] = true;
                low++;
                lowDfs(next.nodeNum);
            }
        }
    }

    private static void highDfs(int cur) {
        flag[cur] = true;

        for (Node next = graph[cur].upper; next != null; next = next.upper) {
            if (!flag[next.nodeNum]) {
                flag[next.nodeNum] = true;
                high++;
                highDfs(next.nodeNum);
            }
        }
    }

    static class Node {
        Node upper;
        Node lower;
        int nodeNum;

        public Node(Node upper, Node lower, int nodeNum) {
            this.upper = upper;
            this.lower = lower;
            this.nodeNum = nodeNum;
        }
    }
}
