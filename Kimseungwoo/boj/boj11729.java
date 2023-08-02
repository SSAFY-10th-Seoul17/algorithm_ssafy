package java_boj;

import java.util.*;

class Hanoi {
	
	static StringBuilder sb = new StringBuilder();
	
	public static void hanoi(int seq, int from, int to, int via) {
//		total = total.add(BigInteger.ONE);
		if(seq == 1) {
			move(from, to);
			return;
		}
		hanoi(seq-1, from, via, to);
		move(from, to);
		hanoi(seq-1, via, to, from);
		
	}
	
	public static void move(int from, int to) {
		sb.append(from + " " + to + "\n");
	}
}

public class boj11729 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		Hanoi.hanoi(n, 1, 3, 2);
		int k = 1;
		for(int i = 0; i<n; i++) {
			k *= 2;
		}
		System.out.println(k-1);
		System.out.println(Hanoi.sb.toString());
//		Hanoi.sb = Hanoi.sb.delete(Hanoi.sb.length()-2, Hanoi.sb.length());
//		System.out.println("hi");
	}
}
