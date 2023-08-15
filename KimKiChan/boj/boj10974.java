import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj10974 {
	static int n;
	static boolean[] isSelected;
	static int numList[];
	static StringBuilder sb = new StringBuilder("");
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		isSelected = new boolean[n+1];
		numList = new int[n];
		
		perm(0);
		
		System.out.println(sb.toString());
		
	}
	public static void perm(int cnt) {
		if(cnt == n) {
			for(int i = 0; i < n; i++) {
				sb.append(numList[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = 1; i <= n; i++) {
			if(isSelected[i] == true) {
				continue;
			}
			numList[cnt] = i;
			isSelected[i] = true;
			perm(cnt + 1);
			isSelected[i] = false;
			
		}
		
	}
	
}
