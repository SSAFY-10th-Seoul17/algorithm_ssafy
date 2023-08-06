import java.io.*;
import java.util.*;

public class boj10972 {
	static boolean isLast = false;
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

		// solution : 다음순열
		int[] results = nextPermutation(nums);
		for(int result : results) {
			sb.append(result+" ");
		}
		
		// output
		if(isLast) {
			bw.write("-1");
		} else {
			bw.write(sb.toString());
		}
		bw.flush();
		bw.close();
	}

	/**
	 * 다음순열 :
	 * 1. 오른쪽 -> 왼쪽 : 왼쪽 인덱스가 오른쪽 보다 더 큰 시점의 숫자 num[i] > num[i-1] -> num[i-1]
	 * 2. 오른쪽 -> 찾은 인덱스-1 : 처음으로 발견된 찾은 숫자보다 더 큰 숫자
	 * 3. 찾은 두 숫자 swap
	 * 4. 바꾼 숫자의 오른쪽 전부 reverse
	 *
	 * 순열 특성 :
	 * - 모두 오름차순 : 1st 순열
	 * - 모드 내림차순 : 마지막 순열
	 */
	static int[] nextPermutation(int[] nums) {
		// 왼쪽으로 확인하며 처음으로 발견하는 작아지는 숫자
		int idx = nums.length-1;
		while(idx > 0 && nums[idx-1] > nums[idx]) { // 크면(오름차순) 그냥 계속 넘김
			idx--;
		}

		if(idx == 0) { // 전체가 다 오르차순(끝에서부터)이라면 마지막 순열
			isLast = true;
			return nums;
		}

		// 왼쪽으로 확인하면서 처음으로 발견하는 아까 발견한 숫자보다 큰 숫자
		int nIdx = nums.length-1;
		while(nums[idx-1] > nums[nIdx]) { // 발견한 숫자보다 작으면 계속 넘김
			nIdx--;
		}

		// 찾은 두 수 swap
		int temp = nums[idx-1];
		nums[idx-1] = nums[nIdx];
		nums[nIdx] = temp;

		// 뒤에서부터 오름차순인 인덱스까지의 수 reverse
		nIdx = nums.length-1;
		while(idx < nIdx) {
			temp = nums[nIdx];
			nums[nIdx] = nums[idx];
			nums[idx] = temp;
			nIdx--; idx++;
		}

		return nums;
	}
}

