package toyproject.somedaybucket.myAlgo.boj;

import java.io.*;
import java.util.*;

public class boj1780 {

    private static int[] paper = new int[3];

    private static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        dp = new int[N][N];

        // dp input
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                dp[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        countingPaper(0, 0, N);

        for(int x : paper){
            System.out.println(x);
        }
    }

    private static void countingPaper(int x, int y, int size){
        // base condition
        if(size == 1){
            paper[dp[x][y]+1]+=1;
            return;
        }

        int curVal = dp[x][y];
        boolean flag = true;

        for(int i = x; i < x + size; i++){
            for(int j = y; j < y + size; j++){
                if(curVal != dp[i][j])
                    flag = false;
            }
        }

        if(!flag){
            int newSize = size/3;
            // 1 row
            countingPaper(x, y, newSize);
            countingPaper(x, y+newSize, newSize);
            countingPaper(x, y+newSize*2, newSize);

            // 2 row
            countingPaper(x+newSize, y, newSize);
            countingPaper(x+newSize, y+newSize, newSize);
            countingPaper(x+newSize, y+newSize*2, newSize);

            // 3 row
            countingPaper(x+newSize*2, y, newSize);
            countingPaper(x+newSize*2, y+newSize, newSize);
            countingPaper(x+newSize*2, y+newSize*2, newSize);
        } else {
            paper[dp[x][y]+1] += 1;
        }
    }
}
