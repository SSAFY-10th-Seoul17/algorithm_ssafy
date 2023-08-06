import java.io.*;
import java.util.*;

public class boj10973 {
	static boolean isFirst = false;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		// input
		int n = Integer.parseInt(br.readLine());
		int[] nums = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		// solution : 이전순열(다음순열에서 부등호 바꿈)
		int[] results = prevPermutation(nums);
		for(int result : results) {
			sb.append(result+" ");
		}

		// output
		if(isFirst) {
			bw.write("-1");
		} else {
			bw.write(sb.toString());
		}
		bw.flush();
		bw.close();
	}

	static int[] prevPermutation(int[] nums) {
		int idx = nums.length-1;
		while(idx > 0 && nums[idx] > nums[idx-1]) { // 부등호 수정
			idx--;
		}

		if(idx == 0) {
			isFirst = true;
			return nums;
		}

		int nIdx = nums.length-1;
		while(nums[nIdx] > nums[idx-1]) { // 부등호 수정
			nIdx--;
		}

		int temp = nums[idx-1];
		nums[idx-1] = nums[nIdx];
		nums[nIdx] = temp;

		nIdx = nums.length-1;
		while(idx < nIdx) {
			temp = nums[idx];
			nums[idx] = nums[nIdx];
			nums[nIdx] = temp;
			nIdx--; idx++;
		}

		return nums;
	}
}
