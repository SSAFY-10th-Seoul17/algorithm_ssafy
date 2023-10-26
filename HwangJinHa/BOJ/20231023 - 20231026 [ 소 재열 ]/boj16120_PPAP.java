package study10월4주차목;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class boj16120_PPAP {
    static Stack<Character> stack = new Stack<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (char c : br.readLine().toCharArray()) {
            stack.add(c);
            while (isLastPPAP()) {
                stack.pop();
                stack.pop();
                stack.pop();
                stack.pop();
                stack.push('P');
            }
        }
        if (stack.size() == 1 && stack.peek() == 'P')
            System.out.println("PPAP");
        else
            System.out.println("NP");
    }
    static boolean isLastPPAP() {
        int size = stack.size();
        if (size < 4)
            return false;
        if (stack.elementAt(size - 4) == 'P' &&
            stack.elementAt(size - 3) == 'P' &&
            stack.elementAt(size - 2) == 'A' &&
            stack.elementAt(size - 1) == 'P' ) {
            return true;
        }
        return false;
    }
}