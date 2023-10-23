package boj;

import java.io.*;
import java.util.*;

public class boj18353 {
	 static int[] soldiers;
	    static int[] dp;
	    static int N;
	    static int byeSoldiers;

	    public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
	        N = Integer.parseInt(st.nextToken());
	        soldiers = new int[N];
	        dp = new int[N];

	        for(int i = 0; i<N; i++){
	            dp[i] = 1;
	        }
	        st = new StringTokenizer(br.readLine(), " ");
	        for(int i = 0; i < N; i++){
	            soldiers[i] = Integer.parseInt(st.nextToken());
	        }

	        decreasingSequence();
	        for(int e : dp){
	            if (e > byeSoldiers){
	                byeSoldiers = e;
	            }
	        }
	        //System.out.println(Arrays.toString(dp));
	        System.out.println(N - byeSoldiers);

	    }

	    private static void decreasingSequence(){
	        for(int i = 1; i < N; i++){
	            int j = 1;
	            while (i >= j){
	                if(soldiers[i-j] > soldiers[i]){
	                    dp[i] = Math.max(dp[i-j]+1, dp[i]);
	                }
	                j++;
	            }
	        }
	    }
}
