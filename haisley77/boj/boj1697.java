import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1697 {
	static int N, K, res;
	static int[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visit = new int[100001];	
		find(N);
		System.out.println(visit[K]-1);
	}
	
	public static void find(int x) {
		
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(x);
		visit[x] = 1;

		haha:
		while (!q.isEmpty()) {
			int now = q.poll();	
			int[] tmp = {-1,1,now};    // 현재 위치 바뀔 때마다 값 변경

			for (int i = 0; i < tmp.length; i++) {
				int px = now + tmp[i];
				
				if (px >= 0 && px <= 100000 && visit[px] == 0) {
					q.add(px);
					visit[px] = visit[now] + 1;
				}
				
				if (px == K) {
					break haha;
				}
			}		
		}	
	}
		
}