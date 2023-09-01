package BOJ;

import java.io.*;
import java.util.*;

public class boj05567 {
    private static int n, m, cnt = 0;
    private static ArrayList<ArrayList<Integer>> graph;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        visited = new boolean[n+1];
        graph = new ArrayList<>(7);

        for (int i = 0; i < n+1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        BFS(1);

        System.out.println(cnt);
    } // end of main

    private static void BFS(int N) {
        Queue<int[]> queue = new ArrayDeque<>();

        visited[N] = true;
        queue.add(new int[] {N, 0});

        while (!queue.isEmpty()) {
            int[] vArr = queue.poll();
            int v = vArr[0];

            for (int i = 0; i < graph.get(v).size(); i++) {
                if (!visited[graph.get(v).get(i)] && vArr[1] < 2) {
                    cnt++;
                    visited[graph.get(v).get(i)] = true;
                    queue.add(new int[] {graph.get(v).get(i), vArr[1] + 1});
                }
            }
        }

    }

} // end of class