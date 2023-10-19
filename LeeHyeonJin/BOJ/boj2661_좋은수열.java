import java.io.*;
import java.util.*;

public class boj2661_좋은수열 {
	static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		N = Integer.parseInt(br.readLine());

		// solution & output
		backtrack("");
	}

	/**
	 * 파라미터로 받은 숫자(이전까지 만들어진 숫자)에 새로운 숫자를 더할건데,
	 * 이때 이전의 숫자와 동일한 숫자는 제외
	 */
	static void backtrack(String num) {
		// 기저조건 : 주어진 자릿수(N)만큼의 숫자길이를 완성하면 반환
		if(num.length() == N) {
			System.out.println(num);
			System.exit(0);
		}

		// 풀이 :
		// 이전까지 만든 숫자와 중복되는지 확인(길이만큼 : 1자리 비교, 2자리 비교, ... 길이/2 자리 비교)
		// 중복되지 않으면 다음 숫자 만들러 감(재귀호출)
		for(int i=1; i<=3; i++) {
			if(check(num+i)) backtrack(num+i);
		}
	}

	static boolean check(String num) {
		if(num.length() == 1) return true;
		int len = num.length();
		for(int i=1; i<=len/2; i++) { // 1자리수 비교, 2자리수 비교, ... N/2자리수 비교
			boolean duplicated = true;
			for(int j=0; j<i; j++) {
				if(num.charAt(len-2*i+j) != num.charAt(len-i+j)) {
					duplicated = false;
				}
			}
			if(duplicated) return false;
		}
		return true;
	}
}
