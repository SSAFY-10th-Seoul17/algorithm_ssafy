import java.io.*;
import java.util.*;

/**
 * 아이디어 : a ~ b 까지의 최단거리가 가장 작은 노드를 탐색한다 => 모든 노드 ~ 모든 노드 => 플로이드-워셜
 */
public class boj2660 {
	static final int INF = Integer.MAX_VALUE / 2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N+1][N+1];

		/**
		 * 플로이드-워셜 알고리즘 수행 준비 :
		 * - 인접배열 생성
		 * - 자기자신~자기자신 사이의 거리는 0
		 * - 인접하는(친구관계) 노드를 제외하고는 모두 INF
		 * - 인접하면 1
		 */
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(i != j) map[i][j] = INF;
			}
		}

		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a == -1 && b == -1) break;
			map[a][b] = map[b][a] = 1;
		}

		// solution
		// (1) 모든노드~모든노드 간의 최단거리 구하기
		for(int k=1; k<=N; k++) { //경유지
			for(int i=1; i<=N; i++) { //출발지
				for(int j=1; j<=N; j++) { //도착지
					map[i][j] = Math.min(map[i][j], map[i][k]+map[k][j]);
				}
			}
		}

		// (2) 최단거리 구하기
		int minScore = INF;
		int[] scores = new int[N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(map[i][j] >= INF) continue;
				scores[i] = Math.max(scores[i], map[i][j]);
			}
			minScore = Math.min(minScore, scores[i]);
		}

		// (3) 최단거리가 가장 작은 노드들 구하기
		List<Integer> minNodes = new ArrayList<>();
		for(int i=1; i<=N; i++) {
			if(scores[i] == minScore) minNodes.add(i);
		}

		// output
		StringBuilder sb = new StringBuilder();
		sb.append(minScore).append(" ").append(minNodes.size()).append("\n");
		for(int minNode : minNodes) sb.append(minNode).append(" ");
		System.out.println(sb.toString());
	}
}
