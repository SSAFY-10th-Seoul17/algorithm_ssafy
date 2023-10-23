import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int A, B, n, m;
	static boolean[] bridge;
	static Queue<int[]> q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		A = Integer.parseInt(st.nextToken());  // 스카이 콩콩 힘 
		B = Integer.parseInt(st.nextToken());  // 스카이 콩콩 힘 
		n = Integer.parseInt(st.nextToken());  // 동규 현재 위치 
		m = Integer.parseInt(st.nextToken());  // 주미 현재 위치 
		
		bridge = new boolean[100001];
		bfs();

	}
	
	public static boolean inRange(int x) {
		return 0 <= x && x <= 100000;
	}
	
	public static boolean check(int x, int cnt) {
		if (x != m) {
			q.offer(new int[] {x, cnt+1});
			bridge[x] = true;
			return true;
		}
		return false;
	}
	
	public static void bfs() {
		int cnt = 0;
		q = new LinkedList<int[]>();
		q.offer(new int[] {n, 0});
		while(!q.isEmpty()) {
			int[] w = q.poll();
			int v = w[0];
			cnt = w[1];
			
			if (inRange(v-1) && !bridge[v-1]) {
				if (!check(v-1, cnt)) break;				
			}
			if (inRange(v+1) && !bridge[v+1]) {
				if (!check(v+1, cnt)) break;		
			}
			if (inRange(v+A) && !bridge[v+A]) {
				if (!check(v+A, cnt)) break;
			}
			if (inRange(v-A) && !bridge[v-A]) {
				if (!check(v-A, cnt)) break;
			}
			if (inRange(v+B) && !bridge[v+B]) {
				if (!check(v+B, cnt)) break;
			}
			if (inRange(v-B) && !bridge[v-B]) {
				if (!check(v-B, cnt)) break;
			}
			if (inRange(v*A) && !bridge[v*A]) {
				if (!check(v*A, cnt)) break;				
			}
			if (inRange(v*B) && !bridge[v*B]) {
				if (!check(v*B, cnt)) break;				
			}
			
		}
		
		System.out.println(cnt+1);

	}

}
