import java.io.*;
import java.util.*;

public class boj17265 {
	static int N;
	static String[][] map;
	static int[][] directions = { {0,1},{1,0} };
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		N = Integer.parseInt(br.readLine());
		map = new String[N][N];
		for(int r=0; r<N; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int c=0; c<N; c++) {
				map[r][c] = st.nextToken();
			}
		}

		// solution
		DFS(0, 1, "x", Integer.parseInt(map[0][0]));
		DFS(1, 0, "x", Integer.parseInt(map[0][0]));

		// output
		System.out.println(max+" "+min);
	}

	static void DFS(int r, int c, String op, int sum) {
		if(op.equals("*")) {
			sum *= Integer.parseInt(map[r][c]);
			op = "x";
		}
		else if(op.equals("+")) {
			sum += Integer.parseInt(map[r][c]);
			op = "x";
		}
		else if(op.equals("-")) {
			sum -= Integer.parseInt(map[r][c]);
			op = "x";
		}
		else if(op.equals("x")) {
			op = map[r][c];
		}

		if(r == N-1 && c == N-1) {
			min = min > sum ? sum : min;
			max = max < sum ? sum : max;
			return;
		}

		for(int d=0; d<2; d++) {
			int nr = r + directions[d][0];
			int nc = c + directions[d][1];
			if(!(nr>=0 && nr<N && nc>=0 && nc<N)) continue;
			DFS(nr, nc, op, sum);
		}
	}
}
