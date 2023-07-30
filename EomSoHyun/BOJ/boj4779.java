import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = "";
		int n;
		
		while (sc.hasNext()) {
			n = sc.nextInt();
			for (int i=0; i < n+1; i++) {
				if (i == 0) {
					str = "-";
				}
				else {
					str = str + " ".repeat(str.length()) + str;
				}
			}
			System.out.println(str);
		}

	}

}
