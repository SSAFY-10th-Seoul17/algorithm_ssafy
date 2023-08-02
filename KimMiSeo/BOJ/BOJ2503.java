// 숫자 야구
// 📝 


import java.io.*;
import java.util.*;

public class BOJ2503 {
	public static int[][] question;
	public static int result=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 몇번이나 질문했는지
		int n = Integer.parseInt(br.readLine());
		
		// n줄마다 질문 , 스트라이크, 볼 개수
		question = new int[n][3];
		for (int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int q = Integer.parseInt(st.nextToken());
			int strike = Integer.parseInt(st.nextToken());
			int ball = Integer.parseInt(st.nextToken());
			
			question[i][0] = q;
			question[i][1] = strike;
			question[i][2] = ball;
		}
		
		// 중복되지 않게 3자리수 모두 구하기 - 순열
		for(int j=1; j<=9; j++) {
			for (int k=1; k<=9; k++) {
				if (j == k) continue;
				for (int g=1; g<=9; g++ ) {
					if (j==g || k==g) continue;
					
					int num = 100*j+10*k+g;
					isCo(num);
				}
			}
		}
		
		System.out.println(result);
	}
	
	// 결과가 모두 맞는 지 체크하는 함수 
	private static void isCo(int num) {
		boolean flag = true;
		for (int i=0; i<question.length; i++) {
			int[] qnum = numToArray(question[i][0]);
			int[] onum = numToArray(num);
			
			int strike = question[i][1];
			int ball = question[i][2];
			
			int s = 0;
			int b = 0;
			
			for(int j=0; j<onum.length; j++) {
				for (int k=0; k<onum.length; k++) {
					if (onum[j] == qnum[k]) {
						if (j == k) {
							s ++;
						}
						else {
							b++;
						}
					}
				}
			}
			
			if (s!= strike || b!= ball) {
				flag = false;
				break;
			}
			
			
		}
		
		if (flag) result++;
	}
	
	// 숫자를 배열로 바꿔주는 함수 ex) 123 -> [1,2,3]
	private static int[] numToArray(int num) {
		String temp = Integer.toString(num);
		int[] result = new int[temp.length()];
		
		for (int i=0; i<temp.length(); i++) {
			result[i] = temp.charAt(i)-'0';
		}
		return result;
	}
}
