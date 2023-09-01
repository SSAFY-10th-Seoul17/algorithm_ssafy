package boj;

//비밀 모임 -> implements by Floyd-warshall algorithm!

import java.io.*;
import java.util.*;

public class boj13424 {
 static int[][] graph;
 static int N; // cnt of vertex
 static int M; // cnt of edge

 static int fNum;

 static int[][] friendsList;
 static ArrayList<Integer> friendsIdxList;
 static final int INF = Integer.MAX_VALUE;

 public static void main(String[] args) throws IOException {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     int TC = Integer.parseInt(br.readLine());
     StringBuilder sb = new StringBuilder();

     for(int t = 1; t <= TC; t++){
         // v, e input
         StringTokenizer st = new StringTokenizer(br.readLine(), " ");
         N = Integer.parseInt(st.nextToken());
         M = Integer.parseInt(st.nextToken());

         graph = new int[N+1][N+1];

         for(int i = 1; i <= N; i++){
             for(int j = 1; j <= N; j++){
                 if(i == j) {
                     graph[i][j] = 0;
                     continue;
                 }
                 graph[i][j] = INF;
             }
         }

         for(int i = 0; i < M; i++){
             st = new StringTokenizer(br.readLine(), " ");
             int from = Integer.parseInt(st.nextToken());
             int to = Integer.parseInt(st.nextToken());
             int weight = Integer.parseInt(st.nextToken());

             graph[from][to] = weight;
             graph[to][from] = weight;
         }

         fNum = Integer.parseInt(br.readLine());
         friendsList = new int[fNum][];
         friendsIdxList = new ArrayList<>();
         st = new StringTokenizer(br.readLine(), " ");
         while(st.hasMoreTokens()){
             friendsIdxList.add(Integer.parseInt(st.nextToken()));
         }

         floyd();

         for(int i = 0; i < fNum; i++){
             friendsList[i] = Arrays.copyOf(graph[friendsIdxList.get(i)], N+1);
         }
         int minDistanceRoom = -1;
         int min = Integer.MAX_VALUE;

         for(int i = 1; i <= N; i++){
             int sum = 0;
             for(int j = 0; j < fNum; j++){
                 sum += friendsList[j][i];
             }
             if(sum < min){
                 min = sum;
                 minDistanceRoom = i;
             }
         }

         sb.append(minDistanceRoom).append("\n");
     }

     System.out.println(sb);
 } // end of main

 private static void floyd(){
     for(int k = 1; k <= N; k++){
         for(int i = 1; i <= N; i++){
             for(int j = 1; j <= N; j++){
                 if(graph[i][k] != INF && graph[k][j] != INF && graph[i][j] > graph[i][k] + graph[k][j]){
                     graph[i][j] = graph[i][k] + graph[k][j];
                 }
             }
         }
     }
 }
} // end of class
