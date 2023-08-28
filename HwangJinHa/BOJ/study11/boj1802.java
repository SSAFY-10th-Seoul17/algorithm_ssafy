package study8_5;

import java.io.*;

// 종이 접기 
public class boj1802 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		
		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			System.out.println((check(s))? "YES" : "NO");
		}
	}

	static boolean check(String s) {
		if (s.length() == 1)
			return true;
		int m = s.length() / 2;
		for (int i = 0; i < m; i++) {
			if (s.charAt(i) == s.charAt(s.length() - 1 - i)) {
				return false;
			}
		}
		return check(s.substring(0, m)) && check(s.substring(m+1, s.length()));
	}
}
