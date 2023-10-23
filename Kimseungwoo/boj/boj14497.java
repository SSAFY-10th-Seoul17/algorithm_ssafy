package toyproject.somedaybucket.myAlgo.boj;

import java.io.*;
import java.util.StringTokenizer;

public class boj14497 {

    static char[][] map;
    static int N, M;
    static int jx, jy;
    static int bx, by;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        jx = Integer.parseInt(st.nextToken())-1;
        jy = Integer.parseInt(st.nextToken())-1;
        bx = Integer.parseInt(st.nextToken())-1;
        by = Integer.parseInt(st.nextToken())-1;

        map = new char[N][M];

        for(int i = 0; i < N; i++){
            map[i] = br.readLine().toCharArray();
        }

        jump(jx, jy);

        System.out.println(count);
    }

    private static void jump(int i, int j){
        int curJump = 0;
        while(true){
            bfs(i, j, ++curJump, new boolean[N][M]);
            if(count > 0) break;
        }
    }

    private static void bfs(int i, int j, int cnt, boolean[][] visited){

        if (i == bx && j == by){
            count = cnt;
            return;
        }

        if(map[i][j] == '1'){
            map[i][j] = '0';
            return;
        }

        for(int k = 0; k < 4; k++){
            int nx = i + dx[k];
            int ny = j + dy[k];
            if(!checkPos(nx, ny)) continue;
            if(visited[nx][ny]) continue;

            visited[nx][ny] = true;
            bfs(nx, ny, cnt, visited);
        }
    }

    private static boolean checkPos(int i, int j){
        return (i < 0 || j < 0 || i >= N || j >= M) ? false : true;
    }
}
