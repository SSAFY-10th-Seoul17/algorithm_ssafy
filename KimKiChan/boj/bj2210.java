import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class bj2210 {
	private static int[][] arr = new int [5][5];
	private static HashSet<String> set = new HashSet<>(); // 숫자열을 넣는 집합
	private static int[] dx = {-1, 1, 0, 0};
	private static int[] dy = {0, 0, -1, 1};
	private static int[] numList = new int[6];

	public static void main(String[] args) throws Exception {
		// 5x5 숫자판 배열
		// 임의의 원소에서 시작 -> 모든 원소 순회
		// 숫자 string 생성 -> set에 넣어서 중복 방지
		// 1칸씩 5번 이동
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int  j= 0; j < 5; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				dps(i, j, 0);
			}
		}
		System.out.println(set.size());
		
	}

	private static void dps(int x, int y, int cnt) {//i, j 시작
		numList[cnt] = arr[x][y];
		if(cnt == 5) {
			set.add(Arrays.toString(numList));
			return;
		}
		for(int i = 0; i < 4; i++) {
			if(x+dx[i] < 5 && x+dx[i] >= 0 && y+dy[i] < 5 && y+dy[i] >=0) {
				dps(x + dx[i], y+dy[i], cnt+1);
			}
		}
	}
}