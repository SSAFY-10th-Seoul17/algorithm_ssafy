package java_boj;

import java.util.*;


public class boj2164 {
	static LinkedList<String> cardl = new LinkedList<>();
	static Queue<String> cardq = new LinkedList<>();
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i = 1; i <= n; i++ ) {
			cardq.add(i + "");
		}
		
		bruteForce();
		System.out.println(cardq.peek());
	}
	private static void bruteForce() {
//		while (cardl.size() > 1) {
//			cardl.removeFirst();
//			String s = cardl.getFirst();
//			cardl.addLast(s);
//			cardl.removeFirst();
//		}
		while (cardq.size() > 1) {
			cardq.remove();
			cardq.add(cardq.peek());
			cardq.remove();
		}
		
		return;
	}
}
