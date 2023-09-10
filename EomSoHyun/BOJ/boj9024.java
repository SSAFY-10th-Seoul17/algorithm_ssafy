import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n, k;
	static int[] nums;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		int t = Integer.parseInt(st.nextToken());  // 테스트 케이스 개수
		for (int T = 1; T <= t; T++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			nums = new int[n];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(nums);
			find();
		}
		System.out.println(sb);
		
	}
	
	public static void find() {
		int start = 0;
		int end = n-1;
		int diff = Integer.MAX_VALUE;
		int cnt = 0;
		while (start < end) {
			if (Math.abs(k-(nums[start] + nums[end])) < diff) {
				cnt = 1;
				diff = Math.abs(k-(nums[start] + nums[end]));
			} else if (Math.abs(k-(nums[start] + nums[end])) == diff) {
				cnt++;
			}
			if (nums[start] + nums[end] < k) {
				start++;
			} else {
				end--;
			}
				
		}
		sb.append(cnt).append('\n');
	}
	

}
