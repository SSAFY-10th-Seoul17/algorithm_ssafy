import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class boj1174 {
	private static ArrayList<Long> li;
	private static int[] arr = {0,1,2,3,4,5,6,7,8,9};
	private static int N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		li = new ArrayList<Long>();
		if (N > 1023) {
			System.out.println(-1);
			return;
		}
		combination(0,9);
		Collections.sort(li);
		System.out.println(li.get(N-1));
	}
	private static void combination(long cur, int i) {
		if (!li.contains(cur)) li.add(cur);
		if (i == -1) return;
		combination(cur*10+arr[i],i-1);	// 선택
		combination(cur, i-1);			// 비선택
	}
}