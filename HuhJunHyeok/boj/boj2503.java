import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * [boj] 2503. 숫자 야구
 */
public class boj2503 {
	static int n; // 질문 횟수
	static String[] numArr; // 물어본 수의 배열.
	static int[][] strikeBall; // 스트라이크와 볼의 배열
//	static StringBuilder sb = new StringBuilder(); // 단순 값 하나 출력이라 미사용.
	static boolean[] check = new boolean[865]; // 123 ~ 987. true -> 제외된 수. false -> 후보인 수
	static int checkDiff = 123; // 123 - 0. boolean[] check의 idx값과 실제 값의 차이
	static int possibleNumCnt;
	static boolean findAns;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		numArr = new String[n];
		strikeBall = new int[n][2];
		
		checkInitialize();
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			numArr[i] = st.nextToken();
			for(int j = 0; j < 2; j++) {
				strikeBall[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0; i < n; i++) {
			if(strikeBall[i][0] == 3) {
				findAns = true;
				break;
			}
			
			for(int j = 0; j < 865; j++) {
				if(!check[j]) {
					int realNum = j + checkDiff;
					String checkNumStr = String.valueOf(realNum);
					if(strikeBall[i][0] != countStrike(checkNumStr, numArr[i]) || strikeBall[i][1] != countBall(checkNumStr, numArr[i])) {
						check[j] = true;
					}					
				}
			}
			
		}
		
		if(findAns) {
			System.out.println(1);
		} else {
			for(int i = 0; i < 865; i++) {
				if(!check[i]) {
					possibleNumCnt++;
				}
			}
			System.out.println(possibleNumCnt);
		}
	}
	
	public static void checkInitialize() {
		for(int i = 0; i < 865; i++) {
			String numStr = String.valueOf(i + checkDiff);
			if(!numStr.contains("0") && (numStr.charAt(0) != numStr.charAt(1) && numStr.charAt(0) != numStr.charAt(2) && numStr.charAt(1) != numStr.charAt(2))) {
				continue;
			}
			check[i] = true;
		}
	}
	
	public static int countStrike(String origin, String compare) {
		int count = 0;
		for(int i = 0; i < 3; i++) {
			if(origin.charAt(i) == compare.charAt(i)) {
				count++;
			}
		}
		return count;
	}
	
	public static int countBall(String origin, String compare) {
		int count = 0;
		for(int i = 0; i < 3; i++) {
			if(origin.contains(Character.toString(compare.charAt(i))) && origin.charAt(i) != compare.charAt(i)) {
				count++;
			}
		}
		return count;
	}
}
