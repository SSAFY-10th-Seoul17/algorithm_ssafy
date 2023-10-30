package study9_1;

import java.io.*;
import java.util.*;

// 탈출 
public class boj16397{
	static int n, t, g;
	static boolean[] visited;
	
	static final int MAX = 99999;

    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	
    	n = Integer.valueOf(st.nextToken());
    	t = Integer.valueOf(st.nextToken());
    	g = Integer.valueOf(st.nextToken());
    	
    	visited = new boolean[100000];
    	
    	Queue<Integer> q = new LinkedList<Integer>();
    	Queue<Integer> pushCnt = new LinkedList<Integer>();
    	q.add(n);
    	pushCnt.add(0);
    	visited[n] = true;

    	
    	while (!q.isEmpty()) {
    		int now = q.poll();
    		int nowPush = pushCnt.poll();
    		if (nowPush > t)
    			continue;

    		if (now == g) {
    			System.out.println(nowPush);
    			System.exit(0);
    		}
    			
    		
    		int a = now + 1;
    		if (!(a > MAX || visited[a])) {
	    		visited[a] = true;
	    		q.add(a);
	    		pushCnt.add(nowPush+1);
    		}
    		
    		int b = now * 2;
    		if (b > MAX) {
    			continue;
    		}
    		b -= Math.pow(10, (int)Math.log10(b));
    		if (!visited[b]) {
    			visited[b] = true;
	    		q.add(b);
	    		pushCnt.add(nowPush+1);
    		}
    	}
    	System.out.println("ANG");
	}
}
