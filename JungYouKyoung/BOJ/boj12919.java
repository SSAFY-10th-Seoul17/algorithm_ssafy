import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static String t;
	private static String sb;
	static boolean flag = false;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = br.readLine();
		t = br.readLine();
		
		solved(t);
		System.out.println((flag)?1:0);
		
	}//end main

	private static void solved(String t) { //t->s
		StringBuilder answer = new StringBuilder();
		answer.append(t);
		
		//기저조건
		if(answer.toString().equals(sb.toString())) {
			flag = true;
			return;
		}
		
		if(answer.length() <= sb.length()) {
			return;
		}
		
		//문자열 맨앞에 B가 있으면 B를 삭제할 수 있음 BAB -> BA, BA -> A
		//맨앞에 B가 있으면 reverse() 후 맨뒤 B 삭제
		if(answer.charAt(0) == 'B') {
			StringBuilder sub = new StringBuilder();
			sub.append(answer);
			sub.reverse();
			solved(sub.substring(0,sub.length()-1));
		}
		
		//문자열의 맨 뒤에 A가 있으면 A를 삭제할 수 있음
		if(answer.charAt(answer.length()-1) == 'A') {
			StringBuilder sub = new StringBuilder();
			sub.append(answer);
			solved(sub.substring(0, sub.length()-1));
		}

		
		
	}

}//end class
