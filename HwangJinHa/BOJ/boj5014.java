import java.io.*;
import java.util.*;

public class Main {

	static int f,s,g, u, d;
	static int[] len;
	static int[] dir = new int[2];
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		f = Integer.parseInt(st.nextToken());
		s = Integer.parseInt(st.nextToken());
		g = Integer.parseInt(st.nextToken());
		u = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		dir = new int[] {u, -d};
		
		len = new int[f+1];
		bfs(s);
		
	}
	
	static void bfs(int node) {
		Queue<Integer> q = new LinkedList<>();
		q.add(node);

		boolean[] visited = new boolean[f+1];
		visited[node] = true;

		len[node] = 0;
		
		while(!q.isEmpty()) {
			int now = q.poll();
			if(now == g) {
				System.out.println(len[now]);
				return;
			}
			
			for(int i=0; i<2; i++) {
				int next = now + dir[i];
				if(next > f || next <1 )
					continue;
				if(visited[next])
					continue;
				visited[next] = true;
				q.add(next);
				len[next]= len[now]+1;
			}
		}
		
		System.out.println("use the stairs");
		
	}
}