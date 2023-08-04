import java.io.*;
import java.util.*;

public class boj2004 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		// solution : 팩토리얼의 0의 개수 구하기와 동일
		int cnt5 = count(5, n) - count(5, m) - count(5, (n-m)); // n!/(n-r)!r! -> 0의 개수는 나누면 빼기가 됨
		int cnt2 = count(2, n) - count(2, m) - count(2, (n-m));
		int result = Math.min(cnt5, cnt2);

		// output
		bw.write(result+"");
		bw.flush();
		bw.close();
	}

	static int count(int num, int sNum) { // 팩토리얼 0의 개수를 구하는 공식
		int cnt = 0;
		while(sNum >= num) {
			cnt += sNum / num;
			sNum /= num;
		}
		return cnt;
	}
}
