import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj1436{
	static boolean check(int num) {
		int cnt = 0;
		while (num > 0) {
			if (num % 10 == 6)
				cnt++;
			else
				cnt = 0;
			if (cnt == 3)
				return true;
			num /= 10;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.valueOf(br.readLine());
		int num = 665;
		while (n > 0) {
			num++;
//			System.out.println("check(" + num + ") -> " + check(num));
			if (check(num))
				n--;
		}
		System.out.println(num);
	}
}
