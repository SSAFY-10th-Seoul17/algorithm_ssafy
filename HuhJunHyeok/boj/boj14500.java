import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 14500. 테트로미노
 */
public class boj14500 {
	static int n, m, maxValue = 0;
	static int[][] paper;
	static boolean[][] isChecked;
	static int[][] delta = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우 탐색용 delta
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer nm = new StringTokenizer(br.readLine(), " ");
		n = Integer.parseInt(nm.nextToken());
		m = Integer.parseInt(nm.nextToken());
		paper = new int[n][m];
		isChecked = new boolean[n][m];
		
		for(int i = 0; i < n; i++) {
			StringTokenizer values = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < m; j++) {
				paper[i][j] = Integer.parseInt(values.nextToken());
			}
		}
		
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				isChecked[i][j] = true;
				dfs(1, i, j, paper[i][j]);// ㅜ 모양 제외하고 체크
				remainTetromino(i, j); // ㅜ 모양 체크
				isChecked[i][j] = false;
			}
		}
		
		System.out.println(maxValue); // 단순히 하나의 값만 출력하므로 StringBuilder 사용하지 않음.
	}
	
	public static boolean isInBoundary(int x, int y) {
		return 0 <= x && x < n && 0 <= y && y < m;
	}
	
	/**
	 * ㅜ 를 제외한 나머지 테트로미노 모양은 depth가 4인 dfs를 통해 최댓값 구하기 가능.(한 번에 그리기 가능하기 때문.)
	 */
	public static void dfs(int depth, int x, int y, int sum) {
		if(depth == 4) {
			maxValue = Math.max(maxValue, sum);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			int nextX = x + delta[i][0];
			int nextY = y + delta[i][1];
			if(isInBoundary(nextX, nextY) && !isChecked[nextX][nextY]) {
				isChecked[nextX][nextY] = true;
				dfs(depth + 1, nextX, nextY, sum + paper[nextX][nextY]);
				isChecked[nextX][nextY] = false;
			}
		}
	}
	
	/**
	 * ㅜ 테트로미노 모양에서의 최댓값 구하기.
	 * + 그 최댓값과 나머지 테트로미노 모양의 최댓값을 비교해서 최종 최댓값로 설정.
	 * x, y 좌표의 위치가 해당 모양의 ㅡ ㅣ 모양의 중간 칸을 의미.
	 */
	public static void remainTetromino(int x, int y) {
		for(int i = 0; i < 4; i++) { // ㅓ ㅜ ㅗ ㅏ 를 다 만들기 위한 i값.
			int caseMax = paper[x][y];
			boolean flag = true; // 최종 최댓값 비교 계산 여부 플래그
			
			for(int j = 0; j < 3; j++) { // x, y 좌표의 칸을 제외하면 진행하면서 찾아야하는 칸은 3칸.
				int direction = (i + j) % 4; // i 0 -> 0, 1, 2 / i 1 -> 1, 2, 3 / i 2 -> 2, 3, 0 / i 3 -> 3, 0, 1  ==> ㅓㅜㅗㅏ 순서.
				int nextX = x + delta[direction][0];
				int nextY = y + delta[direction][1];
				
				if(!isInBoundary(nextX, nextY)) {
					flag = false;
					break;
				}
				caseMax += paper[nextX][nextY];
			}
			
			if(flag) {
				maxValue = Math.max(maxValue, caseMax);
			}
		}
	}
}
