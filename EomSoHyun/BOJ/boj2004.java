import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static public int calc(int n, int x) {
		int cnt = 0;
		while (x <= n) {
			n = n /x;
			cnt += n;
		}
		return cnt;
			
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int res = Math.min(calc(n, 2) - calc(n-m, 2) - calc(m,2), calc(n, 5) - calc(n-m, 5) - calc(m, 5));
		System.out.println(res);

	}

}
