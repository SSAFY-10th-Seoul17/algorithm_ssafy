package BOJ;

import java.io.*;
import java.util.*;

public class boj18243 {
    private static List<List<Integer>> graph;
    private static boolean flag = true;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.get(A).add(B);
            graph.get(B).add(A);
        }

        for (int i = 1; i < N + 1; i++) {
            visited = new boolean[N + 1];
            BFS(i);
            for (int j = 1; j < N+1; j++) {
                if (!visited[j]) {
                    flag = false;
                    break;
                }
            }
            if (!flag) {
                break;
            }
        }

        if (flag) {
            System.out.println("Small World!");
        } else {
            System.out.println("Big World!");
        }
    } // end of main

    private static void BFS(int V) {
        Queue<int[]> queue = new ArrayDeque<>();

        queue.add(new int[]{V, 0});

        while (!queue.isEmpty()) {
            int[] vArr = queue.poll();
            int v = vArr[0];
            if (vArr[1] > 6) {
                flag = false;
            }
            visited[v] = true;

            for (Integer adjV : graph.get(v)) {
                if (!visited[adjV]) {
                    queue.add(new int[]{adjV, vArr[1]+1});
                }
            }
        }
    }
} // end of class