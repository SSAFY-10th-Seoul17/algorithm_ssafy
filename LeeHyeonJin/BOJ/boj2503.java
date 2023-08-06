import java.io.*;
import java.util.*;

public class boj2503 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// input
		int n = Integer.parseInt(br.readLine());
		int[] qNums = new int[n];
		int[] strikes = new int[n];
		int[] balls = new int[n];
		StringTokenizer st;
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			qNums[i] = Integer.parseInt(st.nextToken());
			strikes[i] = Integer.parseInt(st.nextToken());
			balls[i] = Integer.parseInt(st.nextToken());
		}

		// solution
		int result = 0;
		for(int num=123; num<1000; num++) {
			String strNum = String.valueOf(num);
			// 순열조건 부합X 숫자 걸러내기
			boolean isPossible = true;
			for(int i=0; i<3; i++) {
				if(strNum.charAt(i) == '0') {
					isPossible = false;
				}
				if(strNum.charAt(i) == strNum.charAt((i+1)%3)) {
					isPossible = false;
				}
			}
			if (!isPossible) {
				continue;
			}

			// 질문숫자와 비교
			for(int j=0; j<n; j++) {
				int strikeCnt = 0;
				int ballCnt = 0;
				String strQNum = String.valueOf(qNums[j]);
				for(int i=0; i<3; i++) {
					if(strQNum.charAt(i) == strNum.charAt(i)) { // 스트라이크
						strikeCnt++;
					} else if(strQNum.contains(strNum.charAt(i)+"")) { // 볼
						ballCnt++;
					}
				}
				if(strikeCnt != strikes[j] || ballCnt != balls[j]) {
					isPossible = false;
					break;
				}
			}
			if (isPossible) {
				result++;
			}
		}
		// output
		bw.write(result+"");
		bw.flush();
		bw.close();
	}
}
