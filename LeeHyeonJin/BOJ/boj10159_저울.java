import java.io.*;
import java.util.*;

public class boj10159_저울 {
	static int N, M;
	static List<List<Integer>> lgts = new ArrayList<>();
	static List<List<Integer>> hvys = new ArrayList<>();
	static int[] results;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// input
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		for(int i=0; i<=N; i++) {
			lgts.add(new ArrayList<>());
			hvys.add(new ArrayList<>());
		}
		for(int i=0; i<M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int hvy = Integer.parseInt(st.nextToken());
			int lgt = Integer.parseInt(st.nextToken());
			hvys.get(lgt).add(hvy);
			lgts.get(hvy).add(lgt);
		}

		// solution
		results = new int[N+1];
		for(int i=1; i<=N; i++) {
			BFSLGT(i);
			BFSHVY(i);
			check(i);
		}

		// output
		for(int i=1; i<=N; i++) sb.append(results[i]).append("\n");
		System.out.println(sb.toString());
	}

	static void BFSLGT(int num) {
		Queue<Integer> queue = new ArrayDeque<>();
		visited = new boolean[N+1];
		queue.offer(num);
		visited[num] = true;

		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(int next : lgts.get(now)) {
				if(visited[next]) continue;
				queue.offer(next);
				visited[next] = true;
			}
		}
	}

	static void BFSHVY(int num) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(num);

		while(!queue.isEmpty()) {
			int now = queue.poll();
			for(int next : hvys.get(now)) {
				if(visited[next]) continue;
				queue.offer(next);
				visited[next] = true;
			}
		}
	}

	static void check(int num) {
		for(int i=1; i<=N; i++) {
			if(!visited[i]) results[num]++;
		}
	}
}
