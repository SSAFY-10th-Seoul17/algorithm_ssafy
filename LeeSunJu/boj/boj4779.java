import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj4779 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = "";
		String s = "";
		int N = 0;
		while ((input = br.readLine()) != null) {
			N = Integer.parseInt(input);
			s = "-".repeat((int) Math.pow(3, N)); // "-"를 3의 n승만큼 채워주기
			System.out.println(solve(s));
		}
	}

	public static String solve(String s) {
		if (s.length() == 1) {
			return "-";
		}

		int q = s.length() / 3; // "-" or " "이 들어갈 개수
		String st = "";

		st += solve(s.substring(0, q));
		st += " ".repeat(q);
		st += solve(s.substring(q * 2));

		return st;
	}
}
