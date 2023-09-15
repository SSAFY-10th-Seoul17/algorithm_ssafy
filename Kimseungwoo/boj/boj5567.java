package boj;

import java.io.*;
import java.util.*;

public class boj5567 {

    static int N; // 동기의 수
    static int M; // 리스트의 길이

    static int[][] friendsGraph; // 친구간의 관계를 나타낼 인접 리스트
    static boolean[] visited; // 해당 친구를 초대 리스트에 넣었는지 확인할 배열

    static class Friend {
        int fNum;
        int depth;
        Friend(int fNum, int depth){
            this.fNum = fNum;
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        friendsGraph = new int[N+1][N+1];
        visited = new boolean[N+1];

        for(int i = 0; i < M; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            friendsGraph[from][to] = 1;
            friendsGraph[to][from] = 1;
        }

        bfs(1); // 상근이부터 탐색!

        int ans = 0;
        for(int i = 2; i <= N; i++){
            if(visited[i]) ans++;
        }

        System.out.println(ans);
    }

    private static void bfs(int start){
        Queue<Friend> q = new ArrayDeque<>();
        q.offer(new Friend(start, 0));
        visited[start] = true;

        while(!q.isEmpty()){
            Friend fInfo = q.poll();
            int fNumb = fInfo.fNum;
            int depth = fInfo.depth;;

            for(int i = 1; i < N+1; i++){
                if(!visited[i] && friendsGraph[fNumb][i] == 1){
                    if(depth + 1 > 2) return;
                    visited[i] = true;
                    q.offer(new Friend(i, depth+1));
                }
            }
        }
    }
}
