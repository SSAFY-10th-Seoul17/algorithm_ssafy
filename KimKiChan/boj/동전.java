package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//9084번
public class 동전 {
	//주어진 금액을 만드는 모든 방법의 개수
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int test = Integer.parseInt(br.readLine());
		for(int t = 0; t < test; t++) {
			// 동전의 가지 수 n
			int n = Integer.parseInt(br.readLine());
			int[] coin = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine()); 
			for (int i = 0; i < n; i++) {
				coin[i] = Integer.parseInt(st.nextToken());
			}
			
			// 동전으로 만들어야 할 금액 m
			int m = Integer.parseInt(br.readLine());
			
			int[] money = new int[m+1]; // money[i]: i원을 만드는 방법의 개수
			
			//m원을 만드는 방법의 개수
			money[0] = 1;
			for(int i = 0; i < n; i++) {
				for(int j = coin[i]; j <= m; j++) {
					money[j] += money[j-coin[i]];
				}
			}
			
			sb.append(money[m]).append("\n");
			
		}// end of test case
		System.out.println(sb);
		
	}// end of main
}// end of class

