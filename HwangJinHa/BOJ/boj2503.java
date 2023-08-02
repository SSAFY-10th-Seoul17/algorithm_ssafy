import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj2503 {
	
	static String[] candStr;
	static int[][] candNum;
	
	static boolean check(String s, int idx) {
		String comp = candStr[idx];
		int[] state = candNum[idx].clone();
		
		for(int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (s.charAt(i) == comp.charAt(j)) {
					if (i == j) {
						state[0]--;
						break;
					}
					else {
						state[1]--;
						break;
					}
				}
			}
		}
		return state[0] == 0 && state[1] == 0;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.valueOf(br.readLine());
		candStr = new String[n];
		candNum = new int[n][2];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			candStr[i] = st.nextToken();
			candNum[i] = new int[] {Integer.valueOf(st.nextToken()), Integer.valueOf(st.nextToken())};
		}
		
		HashSet<Integer> checkSet;
		
		int cnt = 0;
		for (int num = 111; num <= 999; num++) {
			checkSet = new HashSet<>();
			int tmp = num;
			while(tmp > 0) {
				checkSet.add(tmp % 10);
				tmp /= 10;
			}
			if (checkSet.size() != 3 || checkSet.contains(0))
				continue;
		
			String s = String.valueOf(num);
			boolean isPossible = true;
			for (int i = 0; i < n; i++) {
				if (!check(s, i)) {
					isPossible = false;
					break;
				}
			}
			cnt = (isPossible)? cnt + 1 : cnt;
		}
		System.out.println(cnt);
	}
}
