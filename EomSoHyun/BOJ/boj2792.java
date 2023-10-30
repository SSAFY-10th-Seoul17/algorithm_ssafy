import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());  // 아이들 수
		int m = Integer.parseInt(st.nextToken());  // 색상 수
		
		int start = 1;
		int end = 0;
		
		int nums[] = new int[m];  // 각 색상의 보석 수
		for (int i = 0; i < nums.length; i++) {
			nums[i] = Integer.parseInt(br.readLine());
			if (end < nums[i]) {
				end = nums[i];
			}
		}
		
		int rst = Integer.MAX_VALUE;
		while (start <= end) {
			int mid = (start + end) / 2;
			
			int cnt = 0;
			for (int i = 0; i < nums.length; i++) {
				cnt += nums[i] % mid == 0 ? nums[i] / mid : nums[i] / mid + 1;
			}
			
			if (cnt <= n) {
				end = mid - 1;
				rst = Integer.min(rst, mid);
			}
			
			else {
				start = mid + 1;
			}
			
		}
		
		System.out.println(rst);
	}

}
