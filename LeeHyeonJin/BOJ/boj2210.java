import java.io.*;
import java.util.*;

public class boj2210 {
	static int[][] directions = { {-1,0},{1,0},{0,-1},{0,1} };
	static int[][] nums;
	static Set<String> exists = new HashSet<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		nums = new int[5][5];
		for(int i=0; i<5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<5; j++) {
				nums[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// solution : 이동하면서 숫자를 "차례대로" 이어붙인다 -> 이전의 숫자가 다음의 결과에 영향 O -> DFS
		for(int r=0; r<5; r++) {
			for(int c=0; c<5; c++) {
				// 숫자판의 모든 곳을 시작위치로 숫자를 이어붙여보고, 중복되는 건 카운팅 X -> 완전탐색
				dfs(1, r, c, String.valueOf(nums[r][c]));
			}
		}

		// output
		System.out.println(exists.size());
	}

	static void dfs(int len, int r, int c, String numStr) {
		if(len == 6) {
			// Set 자료형은 어차피 중복 X -> get() 메서드가 불필요
			// -> 대신 List에 비해 효율적인 존재여부 확인 contains() or 길이가 이미 중복배제된 길이
			exists.add(numStr);
			return;
		}
		for(int d=0; d<4; d++) {
			int nr = r+directions[d][0];
			int nc = c+directions[d][1];
			if(nr>=0 && nr<5 && nc>=0 && nc<5) {
				dfs(len+1, nr, nc, numStr+nums[nr][nc]);
			}
		}
	}
}
