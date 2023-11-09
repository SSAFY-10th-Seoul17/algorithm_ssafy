import java.io.*;
import java.util.*;

// 아이디어 : 완전탐색 + 이분탐색을 통한 인덱스 범위 찾기
// 10000 C 3 은 시간초과(N^3)
// => 그래서 조합으로 구하는 수의 개수를 최대한 줄이기 위해 두개의 숫자만 "완전탐색"을 통해 구하고 나머지 하나는 "이분탐색"으로 범위를 구해서 개수를 구한다(N^2 * logN)
// 1. 주어진 숫자 정렬
// 2. 이중반복문을 통해 겹치지 않는 숫자 2개 구하기
// 3. 위에서 구한 2개의 숫자와 더해서 합이 0이 되는 숫자의 인덱스 범위를 이분탐색을 통해 구하기(upperBound & lowerBound)
public class boj3151_합이0 {
	static int N;
	static int[] nums;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}

		// solution
		Arrays.sort(nums);
		long res = 0;
		for(int i=0; i<N; i++) {
			for(int j=i+1; j<N; j++) {
				int sum = nums[i] + nums[j];
				res += upperBound(j+1, N, -sum) - lowerBound(j+1, N, -sum);
			}
		}

		// output
		System.out.println(res);
	}

	static int lowerBound(int start, int end, int target) {
		while(start < end) {
			int mid = (start+end)/2;
			if(nums[mid] >= target) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		return start;
	}

	static int upperBound(int start, int end, int target) {
		while(start < end) {
			int mid = (start+end)/2;
			if(nums[mid] > target) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		return end;
	}
}
