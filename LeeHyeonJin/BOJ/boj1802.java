import java.io.*;
import java.util.*;

public class boj1802 {
	static int N;
	static char[] papers;
	static boolean possible;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			// input
			String paper = br.readLine();
			N = paper.length();
			papers = new char[N];
			for(int i=0; i<N; i++) papers[i] = paper.charAt(i);

			// solution
			possible = true;
			devideConquer(0, N-1);
			String res = possible ? "YES" : "NO";

			// output
			sb.append(res).append("\n");
		}

		System.out.println(sb);
	}

	static void devideConquer(int start, int end) {
		if(start == end) return;

		int mid = (start + end) / 2;
		for(int i=start; i<mid; i++) {
			if(papers[i] != papers[end-i]) continue;
			possible = false;
			return;
		}

		devideConquer(start, mid-1);
		devideConquer(mid+1, end);
	}
}
