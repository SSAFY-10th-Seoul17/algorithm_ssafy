import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class bj18353 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		// 입력 : 변사번호 순, 배치 : 전투력 내림차순
		// 배치과정 : 특정 위치 병사 열외 
		
		int[] soldiers = new int[n];
		int[] batch = new int[n]; // batch[i] : 병사 1부터 병사 i까지 배치할 경우의 길이
		Arrays.fill(batch, 1);
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < soldiers.length; i++) {
			soldiers[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if(soldiers[j] > soldiers[i]) { // 전투력 내림차순
					batch[i] = Math.max(batch[i], batch[j] + 1); // 병사 수 최대
				}
			}
		}
		
		int maxNum = 0; // 최대 남은 병사의 수
		for(int i = 0; i < n; i++) {
			maxNum = Math.max(batch[i], maxNum);
		}
		
		System.out.println(n - maxNum); // 열외해야 하는 병사의 수
		
	}// end of main
} // end of class
