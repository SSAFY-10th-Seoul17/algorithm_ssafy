package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj5014 {

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        int F=Integer.parseInt(st.nextToken());
        int start=Integer.parseInt(st.nextToken());
        int goal=Integer.parseInt(st.nextToken());
        int up=Integer.parseInt(st.nextToken());
        int down=Integer.parseInt(st.nextToken());

        int[] building=new int[F+1];
        Arrays.fill(building, 1_000_000);
        bfs(start,up,down,F,building);
        System.out.println(building[goal]==1000000?"use the stairs":building[goal]);
    }
    private static void bfs(int st,int up,int down,int f,int[] building) {
        Queue<Integer>q=new LinkedList<>();

        q.add(st);
        building[st]=0;
        while(!q.isEmpty()) {
            Integer cur=q.poll();

            if(cur+up<=f) {
                if(building[cur+up]>building[cur]+1) {
                    building[cur+up]=building[cur]+1;
                    q.add(cur+up);
                }

            }

            if(cur-down>=1&&building[cur-down]>building[cur]+1) {
                building[cur-down]=building[cur]+1;
                q.add(cur-down);
            }
        }
    }
}

