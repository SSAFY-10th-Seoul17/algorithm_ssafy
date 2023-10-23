import java.io.*;
import java.util.*;

public class boj12919_A와B2 {
	static String S, T;
	static final int ADD_METHOD = 1, REVERSE_METHOD = 2;
	static boolean done = false, possible = false;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		S = br.readLine();
		T = br.readLine();

		// solution
		bruteforce(T);

		// output
		System.out.println(possible ? 1 : 0);
	}

	// 아이디어 : T -> S 를 만들어주는 재귀함수를 반복해서 S 만큼의 길이가 되었을 때, S 와 동일한 문자열을 만들 수 있는지 살펴본
	static void bruteforce(String str) {
		// 기저조건 : T 를 계속 잘라서(규칙에 따라) S 만큼의 길이가 된 경우
		if(str.length() == S.length()) {
			if(str.equals(S)) { // T 를 자른 문자열이 S 가 되었다면 => 1반환
				possible = true;
				done = true;
			}
			return;
		}

		if(done) return; // 이미 가능함을 확인하여 더이상 탐색이 필요 X

		// 두 가지 규칙에 따라 문자열을 자른다
		// 두 가지 규칙을 적용시키기 위해 각각 다른 방법을 파라미터로 재귀호출을 한다
		// 이때, 첫번째 규칙을 적용하려면 가장 뒷글자가 A 여야하고, 두번째 규칙을 적용하려면 가장 첫번째 글자가 B 여야만 한다
		if(str.charAt(str.length()-1) == 'A') {
			bruteforce(add(str));
		}
		if(str.charAt(0) == 'B') {
			bruteforce(reverse(str));
		}
	}

	// 문자열의 뒤에 A 를 추가한다 <-> 문자열의 뒤에서 A 를 제거한다
	static String add(String str) {
		return str.substring(0, str.length()-1);
	}

	// 문자열의 뒤에 B를 추가하고 문자열을 뒤집는다 <-> 문자열의 앞에서 B 를 제거하고 문자열을 뒤집는다.
	static String reverse(String str) {
		StringBuilder sb = new StringBuilder(str.substring(1));
		sb.reverse();
		return sb.toString();
	}
}
