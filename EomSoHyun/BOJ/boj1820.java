import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());  // 테스트 케이스 개수 
		for (int t = 1; t <= T; t++) {
			String paper = br.readLine();
			if (simulation(0, paper.length()-1, paper)) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
			
		}
		
	}
	
	static boolean simulation(int x, int y, String paper) {
		if (x == y) {
			return true;
		}
		
		int nx = x;
		int ny = y;
		while (nx != ny) {
			if (paper.charAt(nx) == paper.charAt(ny)) {
				return false;
			}
			nx++;
			ny--;
		}
		
		if (simulation(x, y/2-1, paper)) {
			return true;
		} else {
			return false;			
		}
		
	}

}
