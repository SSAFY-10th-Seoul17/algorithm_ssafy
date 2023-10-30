import java.io.*;
import java.util.*;

// 단방향 그래프
// 간선잇기(인접행렬) from(small) -> to(big)
// 플로이드 워셜로 연결된 간선들 잇기
// 한 정점에서 알수없는 정점세기 
	// 자신을 제외한 정점의 개수 = 나보다 큰애([자신][정점]), 나보다 작은애([정점][자신])가 이어지지 않은 정점의 개수
public class Main {
	private static final int INF = 4950; // N(N-1)/2

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] graph = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			Arrays.fill(graph[i], INF);
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int bigger = Integer.parseInt(st.nextToken());
			int smaller = Integer.parseInt(st.nextToken());
			graph[smaller][bigger] = 1;
		}

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if (i == k || graph[i][k] == INF)
					continue;
				for (int j = 1; j <= N; j++) {
					if (j == i || graph[k][j] == INF)
						continue;
					if (graph[i][j] > graph[i][k] + graph[k][j]) {
						graph[i][j] = graph[i][k] + graph[k][j];
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int thing = 1; thing <= N; thing++) {
			int cnt = 0;
			for (int j = 1; j <= N; j++) {
				if (graph[thing][j] == INF && graph[j][thing] == INF)
					cnt++;
			}
			sb.append(cnt-1).append("\n");
		}
		System.out.println(sb.toString());
	} // end of main
} // end of class
