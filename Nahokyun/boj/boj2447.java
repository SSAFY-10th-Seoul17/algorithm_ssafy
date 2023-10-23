package boj;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
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

        // BufferedWriter사용
        long tcheck_st=System.nanoTime();
        for(char[] i:arr){
            bw.write(i);
            bw.write('\n');
            bw.flush();
        }
        long tcheck_fi=System.nanoTime();
        System.out.println(tcheck_fi-tcheck_st);

//
//        // for문 사용
//        tcheck_st=System.nanoTime();
//        for(int i=0;i<n;i++){
//            for(int j=0;j<n;j++)
//                System.out.printf(String.valueOf(arr[i][j]));
//            System.out.println();
//        }
//        tcheck_fi=System.nanoTime();
//        System.out.println(tcheck_fi-tcheck_st);
//        tcheck_st=System.nanoTime();
//
//        //forEach문 사용
//        for(char[] i:arr){
//            for(char j:i)
//                System.out.printf(String.valueOf(j));
//            System.out.println();
//        }
//        tcheck_fi=System.nanoTime();
//        System.out.println(tcheck_fi-tcheck_st);

    }

    private static void recur(int startY,int startX,int n) {
        if(n==3){
            if(startX==9){
                if(startY==9)
                    n=n;
            }


        }

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
