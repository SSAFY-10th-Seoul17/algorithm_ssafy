package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Objects;

//12919번
public class A와B2 {
	static boolean able = false;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		String t = br.readLine();

		sol(s, t);
		
		if(able) System.out.println(1);
		else System.out.println(0);
		
	}

	private static void sol(String s, String t) {
		if(able) return;
		if(s.length() == t.length()) {
//			System.out.println(s);
			if(Objects.equals(s, t)) {
				able = true;
			}
			return;
		}
		
		if(t.charAt(t.length()-1) == 'A') {
			sol(s, t.substring(0, t.length()-1));
		}

		if(t.charAt(0) == 'B') {
			String substr = t.substring(1);
			StringBuilder sb = new StringBuilder(substr);
			sb.reverse();
			sol(s, sb.toString());
		}

		
	}
	
}
