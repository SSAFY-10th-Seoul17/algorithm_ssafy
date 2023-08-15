import java.io.*;
import java.util.*;

public class boj10974 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static void readLine() throws IOException {
		st = new StringTokenizer(br.readLine());
	}
	static String getStr() {
		return st.nextToken();
	}
	static int getInt() {
		return Integer.valueOf(st.nextToken());
	}
	
	static boolean hasNext() {
		return st.hasMoreTokens();
	}
	
	static int n;
	static int[] comb;
	static boolean[] visited;
	static boolean foundSame = false;
	
	static void solution(int depth) {
		if (depth == n) {
			for(int i = 0; i < n; i++) {
				sb.append(comb[i] + " ");
			}
			sb.append("\n");
		}
		for(int i = 1; i <= n; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			comb[depth] = i;
			solution(depth + 1);
			visited[i] = false;
		}
	}

	
	public static void main(String[] args) throws IOException{
		readLine();
		n = getInt();
		comb = new int[n];
		visited = new boolean[n+1];
		
		solution(0);
		System.out.println(sb);
	}
}