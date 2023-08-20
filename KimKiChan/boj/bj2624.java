import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj2624 {
	// k가지 동전 n1 ... nk 개씩 들어 있다
	// T원의 지폐 -> 동전
	// 입력 : 지폐 금액 : T, 동적 가시 수 : k, 각 동전의 금액 : p(i), 개수:n(i)
	// 출력 : 지폐를 동전으로 교환하는 방법의 가지수 (최대 2^31-1)
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 지폐 금액
		int t = Integer.parseInt(br.readLine());
		int[] money = new int[t+1]; // money[i] = i원을 만들 수 있는 경우의 수
		// 동전 가지 수
		int k = Integer.parseInt(br.readLine());
		// 동전 금액 p, 개수 n
		ArrayList<int[]> coin = new ArrayList<>(); // {p, n}
		for(int i = 0; i < k; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int p = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			coin.add(new int[] {p, n});
		}
		
		money[0] = 1;
		for(int i = 0; i < k; i++) {
			int p = coin.get(i)[0]; // 동전 금액
			int n = coin.get(i)[1]; // 동전 개수
			
			for(int j = t; j > 0; j--) { // 금액
				for(int l = 1; l <= n; l++) { // 개수
					if(j - p*l >= 0) {
						money[j] += money[j - p*l];
					}else break;
				}
			}
		}
		
		System.out.println(money[t]);
		
	}// end of main

}// end of class
