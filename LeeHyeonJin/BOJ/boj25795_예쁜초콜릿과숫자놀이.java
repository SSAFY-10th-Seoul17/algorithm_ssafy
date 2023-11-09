import java.io.*;
import java.util.*;

public class boj25795_예쁜초콜릿과숫자놀이 {
	static int N, a, b, c;
	static char[] chocolates;
	static final int MOD = 100000, WHITE = 1, DARK = -1;
	static long res = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		// solution
		chocolates = new char[2*N];
		construct(0, N, 0);

		// output
		System.out.println(res);
	}

	static void construct(int prev, int cntW, int idx) {
		if(idx == 2*N) {
			res = Math.max(res, calc());
			return;
		}
		if(prev > 0) {
			chocolates[idx] = 'D';
			construct(prev+DARK, cntW, idx+1);
		}
		if(cntW > 0) {
			chocolates[idx] = 'W';
			construct(prev+WHITE, cntW-1, idx+1);
		}
	}

	static long calc() {
		long sum = a;
		for(char chocolate : chocolates) {
			if(chocolate == 'W') sum = (sum+b)%MOD;
			else sum = (sum*c)%MOD;
		}
		return sum;
	}
}
