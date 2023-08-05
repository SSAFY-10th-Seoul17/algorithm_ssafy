import java.io.*;
import java.lang.*;
import java.util.*;

public class boj10422 {
	static int n;
	static int count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// input
		int t = Integer.parseInt(br.readLine());
		for(int i=0; i<t; i++) {
			n = Integer.parseInt(br.readLine());
			count = 0;

			// solution
			getString(1, "(");
			bw.write(count%1000000007+"\n");
		}

		// output
		bw.flush();
		bw.close();
	}

	// 문자열 만들기 메서드 + 확인
	static void getString(int index, String str) {
		if(index == n) {
			if(isPossible(str)) {
				count++;
			}
			return;
		}
		getString(index+1, str.concat("("));
		getString(index+1, str.concat(")"));
	}

	// 올바른 문자열인지 확인하는 메서드
	static boolean isPossible(String str) {
		Stack<Character> s = new Stack<>();
		for(int i=0; i<str.length(); i++) {
			char c = str.charAt(i);
			if(c == '(') {
				s.push('(');
			} else {
				if(s.isEmpty()) {
					return false;
				} else {
					s.pop();
				}
			}
		}
		if(!s.isEmpty()) {
			return false;
		}
		return true;
	}
}
