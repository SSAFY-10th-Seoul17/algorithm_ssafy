import java.io.*;
import java.util.*;

public class boj10974 {
	static int n;
	static int[] nums;
	static boolean[] isSelected;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		//input
		n = Integer.parseInt(br.readLine());
		nums = new int[n];
		isSelected = new boolean[n+1];

		// solution
		permutation(0);

		// output
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	static void permutation(int cnt) {
		if(cnt == n) {
			for(int i=0; i<n; i++) {
				sb.append(nums[i]+" ");
			}
			sb.append("\n");
			return;
		}
		for(int i=1; i<=n; i++) {
			if(isSelected[i]) {
				continue;
			}
			nums[cnt] = i;
			isSelected[i] = true;
			permutation(cnt+1);
			isSelected[i] = false;
		}
	}
}
