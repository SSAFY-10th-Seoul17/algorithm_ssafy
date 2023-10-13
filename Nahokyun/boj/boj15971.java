package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
두 로봇
 */
public class boj15971 {
    static int MAX = 0;
    static boolean[] visited;
    private static Node[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int robot1 = Integer.parseInt(st.nextToken());
        int robot2 = Integer.parseInt(st.nextToken());

        visited = new boolean[n + 1];
        graph = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new Node(null, 0, i);
        }


        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());

            graph[first].next = new Node(graph[first].next, len, second);
            graph[second].next = new Node(graph[second].next, len, first);
        }
        //입력 종료

        dfs(robot1, 0, 0, robot2);

        System.out.println(MAX);

    }

    private static void dfs(int curNodeNum, int sum, int max, int destination) {
        visited[curNodeNum] = true;

        if (curNodeNum == destination) {
            MAX = sum - max;
            return;
        }

        for (Node cur = graph[curNodeNum].next; cur != null; cur = cur.next) {
            if (visited[cur.nodeNum]) {
                continue;
            }

            dfs(cur.nodeNum, sum + cur.len, Math.max(cur.len, max), destination);

        }

    }

    static class Node {
        Node next;
        int nodeNum;
        int len;

        public Node(Node next, int len, int nodeNum) {
            this.next = next;
            this.len = len;
            this.nodeNum = nodeNum;
        }
    }

}
