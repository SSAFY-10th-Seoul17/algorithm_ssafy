import java.io.*;
import java.util.*;

public class boj16120_PPAP {
	static final String TARGET = "PPAP";
	static Stack<Character> stack = new Stack<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		String comp = br.readLine();

		// solution
		Deque<Character> queue = new ArrayDeque<>();
		for(int i=0; i<comp.length(); i++) queue.offerLast(comp.charAt(i));
		boolean possible = DESTRUCT(comp);

		// output
		System.out.println(possible ? "PPAP" : "NP");
	}

	static boolean DESTRUCT(String comp) {
		int idx = 0;
		while(idx != comp.length()) {
			stack.push(comp.charAt(idx++));
			if(stack.size() >= 4) {
				checkAndAdd();
			}
		}
		if((stack.size() == 1 && stack.peek() == 'P') || (stack.size() == 4 && checkAndAdd())) return true;
		return false;
	}

	static boolean checkAndAdd() {
		char fth = stack.pop();
		char thr = stack.pop();
		char sec = stack.pop();
		char fir = stack.pop();
		if(fir != 'P' || sec != 'P' || thr != 'A' || fth != 'P') {
			stack.push(fir);
			stack.push(sec);
			stack.push(thr);
			stack.push(fth);
			return false;
		} else {
			stack.push('P');
			return true;
		}
	}
}
