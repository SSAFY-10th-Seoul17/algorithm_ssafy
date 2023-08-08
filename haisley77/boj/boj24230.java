import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj24230 {
	static int cnt;
	static boolean[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		int[] C = new int[N+1];
		visit = new boolean[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for (int i = 0 ; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}
		
		for (int i = 1; i <= N; i++) {
			C[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
			graph.get(b).add(a);
		}
	
		Queue<Integer> q = new LinkedList<Integer>();
		
		q.add(1);
		visit[1] = true;
		if (C[1] != 0) {
			cnt++;
		}
		while (!q.isEmpty()) {
			int now = q.poll();
			
			for (int i : graph.get(now)) {	
				if (!visit[i]) {
					visit[i] = true;
					q.add(i);
					if (C[i] != C[now]){
						cnt++;
					}
				}
			}

		}
		
		System.out.println(cnt);
		
	}


}
