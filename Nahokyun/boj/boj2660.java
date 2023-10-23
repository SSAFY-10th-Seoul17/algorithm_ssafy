package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj2660 {

    private static Node[] relation;
    private static int n;

    public static void main(String[] args) throws NumberFormatException, IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        relation = new Node[n + 1];

        for (int i = 1; i <= n; i++) {
            relation[i] = new Node(null, i);
        }

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            if (first == -1 && second == -1) {
                break;
            }

            relation[first].next = new Node(relation[first].next, second);
            relation[second].next = new Node(relation[second].next, first);
        }
        // 입력 종료

        int[] score = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            score[i] = bfs(i);
        }

        ArrayList<Integer> al = new ArrayList<>();
        int min = n+2;
        for (int i = 1; i <= n; i++) {
            if (score[i] < min) {
                al.clear();
                al.add(i);
                min = score[i];
            } else if (score[i] == min) {
                al.add(i);
            }
        }

        Collections.sort(al);
        StringBuilder sb = new StringBuilder();
        sb.append(min-1).append(" ").append(al.size()).append("\n");
        for (int i : al) {
            sb.append(i).append(" ");
        }

        System.out.println(sb.toString());
    }

    private static int bfs(int i) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(i);
        boolean[] visited = new boolean[n + 1];
        visited[i] = true;
        int depth = 0;
        while (!q.isEmpty()) {
            ArrayDeque<Integer> tmp = new ArrayDeque<>();

            while (!q.isEmpty()) {
                int cur = q.poll();

                for (Node next = relation[cur].next; next != null; next = next.next) {
                    if (!visited[next.num]) {
                        tmp.add(next.num);
                        visited[next.num] = true;
                    }
                }

            }
            depth++;
            q = tmp;
        }

        return depth;
    }

    static class Node {
        Node next;
        int num;

        public Node(Node next, int num) {
            super();
            this.next = next;
            this.num = num;
        }
    }
}
