import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int n, cnt;
	static String[] nums;
	static int[] strike, ball, guessNum;
	static ArrayList<int[]> possible;
	static boolean[] isSelected;
	
	// 서로 다른 숫자(1~9 중)로 만들 수 있는 모든 세 자리 수
	public static void makeNums(int idx) {
		if (idx == 3) {
			possible.add(Arrays.copyOf(guessNum, 3));
			return;
		}
		for (int i = 1; i < 10; i++) {
			if(isSelected[i]) continue;
			isSelected[i] = true;
			guessNum[idx] = i;
			makeNums(idx+1);
			isSelected[i] = false;
		}
	}
	
	// 번호와 정보가 일치하는지 검사 (검사할 질문한 수, 후보 수, 스트라이크 개수, 볼 개수)
	public static boolean guess(String num, int[] gn, int s, int b) {
		int guessS = 0;
		int guessB = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				int n = num.charAt(i) - '0';
				if (n == gn[j]) {
					if (i == j) guessS++;
					else guessB++;
				}
			}
		}
		if (guessS == s && guessB == b) return true;
		else return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 입력
		int n = Integer.parseInt(br.readLine());  // 질문 개수
		nums = new String[n];  // 질문한 수
		strike = new int[n];  // 스트라이크 개수
		ball = new int[n];  // 볼 개수
		possible = new ArrayList<int[]>();  // 모든 세 자리 수 후보
		isSelected = new boolean[10];  // 사용된 숫잔지 확인
		guessNum = new int[3];

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			nums[i] = st.nextToken();
			strike[i] = Integer.parseInt(st.nextToken());
			ball[i] = Integer.parseInt(st.nextToken());
			
		}
		
		makeNums(0);  // 가능한 모든 수 생성
		for (int i = 0; i < possible.size(); i++) {
			boolean flag = true;
			for (int j = 0; j < n; j++) {
				if(!guess(nums[j], possible.get(i), strike[j], ball[j])) {
					flag = false;
					break;
				}
			}
			if (flag) cnt++;
		}
		
		System.out.println(cnt);
	
		
	}

}
