import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class boj14567 {

	public static void main(String[] args) throws Exception {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = new StringTokenizer(br.readLine()," ");
		 StringBuilder sb = new StringBuilder();
		 int N = Integer.parseInt(st.nextToken());	// 정점수
		 int M = Integer.parseInt(st.nextToken());	// 간선수
		 boolean[][] graph = new boolean[N+1][N+1];	// 방향그래프, 인접행렬
		 int[] indegree = new int[N+1];	// 진입차수저장배열
		 int[] visited = new int[N+1];	// 방문순서저장배열
		 for (int i = 0; i < M; i++) {
			 st = new StringTokenizer(br.readLine()," ");
			 int A = Integer.parseInt(st.nextToken());
			 int B = Integer.parseInt(st.nextToken());
			 graph[A][B] = true;		// 간선연결
			 indegree[B]++;				// 진입차수갱신
		 }
		 ArrayDeque<Integer> q = new ArrayDeque<>();
		 for (int i = 1; i <= N; i++) {
			 if (indegree[i] == 0) {
				 q.offerLast(i);
				 visited[i] = 1;
			 }
		 }
		 while (!q.isEmpty()) {
			 int cur = q.pollFirst();
			 
			 for (int i = 1; i <= N; i++) {
				 if (!graph[cur][i]) continue;	// 인접하지 않은 정점
				 if (visited[i] != 0) continue;	// 방문한적있는정점
				 
				 indegree[i]--;					// 인접정점진입차수갱신
				 if (indegree[i] == 0) {
					 visited[i] = visited[cur] + 1;
					 q.offerLast(i);
				 }
			 }
		 }
		 
		 for (int i = 1; i <= N; i++) {
			 sb.append(visited[i]).append(" ");
		 }
		 System.out.println(sb.toString());
	
	}

}
