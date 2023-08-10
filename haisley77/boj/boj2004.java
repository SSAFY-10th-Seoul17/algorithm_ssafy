import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2004 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		long n = Integer.parseInt(st.nextToken());
		long m = Integer.parseInt(st.nextToken());
		int a = count(n,2)- count(m,2) - count(n-m,2);
		int b = count(n,5)- count(m,5) - count(n-m,5);	
		System.out.println(a < b ? a : b);

	}
	private static int count(long n, int l) {
		int cnt;
		for (cnt = 0; n >= l; cnt += n / l, n /= l);
		return cnt;
	}
}
