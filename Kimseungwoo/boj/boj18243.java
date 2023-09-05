package toyproject.somedaybucket.myAlgo.boj;

import java.io.*;
import java.util.*;

public class boj18243 {

    static int N;
    static int K;
    static int[][] graph;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new int[N+1][N+1];

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from][to] = 1;
            graph[to][from] = 1;
        }


        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(graph[i][j] == 0) {
                    graph[i][j] = INF;
                }
            }
        }

        floyd();

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(graph[i][j] > 6) {
                    System.out.println("Big World!");
                    return;
                }
            }
        }

        System.out.println("Small World!");
    }

    private static void floyd() {
        for(int k = 1; k <= N; k++) {
            for(int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    if(graph[i][k] != INF && graph[k][j] != INF && graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k]+graph[k][j];
                    }
                }
            }
        }
    }
}

