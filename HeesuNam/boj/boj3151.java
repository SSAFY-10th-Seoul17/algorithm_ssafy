import java.io.*;
import java.util.*;

public class Main {
	private static int N;
	private static long ans;
	private static int[] students;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		students = new int[N];
		for (int i = 0; i < N; i++) {
			students[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(students);
		ans = 0;
		for (int i = 0; i < N && students[i] <= 0; i++) {
			grouping(students[i], i + 1, students.length - 1);
		}
		System.out.println(ans);
	} // end of main

	private static void grouping(int target, int left, int right) {
		while (left < right) {
			int sum = target + students[left] + students[right];
			if (sum == 0) {
				int l = 1;
				int r = 1;
				if (students[left] == students[right]) {
					ans += combi(right - left + 1);
					break;
				}
				while (left + 1 < right && students[left] == students[left + 1]) {
					l++;
					left++;
				}
				while (left < right - 1 && students[right] == students[right - 1]) {
					r++;
					right--;
				}
				ans += l * r;
			}
			if (sum < 0) {
				left++;
			} else {
				right--;
			}
		}
	}

	private static long combi(int n) {
		return n * (n - 1) / 2;
	}

} // end of class
