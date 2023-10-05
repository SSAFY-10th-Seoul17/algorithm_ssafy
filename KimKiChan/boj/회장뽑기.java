import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

//2660번
public class 회장뽑기 {
	private final static int INF = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		//다른 모든 회원과 친구 -> 1점
		//다른 모든 회원이 친구이거나 친구의 친구 -> 2점
		//다른 정점과의 거리 중 최소거리가 1
		
		// 회원의 수 m
		int m = Integer.parseInt(br.readLine());
		
		int[][] friendship = new int[m+1][m+1];
		for(int i = 0; i <= m; i++) {
			Arrays.fill(friendship[i], INF);
			friendship[i][i] = 0;
		}

		int[] member = new int[2];

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		member[0] = Integer.parseInt(st.nextToken());
		member[1] = Integer.parseInt(st.nextToken());
		
		while(member[0] != -1 && member[1] != -1) {
			friendship[member[0]][member[1]] = 1;
			friendship[member[1]][member[0]] = 1;
			
			st = new StringTokenizer(br.readLine(), " ");
			member[0] = Integer.parseInt(st.nextToken());
			member[1] = Integer.parseInt(st.nextToken());
		}
		
		br.close(); // 입력 끝
		
		for(int k = 1; k <= m; k++) {
			for(int i = 1; i <= m; i++) {
				if(i==k) continue;
				for(int j = 1; j <= m; j++) {
					if(j == k || j == i) continue;
					if(friendship[i][k] != INF && friendship[k][j] != INF) {
						friendship[i][j] = Math.min(friendship[i][j], friendship[i][k] + friendship[k][j]);
					}
				}
			}
		}
		
		int minScore = m+1;
		int count = 0;
		ArrayList<Integer> candidates = new ArrayList<>();
		
		for(int i = 1; i <= m; i++) {
			int score = 0;
			for(int j = 1; j <= m; j++) {
				if(score < friendship[i][j] && i != j) {
					score = friendship[i][j];
				}
			}
			if(minScore > score) {
				minScore = score;
				count = 1;
				candidates.clear();
				candidates.add(i);
			}else if(minScore == score) {
				count++;
				candidates.add(i);
			}
		}
		
		sb.append(minScore).append(" ").append(count).append("\n");
		for(int i = 0; i < candidates.size(); i++) {
			sb.append(candidates.get(i)).append(" ");
		}
		
		System.out.println(sb);
		
	}
}
