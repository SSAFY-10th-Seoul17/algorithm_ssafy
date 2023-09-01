import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//1802번
public class 종이접기 {
	private static String str;

	// 중간 제외 
	// 양끝 다른 수(합이 1) YES
	// 양끝 같은 수 NO
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb= new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		for(int i = 0; i < t; i++) {
			str = br.readLine();
			
			if(origami(0, str.length()-1)) {
				sb.append("YES\n");
			}else {
				sb.append("NO\n");
			}
			
		}
		
		System.out.println(sb);
		
	}// end of main

	private static boolean origami(int start, int end) {
		if(end <= start) {
			return true;
		}
		
		int mid = (start + end)/2;
		for(int i = start; i < mid; i++) {
			if(str.charAt(i) == str.charAt(end - i)) {
				return false;
			}
		}
		
		return origami(start, mid-1) && origami(mid+1, end);
	}// end of origami
	
}// end of class
