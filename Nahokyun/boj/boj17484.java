package boj;

import java.util.Scanner;

public class boj17484 {
    public static int n,m;
    public static int[] dx=new int[] {-1,0,1};
    public static int[] last;
    public static int[][] arr;
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);

        n=sc.nextInt();
        m=sc.nextInt();
        last=new int[m];
        arr=new int[n][m];

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                arr[i][j]=sc.nextInt();
            }
        }
        for(int i=0;i<m;i++) {
            last[i]=10000000;
        }
        for(int i=0;i<m;i++) {
            dfs(0,i,-1,0);
        }
        int min=100000;
        for(int i=0;i<m;i++) {
            min=Math.min(min, last[i]);
        }
        System.out.println(min);
    }//end of main

    private static void dfs(int depth,int x,int preDist,int sum) {//현재의 깊이,x좌표,직전방향, 현재까지 합

        if(depth==n) {
            last[x]=Math.min(last[x],sum);
            return;
        }

        for(int i=0;i<3;i++) {
            int tmp=x+dx[i];
            if(tmp<0||tmp>=m||i==preDist)
                continue;
            dfs(depth+1,tmp,i,sum+arr[depth][tmp]);
        }
    }

}