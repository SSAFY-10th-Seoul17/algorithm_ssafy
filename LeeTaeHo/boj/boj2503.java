import java.io.*;
import java.util.*;

public class boj2503 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int result = 0;
		boolean[] numArr = new boolean[988];
		StringTokenizer st = new StringTokenizer(br.readLine());
		int num = Integer.parseInt(st.nextToken());
		int strike = Integer.parseInt(st.nextToken());
		int ball = Integer.parseInt(st.nextToken());
		for (int i = 123; i < 988; i++) {
			if(!canSB(i)) {
				continue;
			}
			int strikeCnt = isStrike(i, num); 
			int ballCnt = isBall(i, num);
			numArr[i] = strike == strikeCnt && ball == ballCnt ? true : false;
		}
		for(int i = 0; i < N-1; i++) {
			st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());
			strike = Integer.parseInt(st.nextToken());
			ball = Integer.parseInt(st.nextToken());
			for(int j = 123; j < 988; j++) {
				if(numArr[j]) {
					int strikeCnt = isStrike(j, num); 
					int ballCnt = isBall(j, num);
					numArr[j] = strike == strikeCnt && ball == ballCnt ? true : false;
				}
			}
		}
		for(int i = 123; i < 988; i++) {
			result = numArr[i] ? result + 1 : result;
		}
		System.out.println(result);
	}
	public static int isStrike(int number, int matchNum) {
		int cnt = 0;
		char hun = Integer.toString(number).charAt(0);
		char ten = Integer.toString(number).charAt(1);
		char one = Integer.toString(number).charAt(2);
		if(hun == Integer.toString(matchNum).charAt(0)) cnt++;
		if(ten == Integer.toString(matchNum).charAt(1)) cnt++;
		if(one == Integer.toString(matchNum).charAt(2)) cnt++;
		return cnt;
	}
	public static int isBall(int number, int matchNum) {
		int cnt = 0;
		char hun = Integer.toString(number).charAt(0);
		char ten = Integer.toString(number).charAt(1);
		char one = Integer.toString(number).charAt(2);
		char matHun = Integer.toString(matchNum).charAt(0);
		char matTen = Integer.toString(matchNum).charAt(1);
		char matOne = Integer.toString(matchNum).charAt(2);
		if(hun == matTen || hun == matOne) cnt++;
		if(ten == matHun || ten == matOne) cnt++;
		if(one == matHun || one == matTen) cnt++;
			return cnt;
	}
	
	public static boolean canSB(int number) {
		char hun = Integer.toString(number).charAt(0);
		char ten = Integer.toString(number).charAt(1);
		char one = Integer.toString(number).charAt(2);
		if(hun == ten || ten == one || hun == one ||
				hun == '0' || ten == '0' || one == '0') {
			return false;
		}
		return true;
	}
}
