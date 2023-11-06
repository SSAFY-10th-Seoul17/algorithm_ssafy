package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj22865 {

    private static Node[] nodes;

    static class Node implements Comparable<Node> {
        Node next;
        int num;
        int dist;

        public Node(Node next, int num, int dist) {
            super();
            this.next = next;
            this.num = num;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {

            return this.dist - o.dist;
        }

    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(null, i, 0);
        }
        int[] group = new int[3];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            group[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            nodes[first].next = new Node(nodes[first].next, second, dist);
            nodes[second].next = new Node(nodes[second].next, first, dist);

        }
        // 입력 종료

        int[][] result = new int[3][n + 1];

        for (int i = 0; i < 3; i++) {
            Arrays.fill(result[i], Integer.MAX_VALUE);
            dijk(group[i], result[i]);
        }

        int max = 0;
        int maxIdx = 0;
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < 3; j++) {
                min = Math.min(min, result[j][i]);
            }
            if (max < min) {
                max = Math.max(min, max);
                maxIdx = i;
            }
        }
        System.out.println(maxIdx);

    }

    private static void dijk(int start, int[] result) {
        Queue<Node> pq = new PriorityQueue<Node>();

        pq.add(nodes[start]);
        result[nodes[start].num] = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (Node next = cur.next; next != null; next = next.next) {
                if (result[next.num] > result[cur.num] + next.dist) {
                    result[next.num] = result[cur.num] + next.dist;
                    pq.add(new Node(nodes[next.num].next, next.num, result[next.num]));
                }
            }
        }

    }

}

