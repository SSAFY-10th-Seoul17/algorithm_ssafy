import java.io.*;
import java.util.*;

// 이진탐색으로 a의 수를 b배열에서 찾아서 더 작은 경우의 수의 개수를 더해주었습니다.
// 단, b배열에 중복된 수가 있을 경우에는 이진탐색의 결과가 제일 마지막에 있는 중복 수의 인덱스이므로
// 가장 먼저 나온 인덱스로 바꿔준 후 더 작은 수들의 갯수를 더해주었습니다.
public class BOJ7795_먹을것인가먹힐것인가 {
	static int n,m,result;
	static int[] a;
	static int[] b;
	public static void main(String[] args) throws Exception {
		// a는 자기보다 작은 먹이만 먹을 수 있음
		// a의 크기가 b 보다 큰 쌍이 몇개 있는지
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		
		for (int tc=1; tc<=t; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			a = new int[n];
			b = new int[m];
			
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<n; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<m; i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(b);
			result=0;
			solve();
			sb.append(result+"\n");

		} // end of tc
		System.out.println(sb.toString());
	}
	
	private static void solve() {
		for (int i=0; i<n; i++) {
			int index = Arrays.binarySearch(b, a[i]);
			if (index<0) { // 없을 때
				result += (index+1)* -1;
			}
			else {
				int temp = index;
				for (int k=index-1; k>=0; k--) {
					if (b[k] == a[i]) {
						temp = k;
					}
				}
				result+= temp;
			}
		}
	}
}
