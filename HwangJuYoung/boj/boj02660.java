package BOJ;

import java.io.*;
import java.util.*;

public class boj02660 {
    private static int N;
    private static ArrayList<ArrayList<Integer>> graph;
    private static boolean[] visited;
    private static int[] score;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        score = new int[N+1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if (A == -1 && B == -1) {
                break;
            }

            graph.get(A).add(B);
            graph.get(B).add(A);
        }

        for (int i = 1; i < N+1; i++) {
            visited = new boolean[N+1];
            BFS(i);
        }

        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        int minScore = Integer.MAX_VALUE;
        ArrayList<Integer> chairMan = new ArrayList<>();;
        for (int i = 1; i < N+1; i++) {
            if (minScore > score[i]) {
                minScore = score[i];
                cnt = 1;
                chairMan.clear();
                chairMan.add(i);
            } else if (minScore == score[i]) {
                cnt++;
                chairMan.add(i);
            }
        }

        sb.append(minScore).append(" ").append(cnt).append("\n");
        for (int i = 0; i < chairMan.size(); i++) {
            sb.append(chairMan.get(i)).append(" ");
        }
        System.out.println(sb.toString());
    } // end of main

    private static void BFS(int i) {
        Queue<Integer> queue = new ArrayDeque<>();

        queue.add(i);
        visited[i] = true;

        int cnt = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int j = 0; j < size; j++) {
                int v = queue.poll();

                ArrayList<Integer> tmp = graph.get(v);
                for (Integer integer : tmp) {
                    if (!visited[integer]) {
                        queue.add(integer);
                        visited[integer] = true;
                    }
                }

            }
            boolean flag = false;
            for (int k = 1; k < N+1; k++) {
                if (!visited[k]) {
                    flag = true;
                    break;
                }
            }

            cnt++;
            if (!flag) {
                score[i] = cnt;
                return;
            }
        }
    }
} // end of class