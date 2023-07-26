package myAlgo;

import java.util.*;


class Eratostenes {
	static final int MAX = 1000001;
	
	public int[] list = new int[MAX];
	
	public void initPrime() {
		for (int i = 2; i<MAX; i++) {
			this.list[i] = i;
		}
		
		for(int i = 2; i<Math.sqrt(MAX); i++) {
			for(int j = i+i; j < MAX; j+=i) {
				if (this.list[j] == 0){
					continue;
				}
				this.list[j] = 0;
			}
		}
		
	}
	
}


public class Boj17103 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Eratostenes et = new Eratostenes();
		et.initPrime();
		
		int N = sc.nextInt();
		
		
		for(int i = 0; i< N; i++) {
			int val = sc.nextInt();
			int curAns = 0;
			for(int j = 2; j < (int)(val/2)+1; j++) {
				
				if (et.list[j] != 0 && et.list[val-j] != 0) {
					curAns += 1;
				}
			}
			System.out.println(curAns);
		}
	}
}
