// 📝 

import java.util.*;
import java.io.*;

public class BOJ5014_스타트링크 {
	static int f,s,g,u,d,count;
	static int[] dir;
	static int[] move;
	static boolean[] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		f = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		g = Integer.parseInt(st.nextToken());
		u = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		count = 0;
		dir = new int[] {u,-d};
		move = new int[f+1]; // 움직인 수의 배열
		visited = new boolean[f+1];
		bfs();		
	}
	
	private static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		
		q.offer(s);
		visited[s] = true;
		move[s] = 0;
		
		while(!q.isEmpty()) {			
			
			int cur = q.poll();
			if (cur == g) {
				System.out.println(move[cur]);
				return;
			}
			
			for (int i=0; i<2; i++) {
				int nextcur = cur + dir[i];		
				if (nextcur>=1 && nextcur<=f && !visited[nextcur]) {
					visited[nextcur] = true;
					q.offer(nextcur);
					move[nextcur] = move[cur]+1;
				}
			}
		}
		
		System.out.println("use the stairs");
	}
}
