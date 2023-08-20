import java.io.*;
import java.util.*;

public class boj10973 {
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
	
	static void getPrev(int depth) {
		if (depth == n) {
			for(int i = 0; i < n; i++) {
				sb.append(comb[i] + " ");
			}
			System.out.println(sb);
			System.exit(0);
		}
			for(int i = n; i >= 1; i--) {
			if (visited[i])
				continue;
			visited[i] = true;
			comb[depth] = i;
			getPrev(depth + 1);
		}
	}
	
	static void solution(int depth) {
		if (depth == -1) return;
		visited[comb[depth]] = false;
		for(int i = comb[depth] - 1; i >= 1; i--) {
			if (!visited[i]) {
				comb[depth] = i;
				visited[i] = true;
				getPrev(depth + 1);
			}
		}
		solution(depth - 1);
	}

	
	public static void main(String[] args) throws IOException{
		readLine();
		n = getInt();
		comb = new int[n];
		visited = new boolean[n+1];
		
		readLine();
		for(int i = 0; i < n; i++) {
			comb[i] = getInt();
			visited[i + 1] = true;
		}

		solution(n-1);
		System.out.println(-1);
	}
}