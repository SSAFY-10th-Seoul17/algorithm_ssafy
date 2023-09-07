package study9_1;

import java.io.*;
import java.util.*;

// 퍼즐  
public class boj1525{
	static final int ANS = 123456780;

	static HashMap<Integer, Integer> length = new HashMap<>();
	
	static final int[] dy = {0, 1, 0, -1};
	static final int[] dx = {1, 0, -1, 0};
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
    	
    	int num = 0;
    	for (int i = 0; i < 3; i++) {
    		st = new StringTokenizer(br.readLine());
    		for (int j = 0; j < 3; j++) {
    			num = num * 10 + Integer.valueOf(st.nextToken());
    		}
    	}
    	
    	Queue<Integer> q = new LinkedList<Integer>();
    	Queue<Integer> idx0 = new LinkedList<Integer>();
    	length.put(num, 0);
    	q.add(num);
    	for (int i = 0 ; i < 9; i++) 
    		if (getNum(num, i) == 0) {
    			idx0.add(i);
    			break;
    		}

    	while (!q.isEmpty()) {
    		int now = q.poll();
    		int i0 = idx0.poll();
    		int dist = length.get(now);
    		if (now == ANS) {
    			System.out.println(dist);
    			System.exit(0);
    		}
    		
    		int y = i0 / 3;
    		int x = i0 % 3;

    		for (int i = 0; i < 4; i++) {
    			int yy = y + dy[i];
    			int xx = x + dx[i];
    			if (yy < 0 || 3 <= yy || xx < 0 || 3 <= xx)
    				continue;
    			int next = swap(now, y, x, yy, xx);
    			if (length.keySet().contains(next))
    				continue;
    			length.put(next, dist+1);
    			q.add(next);
    			idx0.add(yy*3+xx);
    		}
    	}
    	System.out.println(-1);
    	
	}
    
    private static int swap(int now, int y, int x, int yy, int xx) {
    	int i = (y*3+x);
    	int ii = (yy*3+xx);
    	int n = getNum(now, i);
    	int nn = getNum(now, ii);
    	int l = (int)Math.pow(10, 8-i);
    	int ll = (int)Math.pow(10, 8-ii);
    	return now - (l * n) - (ll * nn) + (l * nn) + (ll * n);
	}

	static int getNum(int num, int ith) {
    	ith = 8 - ith;
    	int mask = (int)Math.pow(10, ith);
    	return num / mask % 10;
    }
}
