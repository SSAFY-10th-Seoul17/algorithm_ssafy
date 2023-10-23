package boj;

import java.io.*;
import java.util.*;

class Pair {
    int x, y;
    Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class boj17086 {
	static int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};
    static int[] dy = {0, -1, 0, 1, -1, 1, -1, 1};
    static int N;
    static int M;
    static int[][] map;
    static Queue<Pair> queue = new ArrayDeque<>();
    static int distance = -1;
    static int[][] visited;
    /*
    * dx : 행 이동 배열
    * dy : 열 이동 배열
    * N : 행 수
    * M : 열 수
    * map : 상어들의 위치를 저장할 배열
    * queue : 상어들의 인덱스를 넣을 큐
    * ans : 상어 안전거리 저장
    * */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N+1][M+1];
        visited = new int[N+1][M+1];

        for(int i = 1; i<=N; i++){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j <= M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 1; i<=N; i++){
            for(int j = 1; j <= M; j++){
                if(map[i][j] == 1){
                    queue.offer(new Pair(i, j));
                }
            }
        }
        bfs();

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= M; j++){
                if(distance < visited[i][j]){
                    distance = visited[i][j];
                }
            }
        }

        System.out.println(distance);
    }

    private static void bfs(){

        while(!queue.isEmpty()){
            Pair location = queue.poll();
            int x = location.x;
            int y = location.y;

            for(int i = 0; i<8; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(!isPossible(nx, ny)) continue;

                // 빈 칸 탐색해야 함!

                if(visited[nx][ny] > 0) {
                    if(visited[nx][ny] > visited[x][y]+1){
                        visited[nx][ny] = visited[x][y]+1;
                    }
                    continue;
                }

                if(map[nx][ny] == 1){
                    continue;
                }

                visited[nx][ny] = visited[x][y] + 1;
                queue.offer(new Pair(nx, ny));
            }
        }
    }

    private static boolean isPossible(int x, int y){
        if(x < 1 || y < 1 || x > N || y > M){
            return false;
        }
        return true;
    }
}
