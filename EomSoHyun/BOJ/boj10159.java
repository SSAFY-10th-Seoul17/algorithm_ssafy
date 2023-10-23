import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int n, m, cnt;
	static List<List<Integer>> light;
	static List<List<Integer>> heavy;
	static boolean visited[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());  // 물건의 개수
		
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());  // 물건 쌍의 개수
		
		light = new ArrayList<>();
		heavy = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			light.add(new ArrayList<>());
			heavy.add(new ArrayList<>());
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken()); 
			int v2 = Integer.parseInt(st.nextToken()); 
			light.get(v1).add(v2);
			heavy.get(v2).add(v1);
		}
		
		
		for (int i = 1; i <= n; i++) {
			cnt = n-1;
			light(i);
			heavy(i);
			System.out.println(cnt);
		}
		
	}
	
	public static void light(int s) {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[n+1];
		q.offer(s);
		visited[s] = true;
		while (!q.isEmpty()) {
			int v = q.poll();
			for (Integer w : light.get(v)) {
				if (visited[w]) continue;
				q.offer(w);
				visited[w] = true;
				cnt--;
			}
		}
		
	}
	
	public static void heavy(int s) {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] visited = new boolean[n+1];
		q.offer(s);
		visited[s] = true;
		while (!q.isEmpty()) {
			int v = q.poll();
			for (Integer w : heavy.get(v)) {
				if (visited[w]) continue;
				q.offer(w);
				visited[w] = true;
				cnt--;
			}
		}
	
	}
	
	

}
