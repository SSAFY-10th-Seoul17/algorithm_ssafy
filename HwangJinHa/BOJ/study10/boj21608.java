import java.io.*;
import java.util.*;

// 상어 초등학교 
public class Main {
	static int N;
	static ArrayList<Set<Integer>> favorite = new ArrayList<Set<Integer>>();
	static int[] order;
	static int[][] ans;
	
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.valueOf(br.readLine());
		ans = new int[N][N];

		int size = N*N;
		order = new int[size];

		for (int i = 0; i <= size; i++) favorite.add(new HashSet<Integer>());
		
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
//			order[i] = Integer.valueOf(st.nextToken());
			int num = Integer.valueOf(st.nextToken());
//			Set<Integer> f = new HashSet<Integer>();
			Set<Integer> f = favorite.get(num);
			for (int j = 0; j < 4; j++) 
				f.add(Integer.valueOf(st.nextToken()));
			
			// 1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
			int max = -1;
			int foundy = -1;
			int foundx = -1;
			ArrayList<int[]> answerCandidate = new ArrayList<>();
			for (int y = 0; y < N; y++) {
				for (int x = 0; x < N; x++) {
					int cnt = 0;
					if (ans[y][x] != 0)
						continue;
					for (int d = 0; d < 4; d++) {
						int yy = y + dy[d];
						int xx = x + dx[d];
						
						if (yy < 0 || N <= yy || xx < 0 || N <= xx)
							continue;
						
						if (f.contains(ans[yy][xx]))
							cnt++;
					}
					if (cnt > max) {
						max = cnt;
						answerCandidate = new ArrayList<int[]>();
						answerCandidate.add(new int[] {y, x});
					}
					else if (cnt == max)
						answerCandidate.add(new int[] {y, x});
				}
			}
			if (answerCandidate.size() == 1) {
				ans[answerCandidate.get(0)[0]][answerCandidate.get(0)[1]] = num;

				continue;
			}

			// 2. 1을 만족하는 칸이 여러개인 경우 인접한 칸 중 가장 비어있는 칸이 많은 자리를 정한다.
			// 3. 2를 만족하는 칸도 여러개인 경우 행의 번호가 가장작은 칸. 이도 여러개일 경우 열의 번호가 가장작은칸 
			max = -1;
			foundy = -1;
			foundx = -1;
			for (int[] loc : answerCandidate) {
//			for (int y = 0; y < N; y++) {
//				for (int x = 0; x < N; x++) {
				int y = loc[0];
				int x = loc[1];
				int cnt = 0;
				if (ans[y][x] != 0)
					continue;
				for (int d = 0; d < 4; d++) {
					int yy = y + dy[d];
					int xx = x + dx[d];
					
					if (yy < 0 || N <= yy || xx < 0 || N <= xx)
						continue;
					
					if (ans[yy][xx] == 0)
						cnt++;
				}
				if (cnt > max) {
					max = cnt;
					foundy = y;
					foundx = x;
				}
//				}
			}
			ans[foundy][foundx] = num;
		}
		int happiness = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					int y = i + dy[d];
					int x = j + dx[d];
					
					if (y < 0 || N <= y || x < 0 || N <= x)
						continue;

					if (favorite.get(ans[i][j]).contains(ans[y][x]))
						cnt++;
				}
				if (cnt == 0)
					continue;
				else {
					happiness += Math.pow(10, cnt-1);
				}
			}
		}
		System.out.println(happiness);
	}
}
