import java.io.*;
import java.util.*;

public class boj5014 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine());
		int top = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		int goal = Integer.parseInt(st.nextToken());
		int up = Integer.parseInt(st.nextToken());
		int down = Integer.parseInt(st.nextToken());

		// solution :
		// 처음생각 : 엘리베이터 "이동" -> 이전의 이동한 층이 다음 이동에 영향 O -> DFS => 시간초과
		// BFS로 풀어도 될까? 문제가 최단거리라서 그런가?? -> 큐에 넣어서 풀어도 상관은 없을 것 같다
		int min = Integer.MAX_VALUE;
		boolean[] visited = new boolean[top+1];
		Queue<int[]> queue = new LinkedList<>();

		visited[start] = true;
		queue.offer(new int[] {start, 0}); // 현재위치, 버튼누른횟수

		while(!queue.isEmpty()) {
			int[] info = queue.poll();
			int now = info[0];
			int cnt = info[1];

			if(now == goal) {
				min = Math.min(min, cnt);
				break;
			}
			if(cnt >= min) { // 이미 더 작은 경우의 수가 있다면 해당 경우는 살펴볼 필요 X
				break;
			}

			for(int i=0; i<2; i++) {
				int next = now;
				if(i == 0) {
					next += up;
				} else {
					next -= down;
				}
				if(next>=1 && next<=top && !visited[next]) {
					visited[next] = true;
					queue.offer(new int[] {next, cnt+1});
				}
			}
		}

		// output
		if(min != Integer.MAX_VALUE) {
			System.out.println(min);
		} else {
			System.out.println("use the stairs");
		}
	}
}
