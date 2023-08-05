import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1543 {
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String s1 = bf.readLine();
		String s2 = bf.readLine();

		int cnt = 0;
		int s2Len = s2.length();
		for (int i = 0; i <= s1.length() - s2Len; i++) {
			String s = s1.substring(i, i + s2Len);
			if (s.equals(s2)) {
				cnt++;
				i += s2Len - 1;
			}
		}
		System.out.println(cnt);
	}
}