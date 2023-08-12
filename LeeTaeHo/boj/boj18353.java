package LeeTaeHo.boj;

import java.io.*;
import java.util.*;

public class boj18353 {
    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n ; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        dp[0] = 1;
        Collections.reverse(arr);
        for(int i = 0; i < n; i++){
            for(int j = 1; j < n; j++){

            }
        }
    }
}
