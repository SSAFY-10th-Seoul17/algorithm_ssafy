package java_boj;

import java.util.*;

class Baseball {
	
	static LinkedList<String> curNum = new LinkedList<>();
	static LinkedList<String> ans = new LinkedList<>();
	
	static {
		for(int i = 1; i<=9; i++) {
			for(int j = 1; j <= 9; j++) {
				for(int k = 1; k <= 9; k++) {
					if(i == j || j == k || i == k) continue;
					String s = i+"" + j + "" + k + "";
					curNum.add(s);
				}
			}
		}
		
	}
	
	static void check(String compS, int strike, int ball) {
		
		int i = 0;
		
		while (i < curNum.size()) {
			String s = curNum.get(i);
			int st_count = 0;
			int ball_count = 0;
			for(int j = 0; j<3; j++) {
				for(int k = 0; k < 3; k++) {
					if(compS.charAt(j) == s.charAt(k) && j == k) {
						st_count++;
						continue;
					}
					else if(compS.charAt(j) == s.charAt(k) && j != k) {
						ball_count++;
						continue;
					}
				}
			}
			if (st_count != strike || ball_count != ball) {
				curNum.remove(i);
				i -= 1;
			}
			i += 1;
		}
		ans = curNum;
	}
}

public class boj2503 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		sc.nextLine();
		
		for(int i = 0; i<n; i++) {
			String s = sc.nextLine();
			if (Integer.parseInt(s.split(" ")[1]) == 3) {
				break;
			}
			Baseball.check(s.split(" ")[0], Integer.parseInt(s.split(" ")[1])
					, Integer.parseInt(s.split(" ")[2]));
		}
		System.out.println(Baseball.ans.size());		
		
	}
}
