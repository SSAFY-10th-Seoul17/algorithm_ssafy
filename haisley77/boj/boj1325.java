import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1325 {
	static ArrayList<ArrayList<Integer>> arr = new ArrayList<>();;
	static int N,M;
	static boolean[] isVisited;
	static int[] res;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		res = new int[N+1];
		
        for(int i=0; i <= N; i++) {
            arr.add(new ArrayList<Integer>());
        }
        
		for (int i = 0 ; i < M; i++) {
	      	st = new StringTokenizer(br.readLine()," ");
	      	int A = Integer.parseInt(st.nextToken());
	      	int B = Integer.parseInt(st.nextToken());
	      	arr.get(B).add(A);
		}
		
		int max = res[1];
		for (int i = 1; i <= N; i++) {
			isVisited = new boolean[N+1];
			bfs(i);
			max = Math.max(res[i], max);
		}

		for (int i = 1; i <= N; i++) {
			if (res[i] == max) {
				sb.append(i).append(" ");
			}
		}
		System.out.println(sb.toString());
		
	}
	private static void bfs(int i) {
		Queue<Integer> q = new LinkedList<>();	

		q.add(i);
		isVisited[i] = true;
			
		while (!q.isEmpty()) {
			int v = q.poll();
	
			for (int u : arr.get(v)) {
				if (!isVisited[u]) {
					q.add(u);
					isVisited[u] = true;
					res[i]++;
				}
			}
		}
	}

}
