import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 먹을것인가먹힐것인가{
	private static int[] a;
	private static int[] b;
	private static int count;

	// A의 원소 > B의 원소
	// 숫자가 같으면 count하지 않는다.
	// B 정렬 후 A의 원소 이분탐색
	// 마지막으로 탐색한 위치 기록
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int test = Integer.parseInt(br.readLine());
		
		for(int t = 1; t <= test; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			a = new int[n];
			b = new int[m];
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < n; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < m; i++) {
				b[i] = Integer.parseInt(st.nextToken());
			}
			
			// 이분 탐색 전 정렬
			Arrays.sort(a);
			Arrays.sort(b);

			count = 0;

			for(int i = 0; i < n; i++) {
				if(a[i] <= b[0]) continue;
				if(a[i] > b[m-1]) {
					count += m;
					continue;
				}		
				count += binarySearch(0, m-1, a[i]);
			}
			
			sb.append(count).append("\n");
			
		}// end of test
		System.out.println(sb.toString());
		
	}// end of main

	private static int binarySearch(int start, int end, int key) {
		if(start > end) { // 위치 반환
			// 가장 작은 곳의 위치 반환
			return start;
		}else {
			int mid = (start + end) / 2;

			if(b[mid] < key) {
				return binarySearch(mid+1, end, key);
			}
			else if(b[mid] >= key) {
				return binarySearch(start, mid-1, key);
			}
		}
		return 0;	
	}

}// end of class
