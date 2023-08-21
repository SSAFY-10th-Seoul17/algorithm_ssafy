package boj;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj14716 {
    static int[] dx=new int[] {-1,-1,-1,0,0,1,1,1};
    static int[] dy=new int[] {0,1,-1,1,-1,0,1,-1};
    static boolean[][] flag;
    static int[][] arr;
    static int n;
    static int m;

    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());

        n=Integer.parseInt(st.nextToken());
        m=Integer.parseInt(st.nextToken());
        arr=new int[n][m];
        flag=new boolean[n][m];
        for(int i=0;i<n;i++) {
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++) {
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }//입력 종료
        int count=0;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(!flag[i][j]&&arr[i][j]==1) {
                    bfs(i,j);
                    count++;
                }
            }
        }
        System.out.println(count);


    }//end of main

    private static void bfs(int y,int x) {
        Queue<Point> q=new LinkedList<>();

        q.add(new Point(x,y));
        flag[y][x]=true;
        while(!q.isEmpty()) {
            Point tmp=q.poll();
            int curX=tmp.x;
            int curY=tmp.y;

            for(int i=0;i<8;i++) {
                int cmpX=curX+dx[i];
                int cmpY=curY+dy[i];
                if(cmpX>=0&&cmpX<m&&cmpY>=0&&cmpY<n
                        &&!flag[cmpY][cmpX]&&arr[cmpY][cmpX]==1) {
                    q.add(new Point(cmpX,cmpY));
                    flag[cmpY][cmpX]=true;
                }
            }
        }
    }
}
