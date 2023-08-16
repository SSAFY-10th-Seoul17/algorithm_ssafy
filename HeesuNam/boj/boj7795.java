import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int lenA = Integer.parseInt(st.nextToken());
			int lenB = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine()," ");
			int[] A = makeArr(st, lenA);
			st = new StringTokenizer(br.readLine()," ");
			int[] B = makeArr(st, lenB);
			
			sb.append( pair(A,B)).append("\n");
		}
		System.out.println(sb.toString());
	} // end of main

	private static int pair(int[] A, int[] B) {
		int cnt = 0;
		for(int a:A) {
			for(int b: B) {
				if(a<=b) break;
				cnt++;
			}
		}
		return cnt;
	}

	private static int[] makeArr(StringTokenizer st, int N) {
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		return arr;
	}
} // end of class