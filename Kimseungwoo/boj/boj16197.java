package toyproject.somedaybucket.myAlgo.boj;

import java.io.*;
import java.util.*;

public class boj16197 {
    static class Coin {
        int x, y;
        int depth;
        Coin(int x, int y, int depth){
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
        @Override
        public String toString(){
            return "x : " + x + " y : " + y + " ";
        }

    }

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static char[][] map;
    static int N, M;
    static Queue<Coin> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for(int i = 0; i < N; i++){
            map[i] = br.readLine().toCharArray();
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[i][j] == 'o'){
                    map[i][j] = '.';
                    q.offer(new Coin(i, j, 0));
                }
            }
        }
        int ans = moveCoin();

        System.out.println(ans);
    } // end of main

    static int moveCoin(){
        while(true){
            Coin fir = q.poll();
            Coin sec = q.poll();
            if(fir.depth >= 10)
                return -1;
            if(fir.x == sec.x && fir.y == sec.y)
                continue;
            for(int i = 0; i < 4; i++){
                Queue<Coin> checkQ = new ArrayDeque<>();
                // 첫 번째 동전
                int nx = fir.x + dx[i];
                int ny = fir.y + dy[i];
                if(!out(nx, ny)){
                    if(map[nx][ny] == '#')
                        checkQ.offer(new Coin(fir.x, fir.y, fir.depth+1));
                    else {
                        checkQ.offer(new Coin(nx, ny, fir.depth+1));
                    }
                }
                // 두 번째 동전
                nx = sec.x + dx[i];
                ny = sec.y + dy[i];

                if(!out(nx, ny)){
                    if(map[nx][ny] == '#')
                        checkQ.offer(new Coin(sec.x, sec.y, sec.depth+1));
                    else {
                        checkQ.offer(new Coin(nx, ny, sec.depth+1));
                    }
                }
                if(checkQ.size() % 2 == 1)
                    return checkQ.poll().depth;

                while(!checkQ.isEmpty())
                    q.offer(checkQ.poll());
            }
        }
    } // end of moveCoin

    static boolean out(int x, int y){
        return (x < 0 || y < 0 || x >= N || y >= M) ? true : false;
    }


} // end of class
