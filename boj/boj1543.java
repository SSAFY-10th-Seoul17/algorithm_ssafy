package myAlgo;

import java.util.*;

public class Boj1543{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		String comp = sc.nextLine();
		
		
		System.out.println(compDup(s, comp));
	}
	
	private static int compDup(String s1, String s2) {
		
		int ans = 0;
		
		int i = 0;
		while (i < s1.length()) {
			if (i+s2.length() > s1.length()) {
				break;
			}
			if (s1.substring(i, i+s2.length()).equals(s2)) {
				ans += 1;
				i += s2.length();
			} else {
				i++;
			}
			
		}
		
		return ans;
	}
}


