import java.io.*;
import java.util.*;

public class boj16719 {
	static char[] chars;
	static boolean[] selected;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		String input = br.readLine();
		chars = input.toCharArray();

		// solution
		selected = new boolean[chars.length];
		construct(0, chars.length);

		// output
		System.out.println(sb.toString());
	}

	static void construct(int start, int end) {
		// 기저조건
		if(start == end) return;

		// 주어진 범위에서 사전순으로 가장 빠른 문자 찾기
		int idx = 0;
		char min = 'Z' + 1;
		for(int i=start; i<end; i++) {
			if(min <= chars[i]) continue;
			idx = i;
			min = chars[i];
		}

		// 찾은 문자를 추가하여 문자열 생성하기
		selected[idx] = true;
		for(int i=0; i<chars.length; i++) {
			if(!selected[i]) continue;
			sb.append(chars[i]);
		}
		sb.append("\n");

		// 재귀호출
		construct(idx+1, end); // 얘가 무조건 위에 와야 함
		construct(start, idx);
	}
}

