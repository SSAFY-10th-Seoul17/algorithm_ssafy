import java.io.BufferedReader;
import java.io.InputStreamReader;

public class boj1543 {
	public static String docu = "";
	public static String findWord = "";
	public static int count;
	
	public static boolean isSame(int idx) {
		for(int i = 0; i < findWord.length(); i++) {
			if(docu.charAt(idx + i) != findWord.charAt(i)) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		docu = bf.readLine();
		findWord = bf.readLine();

		int len = docu.length() - findWord.length() + 1;
		
		for(int i = 0; i < len; i++) {
			if(isSame(i)) {
				i += findWord.length() - 1;
				count++;
			}
		}
		
		System.out.println(count);
		
		// 시간 초과 코드
//		int findWordIdx = 0;
//		int sameLen = 0;
//		int findWordLen = findWord.length();
//		int docuLen = docu.length();
//		for(int i = 0; i < docuLen - findWordLen + 1; i++) {
//			if(docu.charAt(i) == findWord.charAt(findWordIdx)) {
//				if(findWordIdx == findWordLen - 1) {
//					count++;
//					findWordIdx = 0;
//					sameLen = 0;
//				} else {
//					sameLen++;
//					findWordIdx++;
//				}
//			} else {
//				i -= sameLen;
//				findWordIdx = 0;
//			}
//		}
		
	}
}
