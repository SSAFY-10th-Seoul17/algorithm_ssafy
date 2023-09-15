package toyproject.somedaybucket.myAlgo.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1802 {

    static char[] paper;
    static int half;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int TC = Integer.parseInt(br.readLine());

        for(int i = 0; i < TC; i++){
            paper = br.readLine().toCharArray();
            half = paper.length/2;

            int start = 0;
            int end = paper.length-1;

            if(foldPaper(start, end)){
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
            count = 0;
        }
    } // end of main

    private static boolean foldPaper(int start, int end){
        if(start >= end) return true;
        int mid = start + (end - start)/2;

        for(int i = start; i < mid; i++){
            if(paper[i] == paper[end-i]) return false;
        }

        return foldPaper(start, mid-1) && foldPaper(mid+1, end);
    }
} // end of class
