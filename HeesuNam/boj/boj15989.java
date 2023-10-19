import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int maxVal = 0;
		int[] inputs = new int[T];
		for (int i = 0; i < T; i++) {
			inputs[i]=Integer.parseInt(br.readLine());
			if(maxVal < inputs[i]) maxVal = inputs[i];
		}
		int[] dp = new int[maxVal+1];
		dp[0]=1;
		for (int i = 1; i <= 3; i++) {
			for (int num = i; num <= maxVal; num++) {
				dp[num]+=dp[num-i];
			}	
		}
		for (int tc = 0; tc < T; tc++) {
			sb.append(dp[inputs[tc]]).append("\n");
		}
		System.out.println(sb.toString());
	} // end of main
} // end of class
