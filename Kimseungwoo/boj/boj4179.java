package toyproject.somedaybucket.myAlgo.boj;

import java.io.*;
import java.util.*;

public class boj4179 {

    private static int N, M;
    private static int escapeMvCnt;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, -1, 0, 1};
    private static Deque<Location> q = new ArrayDeque<>();
    private static char[][] map;

    static class Location {
        int x, y;
        char sep = 'n';
        int moveCnt = 0;
        boolean[][] visited;

        Location(int i, int j, char sep, boolean[][] visited, int moveCnt){
            this.x = i;
            this.y = j;
            this.sep = sep;
            this.visited = visited;
            this.moveCnt = moveCnt;
        }

    }

    public static void main(String[] args) throws IOException {
        // 만약 입력받았을 때 지훈이나 불이라면 append하기!
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        escapeMvCnt = N*M+1;

        for(int i = 0; i < N; i++){
            map[i] = br.readLine().toCharArray();
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 'J'){
                    Location cur = new Location(i, j, 'J', new boolean[N][M], 0);
                    cur.visited[i][j] = true;
                    q.offer(cur);
                    break;
                }

            }
        }for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 'F') {
                    Location cur = new Location(i, j, 'F', new boolean[N][M], 0);
                    cur.visited[i][j] = true;
                    q.offer(cur);
                }
            }
        }
        escape();

        if(escapeMvCnt == N*M+1){
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(escapeMvCnt);
        }
    }

    public static void escape(){
        while(!q.isEmpty()){
            Location cur = q.poll();
            for(int i = 0; i < 4; i++){
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if(cur.sep == 'J'){
                    if(nx < 0 || ny < 0 || nx >= N || ny >= M){
                        if(map[cur.x][cur.y] == 'F'){
                            continue;
                        }
                        if(escapeMvCnt > cur.moveCnt+1){
                            escapeMvCnt = cur.moveCnt+1;
                        }

                        for(char[] e : map){
                            System.out.println(Arrays.toString(e));
                        }
                    } else {
                        if(map[nx][ny] != '.' || cur.visited[nx][ny]){
                            continue;
                        } else {
                            cur.visited[nx][ny] = true;
                            map[nx][ny] = 'J';
//                            boolean[][] visited = new boolean[N][M];
//                            for(int j = 0; j < N; j++){
//                                visited[j] = Arrays.copyOf(cur.visited[j], M);
//                            }
                            q.add(new Location(nx, ny, 'J', cur.visited, cur.moveCnt+1));
                        }
                    }
                } else {
                    if(nx < 0 || ny < 0 || nx >= N || ny >= M || cur.visited[nx][ny] || map[nx][ny] == '#'){
                        continue;
                    }
                    cur.visited[nx][ny] = true;
                    map[nx][ny] = 'F';
//                    boolean[][] visited = new boolean[N][M];

//                    for(int j = 0; j < N; j++){
//                        visited[j] = Arrays.copyOf(cur.visited[j], M);
//                    }

                    q.add(new Location(nx, ny, 'F', cur.visited, cur.moveCnt+1));
                }
            }
        }
    }
}
