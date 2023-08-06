import java.io.*;
import java.util.*;

public class boj24230 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// input
		int N = Integer.parseInt(br.readLine());

		int[] colors = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1; i<=N; i++) {
			colors[i] = Integer.parseInt(st.nextToken());
		}

		List<List<Integer>> nodes = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			nodes.add(new ArrayList<>());
		}
		for(int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nodes.get(a).add(b);
			nodes.get(b).add(a);
		}

		// solution : 순환x -> 그래프 -> 퍼져나감 -> dfs
		// 준비
		Stack<int[]> stack = new Stack<>();
		int[] colored = new int[N+1];
		boolean[] visited = new boolean[N+1];
		int cnt = 0;
		// 초기화
		stack.push(new int[] {1, colors[1]});
		visited[1] = true;

		// dfs
		while(!stack.isEmpty()) {
			int[] now = stack.pop();
			if(colored[now[0]] != now[1]) { // 현재 색 != 원하는 색
				cnt++;
				colored[now[0]] = now[1];
			}

			List<Integer> nexts = nodes.get(now[0]);
			for(int next : nexts) {
				if(!visited[next]) {
					colored[next] = now[1];
					stack.push(new int[] {next, colors[next]});
					visited[next] = true;
				}
			}
		}

		// output
		bw.write(cnt+"");
		bw.flush();
		bw.close();
	}
}
