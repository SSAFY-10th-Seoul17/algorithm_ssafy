import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj10972 {
	static int[] A;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		A = new int[N];
		int index = -1;
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		while (st.hasMoreTokens()) {
			A[++index] = Integer.parseInt(st.nextToken());
		}
		int i = 0;
		for (i = N-1; i > 0; i--) {
			if (A[i] > A[i-1]) break;
		}
		if (i == 0) {
			sb.append(-1);
		}
		else {
			for (int j = N-1; j >= i; j--) {
				if (A[i-1] < A[j]) {
					swap(i-1,j);
					break;
				}
			}
			for (int j = i; j < N-1; j++) {
				for (int k = j+1; k < N; k++) {
					if (A[j] > A[k]) {
						swap(j,k);
					}
				}
			}
			for (int a : A) {
				sb.append(a).append(" ");
			}
		}
		System.out.println(sb);
	}
	private static void swap(int i, int j) {
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}
}
