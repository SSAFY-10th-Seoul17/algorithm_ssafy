package boj;

import java.io.*;
import java.util.*;

public class boj3184{

    static int totalSheepCnt;
    static int totalWolfCnt;

    static int R; // row
    static int C; // column;

    // direction array
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static char[][] map;
    static boolean[][] visited;

    static class Location{
        int x, y;
        Location(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][];
        visited = new boolean[R][C];

        for(int i = 0; i < R; i++){
            map[i] = br.readLine().toCharArray();
        }

        for(int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'o') totalSheepCnt++;
                if (map[i][j] == 'v') totalWolfCnt++;
            }
        }

        for(int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if(visited[i][j]) continue;
                if(map[i][j] == 'o' || map[i][j] == 'v') {
                    bfs(i, j);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(totalSheepCnt).append(" ").append(totalWolfCnt);

        System.out.println(sb);
    } // end of main

    private static void bfs(int i, int j){
        Queue<Location> q = new ArrayDeque<>();
        q.offer(new Location(i, j));
        visited[i][j] = true;
        int curWolfCnt = 0;
        int curSheepCnt = 0;

        if(map[i][j] == 'o') curSheepCnt++;
        else curWolfCnt++;

        /*
        * if curWolfCnt >= curSheepCnt then totalSheepCnt -= curSheepCnt
        * else totalWolfCnt -= curWolfCnt
        * */

        while(!q.isEmpty()){
            Location lc = q.poll();
            int x = lc.x;
            int y = lc.y;

            for(int k = 0; k < 4; k++){
                int nx = x + dx[k];
                int ny = y + dy[k];

                if(checkPs(nx, ny) && !visited[nx][ny]){
                    if(map[nx][ny] == 'o') curSheepCnt++;
                    else if(map[nx][ny] == 'v') curWolfCnt++;

                    visited[nx][ny] = true;
                    q.offer(new Location(nx, ny));
                }
            }
        }
        if(curWolfCnt >= curSheepCnt){
            totalSheepCnt -= curSheepCnt;
        } else {
            totalWolfCnt -= curWolfCnt;
        }
    }


    private static boolean checkPs(int x, int y){
        return (x < 0 || y < 0 || x >= R || y >= C || map[x][y] == '#') ? false : true;
    }
} // end of class
