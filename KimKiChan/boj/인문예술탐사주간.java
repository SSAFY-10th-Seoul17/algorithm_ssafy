import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//23829번
public class 인문예술탐사주간 {
	private static long[] tree;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());
		
		tree = new long[n+1];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i <= n; i++) {
			tree[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(tree);
		
		long[] treeSum = new long[n+1];
		for(int i = 1; i <= n; i++) {
			if(i==1) treeSum[i] = tree[i];
			else treeSum[i] = treeSum[i-1] + tree[i];
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < q; i++) {
			long target = Long.parseLong(br.readLine());
			//이분탐색으로 위치 찾기
			int index = binarySearch(target);
			long rightSum = 0;
			long leftSum = 0;
			
			if(tree[index] <= target) {
				leftSum = index * target - treeSum[index];
				rightSum = (treeSum[n] - treeSum[index]) - (n-index) * target;
			}else {
				leftSum = (index-1) * target - treeSum[index-1];
				rightSum = (treeSum[n] - treeSum[index-1]) - (n-index+1) * target;
			}
			long sum = rightSum + leftSum;
			sb.append(sum).append("\n");
		}
		System.out.println(sb.toString());
		
	}

	private static int binarySearch(long target) {
		int left = 0;
		int right = tree.length-1;
		int mid = 0;
		
		while(left <= right) {
			mid = (left+right)/2;
			if(tree[mid] < target) {
				left = mid+1;
			}else if(tree[mid] > target) {
				right = mid-1; 
			}else {
				break;
			}
		}
		
		return mid;
	}
}