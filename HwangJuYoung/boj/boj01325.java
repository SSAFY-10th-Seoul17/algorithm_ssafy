import java.io.*;
import java.util.*;

public class Main {
    static List<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] visited;
    static int maxInfection = 0;
    static int[] maxInfectionIdx;
    static StringBuilder sBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(stringTokenizer.nextToken());
        int M = Integer.parseInt(stringTokenizer.nextToken());

        maxInfectionIdx = new int[N+1];

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < M; i++) {
            stringTokenizer = new StringTokenizer(br.readLine());

            int B = Integer.parseInt(stringTokenizer.nextToken());
            int A = Integer.parseInt(stringTokenizer.nextToken());

            graph.get(A).add(B);
        }


        for (int i = 1; i <= N; i++) {
            visited = new int[N+1];
            BFS(i);

        }

        for (int i = 1; i < maxInfectionIdx.length; i++) {
            if (maxInfectionIdx[i] == maxInfection) {
                sBuilder.append(i).append(" ");
            }
        }
        sBuilder.append("\n");
        System.out.println(sBuilder.toString());

    } // end of main

    private static void BFS(int index) {
        Queue<Integer> queue = new LinkedList<>();
        int cnt = 0;
        visited[index] = 1;
        queue.add(index);


        while (queue.size() > 0) {
            int v = queue.poll();


            for (int i = 0, index2 = graph.get(v).size(); i < index2; i++) {
                ArrayList<Integer> tmp = graph.get(v);
                if (visited[tmp.get(i)] == 0) {
                    cnt++;
                    visited[tmp.get(i)] = 1;
                    queue.add(tmp.get(i));
                }
            }
        }
        maxInfectionIdx[index] = cnt;

        maxInfection = Math.max(maxInfection, cnt);
    } // end of BFS

} // end of class