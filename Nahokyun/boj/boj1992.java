package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1992 {
    public static int[][] arr;
    public static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

        n=Integer.parseInt(br.readLine());
        arr=new int[n][n];
        for(int i=0;i<n;i++){
            String input=br.readLine();
            for(int j=0;j<n;j++){
                arr[i][j]=input.charAt(j)-'0';
            }
        }
        recur(0,0,n);
    }
    private static void recur(int startX,int startY,int n){
        int cur=arr[startY][startX];
        boolean flag=false;
        if(n==1){
            System.out.printf("%d",cur);
            return;
        }
        for(int i=startY;i<startY+n;i++){
            for(int j=startX;j<startX+n;j++){
                if(cur!=arr[i][j]){
                    flag=true;
                    break;
                }
            }
            if(flag)
                break;
        }
        if(!flag)
            System.out.printf("%d",cur);
        else{
            System.out.printf("(");
            recur(startX,startY,n/2);
            recur(startX+n/2,startY,n/2);
            recur(startX,startY+n/2,n/2);
            recur(startX+n/2,startY+n/2,n/2);
            System.out.printf(")");
        }
    }
}
