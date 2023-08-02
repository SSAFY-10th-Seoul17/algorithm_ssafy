import java.io.*;

public class boj1992 {
	static int[][] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		for (int i = 0; i < n; i++) {
			String[] brArr = br.readLine().split("");
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(brArr[j]);
			}
		}
		recursion(0, 0, n);
		System.out.println(sb.toString());
	}

	public static void recursion(int x, int y, int size) {
		if(isCheck(x, y, size)) {
			sb.append(arr[x][y]);
			return;
		}
		
		sb.append("(");
		int newSize = size / 2;
		recursion(x, y, newSize);
		recursion(x, y + newSize, newSize);
		recursion(x + newSize, y, newSize);
		recursion(x + newSize, y + newSize, newSize);
		
		sb.append(")");
	}

	public static boolean isCheck(int x, int y, int size) {
		int check = arr[x][y];
		
		for(int i = x; i < x + size; i++) {
			for(int j = y; j < y + size; j++) {
				if(arr[i][j] != check) {
					return false;
				}
			}
		}
		return true;
	}
}

/*
 * 0
 */