import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1802 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < T; i++) {
			String s = br.readLine();
			if (s.length() == 1) {
				sb.append("YES\n");
			} else if (check(s, 0, s.length()-1)) {
				sb.append("YES\n");
			} else {
				sb.append("NO\n");
			}
		}
		System.out.println(sb.toString());
	}
	
	public static boolean check(String s, int start, int end) {
		if (end - start == 2) {
			return s.charAt(start) != s.charAt(end);
		}
		int mid = (start + end) / 2;
		int left = mid - 1;
		int right = mid + 1;
		while (left >= start && right <= end) {
			if (s.charAt(left) == s.charAt(right)) {
				return false;
			}
			left--;
			right++;
		}
		return check(s, start, mid-1);
	}
}
