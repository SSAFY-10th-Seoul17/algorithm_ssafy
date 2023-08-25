import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj7795 {

	private static int[] A;
	private static int[] B;
	private static int res;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			A = new int[N];
			B = new int[M];
			st = new StringTokenizer(br.readLine()," ");
			for (int i = 0; i < N; i++) {
				A[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine()," ");
			for (int i = 0; i < M; i++) {
				B[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(A);
			Arrays.sort(B);
			
			res = 0;
			int i2 = 0;	
			for (int i1 = 0 ; i1 < N; i1++) {
				while (i2 < M && A[i1] > B[i2]) {
					i2++;
				}
				res += i2;
			}


			sb.append(res).append("\n");
		}
		System.out.println(sb.toString());
	}

}
