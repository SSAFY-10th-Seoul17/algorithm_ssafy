import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static ArrayList<Long> ans = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if(N<=10) {
			System.out.println(N-1);
		}else {
			for (int i = 0; i < 10; i++) {
				makeSmallerNum(1, i);
			}
			Collections.sort(ans);
			if(ans.size()<N) System.out.println(-1);
			else System.out.println(ans.get(N-1));
		}
		
	}
	private static void makeSmallerNum(int inx, long num) {
		if(inx>10)return;

		ans.add(num);
		for (int i = 0; i < num%10; i++) {
			makeSmallerNum(inx+1, num*10 + i);
		}
	}
}
