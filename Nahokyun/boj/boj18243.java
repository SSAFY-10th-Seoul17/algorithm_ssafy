package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj18243 {

    private static Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        nodes = new Node[n + 1];

        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(null, i);
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            nodes[a].next = new Node(nodes[a].next, b);
            nodes[b].next = new Node(nodes[b].next, a);
        }//입력 종료

        boolean[] flag;
        int[] dist;
        for (int i = 1; i <= n; i++) {
            flag = new boolean[n + 1];
            dist = new int[n + 1];
            Arrays.fill(dist, 1000);
            dist[i] = 0;
            dfs(i, dist, 0, flag);

            for (int j = 1; j <= n; j++) {
                if (dist[j] >= 7) {
                    System.out.println("Big World!");
                    return;
                }
            }
        }
        System.out.println("Small World!");


    }//end of main

    private static void dfs(int start, int[] dist, int cur, boolean[] flag) {
        if (cur > 7) {
            return;
        }

        if (dist[start] > cur) {
            dist[start] = cur;
        }
        flag[start] = true;

        for (Node next = nodes[start].next; next != null; next = next.next) {

            if (!flag[next.nodeNum] || dist[next.nodeNum] > cur + 1) {
                dfs(next.nodeNum, dist, cur + 1, flag);
            }
        }
    }


    static class Node {
        Node next;
        int nodeNum;

        public Node(Node next, int nodeNum) {
            this.next = next;
            this.nodeNum = nodeNum;
        }

    }

}

