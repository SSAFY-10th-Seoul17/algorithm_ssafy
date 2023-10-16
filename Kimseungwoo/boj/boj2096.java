package toyproject.somedaybucket.myAlgo.boj;

import java.io.*;
import java.util.*;

public class boj2096 {

    static int N;
    static int[] dp;
    static int[][] minDp;
    static int[][] maxDp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[4];
        minDp = new int[2][4];
        maxDp = new int[2][4];

        for(int i = 1; i <= N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 1; j <= 3; j++){
                dp[j] = Integer.parseInt(st.nextToken());
            }
            for(int j = 1; j <= 3; j++){
                if(j == 1){
                    int minVal = Math.min(minDp[0][1], minDp[0][2]);
                    int maxVal = Math.max(maxDp[0][1], maxDp[0][2]);
                    minDp[1][j] = dp[j] + minVal;
                    maxDp[1][j] = dp[j] + maxVal;

                } else if (j == 3){
                    int minVal = Math.min(minDp[0][3], minDp[0][2]);
                    int maxVal = Math.max(maxDp[0][3], maxDp[0][2]);

                    minDp[1][j] = dp[j] + minVal;
                    maxDp[1][j] = dp[j] + maxVal;
                } else {
                    int minVal = Math.min(minDp[0][3], Math.min(minDp[0][2], minDp[0][1]));
                    minDp[1][j] = dp[j] + minVal;

                    int maxVal = Math.max(maxDp[0][3], Math.max(maxDp[0][2], maxDp[0][1]));
                    maxDp[1][j] = dp[j] + maxVal;
                }
            }
            for(int k = 1; k <= 3; k++){
                minDp[0][k] = minDp[1][k];
                maxDp[0][k] = maxDp[1][k];
            }
        }

        int max = -1;
        int min = 900_001;

        for(int i = 1; i <= 3; i++){
            if(max < maxDp[1][i]){
                max = maxDp[1][i];
            }
            if(min > minDp[1][i]){
                min = minDp[1][i];
            }
        }

//        for(int[] e : minDp){
//            System.out.println(Arrays.toString(e));
//        }
//        System.out.println("======");
//        for(int[] e : maxDp){
//            System.out.println(Arrays.toString(e));
//        }
        System.out.println(max + " " + min);
    }
}
