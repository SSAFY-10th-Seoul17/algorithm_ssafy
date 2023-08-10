import java.io.*;

public class boj10974 {
	static int n;
	static int[] nums;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		nums = new int[n];
		visited = new boolean[n + 1];
		permutation(0);
		System.out.println(sb.toString());
	}
	public static void permutation(int cnt) {
		if(cnt == n) {
			for(int number : nums) {
				sb.append(number + " ");
			}
			sb.append("\n");
			return;
		}
		
		for(int i = 1; i <= n; i++) {
			if(!visited[i]) {
				nums[cnt] = i;
				visited[i] = true;
				permutation(cnt + 1);
				visited[i] = false;
			}
		}
	}
}
