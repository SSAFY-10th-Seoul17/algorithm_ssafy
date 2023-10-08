import java.io.*;
import java.util.*;

public class Main {
	private static int N, M, H;
	private static final int MOD = 10007;
	private static ArrayList<ArrayList<Integer>> blocks;
	private static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 학생
		M = Integer.parseInt(st.nextToken()); // 최대 블록의 수
		H = Integer.parseInt(st.nextToken()); // 목표높이
		blocks = new ArrayList<>();
		blocks.add(new ArrayList<>());
		for (int i = 1; i <= N; i++) {
			blocks.add(new ArrayList<>());
			st = new StringTokenizer(br.readLine(), " ");
			while (st.hasMoreTokens()) {
				blocks.get(i).add(Integer.parseInt(st.nextToken()));
			}
		}
		dp = new int[N + 1][H + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(dp[i], -1);
		}
		System.out.println(makeTower(1, 0));
	}

	private static int makeTower(int student, int height) {
		if(height==H) {
			return 1;
		}
 		if (student > N || height > H)
			return 0;
		if (dp[student][height] != -1)
			return dp[student][height];
		dp[student][height] = 0;
		
		for (int block : blocks.get(student)) {
			dp[student][height] += makeTower(student + 1, height + block)%MOD; // 선택
		}
		dp[student][height] += makeTower(student + 1, height)%MOD; // 선택안함
		return dp[student][height]%MOD;
	}
}
