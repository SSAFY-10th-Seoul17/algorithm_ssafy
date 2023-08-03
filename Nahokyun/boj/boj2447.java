package boj;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class boj2447 {
    public static int n;
    public static char[][] arr;
    public static void main(String[] args) throws IOException {
        Scanner sc=new Scanner(System.in);
        BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
        n=sc.nextInt();
        arr=new char[n][n];
        recur(0,0,n);

        for(char[] i:arr){
            bw.write(i);
            bw.write('\n');
            bw.flush();
        }
    }

    private static void recur(int startY,int startX,int n) {
        if(n==0)
            return;
        for(int i=startY;i<n;i++){
            for(int j=startX;j<n;j++){
                arr[i][j]='*';
            }
        }
        for(int i=startY+n/3;i<startY+2*n/3;i++){
            for(int j=startX+n/3;j<startX+2*n/3;j++){
                arr[i][j]=' ';
            }
        }
        recur(startY,startX,n/3);
        recur(startY,startX+n/3,n/3);
        recur(startY,startX+2*n/3,n/3);
        recur(startY+n/3,startX,n/3);
        recur(startY+n/3,startX+n/3,n/3);
        recur(startY+n/3,startX+2*n/3,n/3);
        recur(startY+2*n/3,startX,n/3);
        recur(startY+2*n/3,startX+n/3,n/3);
        recur(startY+2*n/3,startX+2*n/3,n/3);
    }
}
