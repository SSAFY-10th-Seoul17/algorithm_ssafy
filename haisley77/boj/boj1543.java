import java.util.*;

public class boj1543 {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		String docs = sc.nextLine();
		String word = sc.nextLine();
		String res = docs.replace(word, "");
		System.out.println((docs.length()-res.length())/word.length());
	}
}
