import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int n = Integer.parseInt(st.nextToken());
		int[] nums = new int[n];
		int[] result = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < nums.length; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Stack<Integer> stack = new Stack<>();
		for (int i = nums.length-1; i > -1 ; i--) {
			while (true) {
				if (stack.isEmpty()) {
					result[i] = -1;
					stack.push(nums[i]);
					break;
				}
				int num = stack.pop();
				if (nums[i] < num) {
					result[i] = num;
					stack.push(num);
					stack.push(nums[i]);
					break;
				}
				
			}
		
		 }
		
		for (int i = 0; i < result.length; i++) {
			sb.append(result[i]).append(" ");
		}
		
		System.out.println(sb);
		
	}

}
