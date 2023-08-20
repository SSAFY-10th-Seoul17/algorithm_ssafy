import java.io.*;
import java.util.*;

public class boj2004 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		System.out.println(Math.min(combi(n, 2) - combi(m, 2) - combi(n-m, 2), combi(n, 5) - combi(m, 5) - combi(n-m, 5)));
	}
	
	public static int combi(int num, int divnum) {
		int cnt = 0;
		while(num >= divnum) {
			cnt += num / divnum;
			num /= divnum;
		}
		return cnt;
	}
}
