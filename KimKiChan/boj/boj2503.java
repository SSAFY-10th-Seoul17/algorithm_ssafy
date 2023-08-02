import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj2503 {
	static int[] answ = new int[1000];
	static int maxCount = 0;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		

		int question = Integer.parseInt(br.readLine());
		int[] num = new int[3];
		int n = 0;
		int strike = 0;
		int foul = 0;
		String str = "";
		
		for(int t = 0; t < question; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			
			n = Integer.parseInt(st.nextToken());
			strike = Integer.parseInt(st.nextToken());
			foul = Integer.parseInt(st.nextToken());
			
			for(int i = 2; i >= 0; i--) {
				num[i] = n%10;
				n /= 10;
			}

			findNum(num, strike, foul);

		}
		int result = 0;
		for(int i = 123; i < answ.length; i++) {
			if(answ[i] == question) {
				result++;
			}
		}
        if(maxCount == 0) {
			result = 0;
		}
		System.out.println(result);
		
		
	}
	
	static public void findNum(int[] num, int strike, int foul) {
		int cntStrike;
		int cntFoul;
		for(int i = 123; i <= 987; i++) { // 가능성 있는 숫자 찾기
			cntStrike = 0;
			cntFoul = 0;
			if(checkNum(i)) {
				cntStrike = countStrike(num, i, cntStrike);	//스트라이크 찾기
				cntFoul = countFoul(num, i, cntFoul);		//볼 찾기
				if(cntStrike == strike && cntFoul == foul) {
					answ[i]++;
					if(maxCount < answ[i]) {
						maxCount = answ[i];
					}
				}
			}
		}
	}
	static public int countFoul(int[] num, int n, int cntFoul) {
		if(num[0] == (n/10)%10 ||  num[0] == n%10) {
			cntFoul++;
		}
		if(num[1] == (n/100)%10 ||  num[1] == n%10) {
			cntFoul++;
		}
		if(num[2] == (n/100)%10 ||  num[2] == (n/10)%10) {
			cntFoul++;
		}
		
		return cntFoul;
	}
	
	
	static public int countStrike(int[] num, int n, int cntStrike) {
		if(num[0] == (n/100)%10) { // 백의자리 수 일치
			cntStrike++;
		}
		if(num[1] == (n/10)%10) { // 십의 자리 수 일치
			cntStrike++;
		}
		if(num[2] == n%10) { // 일의 자리 수 일치
			cntStrike++;
		}
		return cntStrike;
	}
	
	static public boolean checkNum(int n) {
		int hundred = (n/100)%10;
		int ten = (n/10) %10;
		int one = n % 10;
		if(hundred != 0 && ten != 0 && one != 0) {
			if(hundred!=ten && ten!=one && one!=hundred) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
		
	}
	
}
