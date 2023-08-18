package boj;

import java.io.*;
import java.util.*;

public class boj5014 {

    static int F,S,G,U,D;
    /*
    * F : total floor
    * G : Destination
    * S : current location
    * U : up floor
    * D : down floor
    */
    static int[] floor;
    static int[] dx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        if(G == S){
            System.out.println(0);
            return;
        }

        floor = new int[F+1];
        floor[S] = 1;
        dx = new int[2];
        dx[0] = U;
        dx[1] = D*-1;

        bfs(S);
        //System.out.println(Arrays.toString(floor));
        if(floor[G] == 0){
            System.out.println("use the stairs");
        } else {
            System.out.println(floor[G]-1);
        }
    }

    private static void bfs(int start){
        Queue<Integer> q = new ArrayDeque<>();

        q.offer(start);

        while (!q.isEmpty()){
            int cur = q.poll();

            if(cur == G) return;

            for(int i = 0; i<2; i++){
                int nx = cur + dx[i];
                if(!isPossible(nx)){
                    continue;
                }
                floor[nx] = floor[cur]+1;
                q.offer(nx);
            }
        }
    }

    private static boolean isPossible(int x){
        if(x < 1 || x > F || floor[x] > 0){
            return false;
        }
        return true;
    }
}