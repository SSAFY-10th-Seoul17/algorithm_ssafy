import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	private static int M;
	private static int N;
	private static int[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		int maxVal = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			if (arr[i] > maxVal) {
				maxVal = arr[i];
			}
		}
		System.out.println(binarySearch(0, maxVal));

	} // end of main

	private static int binarySearch(int left, int right) {
		int res = 0;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (chk(mid) <= M) {
				res = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return res;
	}

	private static int chk(int mid) {
		int min = arr[0];
		int max = arr[0];
		int divide = 1;
		for (int i = 0; i < N; i++) {
			if (min > arr[i]) {
				min = arr[i];
			}
			if (max < arr[i]) {
				max = arr[i];
			}
			if ((max - min) > mid) { // 구간 나누기
				divide++;
				max = arr[i];
				min = arr[i];
				i--;
			}
		}
		return divide;
	}

} // end of class
