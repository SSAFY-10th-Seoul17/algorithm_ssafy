import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj10974{
	static int N; 
	static int[] A;
	static StringBuilder sb;
	static boolean[] isSelected;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		isSelected = new boolean[N+1];
		sb = new StringBuilder();
		perm(0);
		System.out.println(sb);
		
		
	}
	
	public static void perm(int cnt) {
		if (cnt == N) {
			for (int i : A) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		
		for (int i = 1; i <= N; i++) {
			if (isSelected[i]) continue;
			A[cnt] = i;
			isSelected[i] = true;
			perm(cnt+1);
			isSelected[i] = false;
		}
	}

}