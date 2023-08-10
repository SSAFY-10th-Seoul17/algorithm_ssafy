// 07:03 -
// 📝 
// 0을 결정 = 2 * 5 -> 2와 5의 개수 구하기
import java.io.*;
import java.util.*;

public class BOJ2004_조합0의개수 {
	public static void main(String[] args) throws IOException {
		// 끝자리 0의 개수 출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		sb.append(Math.min(count(n, 2) - count(n-m,2) - count(m,2), count(n, 5) - count(n-m,5) - count(m,5)));
		System.out.println(sb.toString());
	}
	
	private static int count(int num, int div) {
		int count = 0;
		
		while (num >= div) {
			count += num / div;
			num /= div;
		}
		
		return count;
	}

}
