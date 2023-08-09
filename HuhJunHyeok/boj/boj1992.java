import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * [boj] 1992. 쿼드트리
 */
public class boj1992 {
	static int n;
	static int[][] video;
	static StringBuilder sb = new StringBuilder(); // 가변 문자열(정답 문자열)
	
	public static void main(String[] args) throws Exception  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		n = Integer.parseInt(br.readLine());
		video = new int[n][n];
		
		for(int i = 0; i < n; i++) {
			String str = br.readLine();
			char[] tempArr = str.toCharArray();
			for(int j = 0; j < n; j++) {
				video[i][j] = tempArr[j] - '0';
			}
		}
		
		quadTreeDfs(n, 0, 0);
		
		bw.write(sb + "");
		bw.flush();
		bw.close();
	}
	
	/**
	 * 쿼드트리 문제를 divide and conquer(분할정복), dfs를 활용하여 해결하는 함수
	 * x, y는 해당 영역의 좌측 상단의 칸의 좌표.
	 * size는 해당 영역의 가로 또는 세로 길이.
	 */
	public static void quadTreeDfs(int size, int x, int y) {
		// 먼저, 해당 공간 영역이 압축 가능한 경우 압축
		if(isAllSame(size, x, y)) {
			sb.append(video[x][y]);
			return;
		}
		
		int newSize = size / 2; // 압축이 불가능한 경우. size를 절반으로 설정하여 하나의 영역을 4개의 영역으로 나눈다.
		
		sb.append('('); // 분할하였을 때 같은 depth에서는 여닫는 괄호를 통해 depth 구분
		
		// 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래 순서
		quadTreeDfs(newSize, x, y);
		quadTreeDfs(newSize, x, y + newSize);
		quadTreeDfs(newSize, x + newSize, y);
		quadTreeDfs(newSize, x + newSize, y + newSize);
		
		sb.append(')'); // 분할하였을 때 같은 depth에서는 여닫는 괄호를 통해 depth 구분
	}
	
	/**
	 * x,y 좌표 기준 size 만큼의 공간이 압축 가능한지 여부를 확인하는 함수
	 */
	public static boolean isAllSame(int size, int x, int y) {
		int refValue = video[x][y];
		int xMaxRange = x + size;
		int yMaxRange = y + size;
		
		for(int i = x; i < xMaxRange; i++) {
			for(int j = y; j < yMaxRange; j++) {
				if(refValue != video[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
