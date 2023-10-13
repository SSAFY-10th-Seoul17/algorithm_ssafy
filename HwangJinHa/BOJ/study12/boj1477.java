import java.io.*;
import java.util.*;

// 휴게소 세우기 
public class Main {
	static int n, m, l;
	static int[] points;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.valueOf(st.nextToken());
		m = Integer.valueOf(st.nextToken());
		l = Integer.valueOf(st.nextToken());

		points = new int[n + 1];

		if (n != 0)
			st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) 
			points[i] = Integer.valueOf(st.nextToken());
		points[n] = l;
		Arrays.sort(points);
		
		int left = -1;
		int right = l;
		int mid;
		while (left + 1 != right) {
			mid = (left + right) / 2;
			if (check(mid)) {
				right = mid;
			} else {
				left = mid;
			}
		}
		
		System.out.println(right);
	}

	private static boolean check(int num) {
		int leftPoints = m;
		int lastPoint = 0;
		int max = 0;
		for (int i = 0 ; i < n+1; i++) {
			if (points[i] - lastPoint > num) {
				if (leftPoints > 0) {
					leftPoints--;
					lastPoint += num;
					i--;
					max = num;
				}
				else {
					return false;
				}
			}
			else {
				max = Math.max(max, points[i] - lastPoint);
				lastPoint = points[i];
			}
		}
		return true;
	}
}
