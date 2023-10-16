package BOJ;

import java.util.*;
import java.io.*;

public class boj12761 {
    private static int A, B, N, M;
    private static int[] memo;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        memo = new int[100001];
        visited = new boolean[100001];

        BFS();

    } // end of main

    private static void BFS() {
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(N);
        visited[N] = true;

        int cnt = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int q = 0; q < size; q++) {
                int v = queue.poll();

                if (v + 1 == M || v - 1 == M || v + A == M || v - A == M ||
                        v + B == M || v - B == M || v * A == M || v * B == M) {
                    System.out.println(cnt+1);
                    return;
                }

                if (v + 1 <= 100000 && !visited[v + 1]) {
                    queue.add(v + 1);
                    visited[v + 1] = true;
                }

                if (v - 1 >= 0 && !visited[v - 1]) {
                    queue.add(v - 1);
                    visited[v - 1] = true;
                }

                if (v + A <= 100000 && !visited[v + A]) {
                    queue.add(v + A);
                    visited[v + A] = true;
                }

                if (v - A >= 0 && !visited[v - A]) {
                    queue.add(v - A);
                    visited[v - A] = true;
                }

                if (v - B >= 0 && !visited[v - B]) {
                    queue.add(v - B);
                    visited[v - B] = true;
                }

                if (v + B <= 100000 && !visited[v + B]) {
                    queue.add(v + B);
                    visited[v + B] = true;
                }

                if (v * A <= 100000 && !visited[v * A]) {
                    queue.add(v * A);
                    visited[v * A] = true;
                }

                if (v * B <= 100000 && !visited[v * B]) {
                    queue.add(v * B);
                    visited[v * B] = true;
                }

            }
            cnt++;
        }

    }
} // end of class