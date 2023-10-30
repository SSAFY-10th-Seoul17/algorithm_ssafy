package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
//16120번
public class PPAP {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		boolean PPAP = true;
		//p의 개수
		//a는 2개의 p 이후에 나타난다.
		int countP = 0;
		for(int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if(c=='P') {
				countP++;
			}else if(c=='A' && countP >= 2)  {
				if(i<str.length()-1 && str.charAt(i+1) == 'P') {
					countP-=2;
				}else {
					PPAP = false;
					break;
				}
			}else {
				PPAP = false;
				break;
			}
			
		}
		if(PPAP && countP==1) System.out.println("PPAP");
		else System.out.println("NP");
		
	}

}
