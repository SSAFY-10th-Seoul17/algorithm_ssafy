import java.io.*;
import java.util.*;

// 조 짜기 
public class boj2229{
	static int n;
	static int[] arr;
	static int[][] diff;
	
	static int[] dp;
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	n = Integer.valueOf(br.readLine());
    	arr = new int[n];
    	diff = new int[n][n];
    	dp = new int[n];

    	st = new StringTokenizer(br.readLine());

    	for (int i = 0; i < n; i++) {
    		arr[i] = Integer.valueOf(st.nextToken());
    	}
    	
    	for (int l = 0; l < n; l++) {
    		int min = arr[l];
    		int max = arr[l];

    		for (int r = l+1; r < n; r++) {
    			if (min > arr[r]) min = arr[r];
    			if (max < arr[r]) max = arr[r];
    			diff[l][r] = max - min;
    		}
    	}
    	
    	for (int i = 1; i < n; i++) {
    		dp[i] = Math.max(dp[i-1], diff[0][i]);
    		for (int j = 0; j < i; j++) {
    			int newval = dp[j] + diff[j+1][i];
    			if (newval > dp[i])
    				dp[i] = newval;
    		}
    	}
    	System.out.println(dp[n-1]);
    }
}
