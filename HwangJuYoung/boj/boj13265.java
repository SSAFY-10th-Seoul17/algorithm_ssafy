package BOJ;

import java.util.*;
import java.io.*;

public class boj13265 {
    private static boolean[] visited;
    private static ArrayList<ArrayList<Integer>> graph;
    private static boolean flag = true;
    private static int start = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        test:
        for (int testCase = 0; testCase < T; testCase++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");

            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            visited = new boolean[n+1];

            for (int i = 0; i < n+1; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine(), " ");

                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                graph.get(x).add(y);
                graph.get(y).add(x);
            }

            for (int i = 1; i < n+1; i++) {
                if (!visited[i]) {
                    start = i;
                    dfs(i, 0);
                    if (!flag) {
                        System.out.println("impossible");
                        flag = true;
                        continue test;
                    }
                }
            }
            System.out.println("possible");
        }
    } // end of main

    private static void dfs(int n, int cnt) {
        visited[n] = true;
        ArrayList<Integer> tmp = graph.get(n);
        for (int i = 0; i < tmp.size(); i++) {
            if (!visited[tmp.get(i)]) {
                dfs(tmp.get(i), cnt+1);
            } else {
                if (tmp.get(i) == start) {
                    if (cnt % 2 == 0) {
                        flag = false;
                        return;
                    }
                }
            }
        }
    }
} // end of class