import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int[] nums = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < nums.length; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		int s = 0;
		int e = n-1;
		int num1 = 0;
		int num2 = 0;
		int maxSum = Integer.MAX_VALUE;
		
		while (s < e) {
			int sum = nums[s] + nums[e];
			
			if (Math.abs(sum) < maxSum) {
				maxSum = Math.abs(sum);
				num1 = s;
				num2 = e;
			}
			
			if (sum > 0) {
				e--;
			}
			else {
				s++;
			}
		}
		
		System.out.println(nums[num1] + " " + nums[num2]);
		
		
	}

}
