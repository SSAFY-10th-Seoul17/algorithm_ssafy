package BOJ;

import java.io.*;
import java.util.*;

public class boj14567 {
    private static List<List<Integer>> graph;
    private static int[] inDegree, minSemester;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        visited = new boolean[N+1];
        inDegree = new int[N + 1];
        minSemester = new int[N + 1];
        Arrays.fill(minSemester, 1);

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph.get(A).add(B);
            inDegree[B]++;
        }

        BFS();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < minSemester.length; i++) {
            sb.append(minSemester[i]).append(" ");
        }

        System.out.println(sb.toString());
    } // end of main

    private static void BFS() {
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int v = queue.poll();
            visited[v] = true;

            List<Integer> tmp = graph.get(v);
            for (int i = 0; i < tmp.size(); i++) {
                if (--inDegree[tmp.get(i)] == 0) {
                    queue.add(tmp.get(i));
                }
                minSemester[tmp.get(i)] = Math.max(minSemester[tmp.get(i)], minSemester[v] + 1);
            }
        }
    }
} // end of class