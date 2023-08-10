package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj5427 {

    private static int m;
    private static int n;
    private static char[][] arr;
    private static int[][] fire;
    private static int[] dx=new int[] {-1,0,0,1};
    private static int[] dy=new int[] {0,1,-1,0};
    private static boolean isSuccess;
    private static int escapeTime;

    private static TimeAndPoint start;
    private static Queue<TimeAndPoint> q=new LinkedList<>();
    private static boolean[][] flag;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int t=Integer.parseInt(br.readLine());

        for(int test=1;test<=t;test++) {
            StringTokenizer st=new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());

            arr = new char[n][m];
            flag = new boolean[n][m];
            fire=new int[n][m];
            isSuccess=false;
            escapeTime=0;

            for(int i=0;i<n;i++)
                Arrays.fill(fire[i], 1001);

            for(int i=0;i<n;i++) {
                String s=br.readLine();
                for(int j=0;j<m;j++) {
                    arr[i][j]=s.charAt(j);
                    if(s.charAt(j)=='@') {
                        start=new TimeAndPoint(j,i,0);
                        continue;
                    }
                    if(s.charAt(j)=='*') {
                        q.add(new TimeAndPoint(j,i,0));
                        flag[i][j]=true;
                        fire[i][j]=0;
                    }
                }
            }//end of input
            if(start.x==0||start.x==m-1||start.y==0||start.y==n-1) {
                System.out.println(1);
                continue;
            }
            firebfs();

            for(int i=0;i<n;i++)//flag 초기화
                Arrays.fill(flag[i], false);

            escapebfs();

            System.out.println(isSuccess?escapeTime+1:"IMPOSSIBLE");

        }//end of testcase

    }//end of main
    private static void firebfs() {
        while(!q.isEmpty()) {
            TimeAndPoint cur=q.poll();
            int curX=cur.x;
            int curY=cur.y;
            int curTime=cur.time;

            for(int i=0;i<4;i++) {
                int cmpX=curX+dx[i];
                int cmpY=curY+dy[i];
                int cmpTime=curTime+1;

                if(cmpX>=0&&cmpX<m&&cmpY>=0&&cmpY<n
                        &&(!flag[cmpY][cmpX]||fire[cmpY][cmpX]>cmpTime)
                        &&arr[cmpY][cmpX]!='#') {
                    fire[cmpY][cmpX]=cmpTime;
                    flag[cmpY][cmpX]=true;
                    q.add(new TimeAndPoint(cmpX,cmpY,cmpTime));
                }

            }
        }
    }
    private static void escapebfs() {
        Queue<TimeAndPoint> escape=new LinkedList<>();
        escape.add(start);

        while(!escape.isEmpty()) {
            TimeAndPoint cur=escape.poll();
            int curX=cur.x;
            int curY=cur.y;
            int curTime=cur.time;
            flag[curY][curX]=true;
            for(int i=0;i<4;i++) {
                int cmpX=curX+dx[i];
                int cmpY=curY+dy[i];
                int cmpTime=curTime+1;
                if(cmpX>=0&&cmpX<m&&cmpY>=0&&cmpY<n
                        &&!flag[cmpY][cmpX]
                        &&arr[cmpY][cmpX]!='#'
                        &&cmpTime<fire[cmpY][cmpX]) {
                    escape.add(new TimeAndPoint(cmpX, cmpY, cmpTime));
                    flag[cmpY][cmpX]=true;
                    if(cmpX==0||cmpX==m-1||cmpY==0||cmpY==n-1) {
                        isSuccess=true;
                        escapeTime=cmpTime;
                        return;
                    }
                }
            }
        }
    }

}//end of class
class TimeAndPoint{
    int x;
    int y;
    int time;
    public TimeAndPoint(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }
}
