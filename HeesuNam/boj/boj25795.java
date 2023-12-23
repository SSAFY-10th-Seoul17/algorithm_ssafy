import java.io.*;
import java.util.*;

public class Main {
	private static int N, a, b, c;
	private static long[] dp;
	private static long ans = 0;
	static final int MOD = 100000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		makeChocolate(0, N, N, a);
		System.out.println(ans);
	} // end of main

	private static void makeChocolate(int curLen, int white, int dark, long sum) {
		if(curLen==2*N) {
			ans  = Math.max(ans, sum);
			return;
		}
		if (white > 0) {
			// white가 남아있으면 white
			makeChocolate(curLen + 1, white - 1, dark, (sum + b) % MOD);
		}
		if (white < dark) {
			// dark는 white와 갯수를 맞춰야함
			makeChocolate(curLen + 1, white, dark - 1, (sum * c) % MOD);
		}
	}

} // end of class
