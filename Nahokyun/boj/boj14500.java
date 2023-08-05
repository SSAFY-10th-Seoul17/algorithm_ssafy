package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj14500{
    public static int[] dx=new int[] {-1,0,1,0};//좌 하 우 상
    public static int[]	dy=new int[] {0,1,0,-1};
    public static int max=Integer.MIN_VALUE;
    public static int n;
    public static int m;
    public static int[][] arr;
    public static boolean[][] flag;
    public static void main(String[] args) throws NumberFormatException, IOException {
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
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                flag[i][j]=true;
                track(1,j,i,arr[i][j]);
                flag[i][j]=false;
            }
        }//dfs 깊이 4 탐색 종료

        for(int i=0;i<n-1;i++) {//ㅜ모양 탐색
            for(int j=0;j<m-2;j++) {
                int sum=0;
                for(int k=0;k<3;k++) {
                    sum+=arr[i][j+k];
                    //System.out.printf(i+" "+(j+k));
                }
                sum+=arr[i+1][j+1];
                //System.out.println();
                if(max<sum)
                    max=sum;
            }
        }
        for(int i=1;i<n;i++) {//ㅗ모양 탐색
            for(int j=0;j<m-2;j++) {
                int sum=0;
                for(int k=0;k<3;k++) {
                    sum+=arr[i][j+k];
                    //System.out.printf(i+" "+(j+k));
                }
                sum+=arr[i-1][j+1];
                //System.out.println();
                if(max<sum)
                    max=sum;
            }
        }
        for(int i=0;i<n-2;i++) {//ㅏ모양 탐색
            for(int j=0;j<m-1;j++) {
                int sum=0;
                for(int k=0;k<3;k++) {
                    sum+=arr[i+k][j];
                    //System.out.printf((i+k)+" "+(j));
                }
                //System.out.println();
                sum+=arr[i+1][j+1];
                if(max<sum)
                    max=sum;
            }
        }
        for(int i=0;i<n-2;i++) {//ㅓ모양 탐색
            for(int j=1;j<m;j++) {
                int sum=0;
                for(int k=0;k<3;k++) {
                    sum+=arr[i+k][j];
                    //System.out.printf((i+k)+" "+(j));
                }
                //System.out.println();
                sum+=arr[i+1][j-1];
                if(max<sum)
                    max=sum;
            }
        }
        System.out.println(max);
    }//end of main

    private static void track(int depth,int curX,int curY,int sum) {
        if(depth==4) {
            if(sum>max) {
                max=sum;
            }
            return;
        }
        for(int i=0;i<4;i++) {//좌 하 우 상
            int cmpX=curX+dx[i];
            int cmpY=curY+dy[i];

            if(cmpX>=0&&cmpX<m&&cmpY<n&&cmpY>=0&&!flag[cmpY][cmpX]) {
                flag[cmpY][cmpX]=true;
                track(depth+1,cmpX,cmpY,sum+arr[cmpY][cmpX]);
                flag[cmpY][cmpX]=false;
            }
        }
    }
}
