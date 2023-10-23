package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MessiGimossi {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int m = Integer.parseInt(br.readLine());
		
		ArrayList<Integer> Messi = new ArrayList<>(); // Messi의 길이
		Messi.add(6); // "Messi "
		Messi.add(14);// "Gimossi "
		String str = " Messi Gimossi ";
		if(m <= 14) {
			if(str.charAt(m) == ' ') {
				System.out.println("Messi Messi Gimossi");
			}else {
				System.out.println(str.charAt(m));
			}
		}else {
			while(true) {
				if(Messi.get(Messi.size()-1) > m) break;
				
				Messi.add(Messi.get(Messi.size()-1) + Messi.get(Messi.size()-2));
			}
			m -= Messi.get(Messi.size()-2);
			int index = Messi.size()-3;
			// m은 Messi[index] 어딘가에 있다
			
			//Messi Gimmossi까지 뺀다
			while(index >= 2) {
				if(m < 14) break;
				
				if(m <= Messi.get(index) && m > Messi.get(index-1)) {
					m -= Messi.get(index-1);
					index-=2;
				}else if(m <= Messi.get(index-1)) {
					index-=1;
				}
			}
			
			
			if(str.charAt(m) == ' ') {
				System.out.println("Messi Messi Gimossi");
			}else {
				System.out.println(str.charAt(m));
			}
			
		}
		
	}
}
