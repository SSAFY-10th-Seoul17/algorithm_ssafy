import java.io.*;
import java.util.*;

public class Main {
	private static final char[] PPAP = {'P','P','A','P'};
	private static String ans = "NP";
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] input = br.readLine().toCharArray();
		if(input.length==1&&input[0]=='P') { // 문자열이 1이고 P면 PPAP임
			ans  = "PPAP";
		}else if(input.length>=4) { // 문자열이 4미만일경우 PPAP아님
			go(input);
		}
		System.out.println(ans);
	} // end of main
	private static void go(char[] input) {
		ArrayDeque<Character> stack = new ArrayDeque<>();
		for (int inx = 0; inx < input.length; inx++) {
			stack.add(input[inx]);
			if(stack.size()>=4 && isPPAP(stack)) {// 맞으면 PPAP빼고 P넣기
					stack.add('P');
				}
			}
		if(stack.size()>4) return; // 마지막에 남은 문자열이 PPAP이거나 P이어야함
		if((stack.size()==4&&isPPAP(stack))||(stack.size()==1&&stack.peek()=='P')) {
			ans  = "PPAP";
		}
	}
	private static boolean isPPAP(ArrayDeque<Character> stack) {
		ArrayDeque<Character> tmp = new ArrayDeque<>();
		for (int inx = 3; inx >= 0; inx--) {
			tmp.add(stack.pollLast());
			if(tmp.peekLast()!=PPAP[inx]) {
				while(!tmp.isEmpty()) {
					stack.add(tmp.pollLast());
				}
				return false;
			}
		}
		return true;
	}
} // end of class
