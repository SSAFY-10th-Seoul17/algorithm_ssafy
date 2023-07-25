import java.util.Scanner;

public class boj1543 {
	static String docs = "";
	static String sub = "";
	
	static boolean check(int idx) {
//		System.out.println(idx + " 부터 검");
		for (int i = 0; i < sub.length(); i++) {
//			System.out.println(docs.charAt(idx + i) + " " + sub.charAt(i));
			if (docs.charAt(idx + i) != sub.charAt(i)) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		docs = sc.nextLine();
		sub = sc.nextLine();
		
		int end = docs.length() - sub.length();
		
		int cnt = 0;
		for (int i = 0; i < end + 1; i++) {
			if (check(i)) {
				cnt++;
				i += sub.length() - 1;
			}
		}
		System.out.println(cnt);
	}
}
