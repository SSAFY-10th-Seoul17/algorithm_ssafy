import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 과자나눠주기 {
	// 같은 길이의 과자 나눠주기
	// M명의 조카, N개의 과자
	// 과자는 여러 조각으로 나눠질 수 있지만 합칠 수는 없다
	// 과자의 길이는 양의 정수
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		// 조카의 수
		int m = Integer.parseInt(st.nextToken());
		// 과자의 수
		int n = Integer.parseInt(st.nextToken());
		// 과자의 길이 n개
		int[] snack = new int[n];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < snack.length; i++) {
			snack[i] = Integer.parseInt(st.nextToken());
		}
		// 오름차순 정렬
		Arrays.sort(snack);
		
		// 과자의 길이 중요
		// snack[n-1]부터 시작
		// snack[n-i] / snackLen -> count
		// 나누기 연산이 0인데 아직 m개를 달성하지 못하면 snackLen를 이분탐색을 통해서 다시 시작
		
		int count = 0;
		int start = 1;
		int end = snack[n-1];
		int snackLen = 0;
		int maxLen = 0;
		while(start <= end) {
			snackLen = (start+end)/2;
			count = 0;
			for(int i = 1; i <= n && n-i>=0; i++) {
				int num = snack[n-i] / snackLen;
				count += num;
				if(count > m) break;
			}
			if(count < m) {
				end = snackLen-1;
			}else if(count >= m){
				start = snackLen+1;
				maxLen = snackLen;
			}
		}
		System.out.println(maxLen);
	}// end of main
}// end of class
