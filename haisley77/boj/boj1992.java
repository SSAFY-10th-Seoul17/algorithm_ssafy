import java.io.*;

public class boj1992 {
	static int[][] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}
		quadTree(0,0,N);
		System.out.println(sb);
	}

	public static boolean isPossible(int y, int x, int n) {
		int tmp = arr[y][x];
		for (int i = y; i < y+n; i++) {
			for (int j = x; j < x+n; j++) {
				if (arr[i][j] != tmp) return false;
			}
		}
		return true;
	}

	public static void quadTree(int y, int x, int n) {
		if (isPossible(y,x,n)) {
			sb.append(arr[y][x]);
			return;
		} 
		n /= 2;
		sb.append("(");
		quadTree(y,x,n);
		quadTree(y,x+n,n);
		quadTree(y+n,x,n);
		quadTree(y+n,x+n,n);
		sb.append(")");
	}

}
