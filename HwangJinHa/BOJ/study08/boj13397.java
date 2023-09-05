import java.io.*;
import java.util.*;

// 구간 나누기 2
public class boj13397 {
	static int n, m;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		n = Integer.valueOf(st.nextToken());
		arr = new int[n + 1];
		arr[n] = 20000; // 끝내기에 사용할 절대 불가능한 값
		m = Integer.valueOf(st.nextToken());

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) 
			arr[i] = Integer.valueOf(st.nextToken());
		
		int l = -1;
		int r = findPossible(); // 가능한 값중 임의의 값
		int mid;
		while (l + 1 < r) { 
			mid = (l+r) / 2;
			if (check(mid, 0, 0)) {
				r = mid;				
			}
			else {
				l = mid;
			}
		}
		System.out.println(r);
	}

	private static boolean check(int mid, int idx, int depth) {
		int lowest = 10001;
		int highest = 0;
		int size = n - (m - depth);
		int i;
		for (i = idx; i <= size; i++) {
			if (arr[i] < lowest)
				lowest = arr[i];
			if (arr[i] > highest)
				highest = arr[i];
			// 안되는 데에서 끊는다.
			if (highest - lowest > mid)
				break;
		}
		i-=1;
		if (i == n-1) {
			if (depth == m-1)
				return true;
			else
				return false;
		}
		else {
			if (depth == m-1)
				return false;
			else
				return check(mid, i+1, depth + 1);
		}
	}

	private static int findPossible() {
		int size = n - m + 1;
		int lowest = 10001;
		int highest = 0;
		for (int i = 0; i < size; i++) {
			if (arr[i] < lowest)
				lowest = arr[i];
			if (arr[i] > highest)
				highest = arr[i];
		}
		return highest - lowest;
	}
}
