package boj;

/*
* 간선 이어가기 2, 다익스트라 알고리즘을 복기해보자!
* floyd -> 시간 초과 : O(n^3)이라 그런듯..
* */

import java.io.*;
import java.util.*;

public class boj14284 {

    static int[][] graph;
    static int[] distance;
    static int N; // cnt of vertex
    static int M; // cnt of edge

    static int ansFrom; // target vertex ans
    static int ansTo; // target vertex ans

    static final int INF = Integer.MAX_VALUE; // 연결되지 않았을 때의 가중치 지정

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N+1][N+1]; // adjacent list, storing connection of vertex to vertex.
        distance = new int[N+1];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from][to] = weight;
            graph[to][from] = weight;
        }

        st = new StringTokenizer(br.readLine());

        ansFrom = Integer.parseInt(st.nextToken());
        ansTo = Integer.parseInt(st.nextToken());

        // initiate graph and distance process
        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(i == ansFrom){
                    if(graph[i][j] == 0){
                        graph[i][j] = INF;
                        distance[j] = INF;
                    }
                    else {
                        distance[j] = graph[i][j];
                    }
                }

                if(graph[i][j] == 0) graph[i][j] = INF;
            }
        }
        //floyd();
        distance[ansFrom] = 0;
        dijkstra(ansFrom);
        System.out.println(distance[ansTo]);

    } // end of main

    private static void dijkstra(int start){
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        while(!q.isEmpty()){
            int v = q.poll();
            for(int i = 1; i <= N; i++){
                if(i == v) continue;
                if(graph[v][i] != INF){ // 해당 정점과 연결된 것일때만 체크
                    if(distance[i] >= graph[v][i] + distance[v]) {
                        distance[i] = graph[v][i] + distance[v];
                    } else continue;
                    q.offer(i);
                }
            }
        }

    }

    private static void floyd(){
        for(int k = 1; k <= N; k++){
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    if((graph[i][k] != INF && graph[k][j] != INF) && graph[i][j] > graph[i][k] + graph[k][j]){
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
    }
} // end of class
