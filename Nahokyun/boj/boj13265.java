package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
색칠하기
 */
public class boj13265 {
    private static Node[] al;
    private static int n;
    private static int m;
    private static int[] colors;
    private static boolean[] visited;
    private static boolean result;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int test = 0; test < t; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            al = new Node[n + 1];

            visited = new boolean[n + 1];
            result = true;

            for (int i = 1; i <= n; i++) {
                al[i] = new Node(i, null);
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                al[a].next = new Node(b, al[a].next);
                al[b].next = new Node(a, al[b].next);

            } // 입력 완료

            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    colors = new int[n + 1];
                    colors[i] = 1;
                    find(i, false);
                }
            }

            sb.append(result ? "possible" : "impossible").append('\n');
        }
        System.out.println(sb.toString());
    }

    private static void find(int cur, boolean colorBit) {
        visited[cur] = true;
        int curColor;
        if (colorBit) {
            curColor = 1;
        } else
            curColor = 2;

        for (Node next = al[cur].next; next != null; next = next.next) {
            if (colors[next.nodeNum] == 0) {// 처음 방문했을때
                colors[next.nodeNum] = curColor;
                find(next.nodeNum, !colorBit);
            } else {
                if (colors[next.nodeNum] != curColor) {
                    result = false;// 현재의 색이 다음에 올색과 다
                    return;
                } else {
                    continue;
                }

            }
        }

    }

    static class Node {// 초기색깔 0, 탐색하면서 1,2 부여
        int nodeNum;
        Node next;

        public Node(int nodeNum, Node next) {
            this.nodeNum = nodeNum;
            this.next = next;
        }

    }
}
