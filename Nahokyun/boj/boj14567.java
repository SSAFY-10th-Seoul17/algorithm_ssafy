package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj14567 {
    static int[] havingnext;
    static boolean[] flag;
    static boolean[] haveTofind;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Node[] subjects = new Node[n + 1];
        havingnext = new int[n + 1];
        haveTofind = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            subjects[i] = new Node(null, i);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            haveTofind[second] = true;
            subjects[first].next = new Node(subjects[first].next, second);
        }

        for (int i = 1; i <= n; i++) {
            flag = new boolean[n + 1];
            if (!haveTofind[i])
                find(i, 0, subjects);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            sb.append(havingnext[i] + 1).append(' ');
        }
        System.out.println(sb.toString());

    }//end of main

    private static void find(int cur, int curDepth, Node[] subjects) {
        if (havingnext[cur] < curDepth) {
            havingnext[cur] = curDepth;
        }

        flag[cur] = true;

        for (Node next = subjects[cur].next; next != null; next = next.next) {
            if (!flag[next.nodeNum] || havingnext[next.nodeNum] < curDepth + 1)
                find(next.nodeNum, curDepth + 1, subjects);
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

