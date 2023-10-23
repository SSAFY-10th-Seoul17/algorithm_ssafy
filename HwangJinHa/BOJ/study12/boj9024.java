package study9_1;

import java.io.*;
import java.util.*;

// 두 수의 합 
public class boj9024{
	static int n, k;
	static ArrayList<Integer> arr;

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	StringBuilder sb = new StringBuilder();
    	
    	arr = new ArrayList<Integer>();
    	
    	int T = Integer.valueOf(br.readLine());
    	for (int testCase = 0; testCase < T; testCase++) {
    		arr.clear();
    		st = new StringTokenizer(br.readLine(), " ");
    		n = Integer.valueOf(st.nextToken());
    		k = Integer.valueOf(st.nextToken());

    		st = new StringTokenizer(br.readLine(), " ");
    		for (int i = 0; i < n; i++) 
    			arr.add(Integer.valueOf(st.nextToken()));

    		Collections.sort(arr);
    		int l = 0;
    		int r = n-1;
    		int ansSum = 500_000_000;
    		int ansCnt = 0;
    		while (l != r) {
    			int sum = arr.get(l) + arr.get(r);
    			if (Math.abs(ansSum - k) > Math.abs(sum - k)) {
    				ansSum = sum;
    				ansCnt = 1;
    			}
    			else if (Math.abs(ansSum - k) == Math.abs(sum - k)) {
    				ansCnt++;
    			}
    			if (sum <= k) {
    				l++;
    			}
    			else {
    				r--;
    			}
    		}
    		sb.append(ansCnt).append("\n");
    	}
    	System.out.println(sb.toString());
    }
}
