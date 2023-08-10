import java.io.*;
import java.util.*;

public class Main {
    private static int F;
    private static int S;
    private static int G;
    private static int U;
    private static int D;
    private static boolean[] visited;
    private static int minCnt = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bReader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sBuilder = new StringBuilder();
        StringTokenizer stringTokenizer = new StringTokenizer(bReader.readLine(), " ");
        F = Integer.parseInt(stringTokenizer.nextToken());
        S = Integer.parseInt(stringTokenizer.nextToken());
        G = Integer.parseInt(stringTokenizer.nextToken());
        U = Integer.parseInt(stringTokenizer.nextToken());
        D = Integer.parseInt(stringTokenizer.nextToken());

        String error = "use the stairs";

        visited = new boolean[F+1];

        BFS(S);

        if (S == G) {
            sBuilder.append(0);
        } else if (minCnt == Integer.MAX_VALUE) {
            sBuilder.append(error);
        } else {
            sBuilder.append(minCnt);
        }

        System.out.println(sBuilder.toString());
    } // end of main

    private static void BFS(int stair) {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[]{stair, 0});
        visited[stair] = true;

        while (!queue.isEmpty()) {
            int[] v = queue.poll();
            if (v[0] + U == G || v[0] - D == G) {
                minCnt = Math.min(minCnt, ++v[1]);
                return;
            }
            if (v[0] + U <= F && !visited[v[0]+U]) {
                queue.add(new int[] {v[0]+U, v[1]+1});
                visited[v[0]+U] = true;
            }
            if (v[0] - D >= 1 && !visited[v[0]-D]) {
                queue.add(new int[] {v[0]-D, v[1]+1});
                visited[v[0]-D] = true;
            }
        }
    } // end of BFS
} // end of class