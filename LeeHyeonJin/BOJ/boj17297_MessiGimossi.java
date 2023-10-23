import java.io.*;
import java.util.*;

public class boj17297_MessiGimossi {
	static int M;
	static List<Integer> len = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		M = Integer.parseInt(br.readLine());

		// solution
		// m 길이를 초과하는 문자열을 생성
		len.add(0);
		len.add(5);
		len.add(13);
		int prev = len.get(1), now = len.get(2), total = 0;
		while(true) {
			total = now+1+prev;
			len.add(total);
			if(total > M) break;
			prev = now; now = total;
		}
		// messi(N)의 M 번째 글자 구하기
		String res = findM(M, len.size());

		// output
		System.out.println(res);
	}

	static String findM(int m, int n) {
		// 기저조건 : messi(1), messi(2) 둘 중에 하나여야 함
		if(m <= 13) return letter(m);

		// 풀이 : m 이 왼쪽(messi(K-1)), 중간(' '), 오른쪽(messi(K-2)) 중 어디에 속하는지 확인
		if(m <= len.get(n-1)) { // n 이 왼쪽(messi(K-1)) 범위에 있음
			return findM(m, n-1);
		} else if(m == len.get(n-1)+1) { // n 이 중간(' ') 범위에 있음
			return "Messi Messi Gimossi";
		} else { // n 이 오른쪽(messi(K-2)) 범위에 있음
			return findM(m-(len.get(n-1)+1), n-2);
		}
	}

	static String letter(int m) {
		switch(m) {
			case 1:
				return "M";
			case 2:
				return "e";
			case 3:
			case 4:
			case 11:
			case 12:
				return "s";
			case 5:
			case 8:
			case 13:
				return "i";
			case 7:
				return "G";
			case 9:
				return "m";
			case 10:
				return "o";
		}
		return "Messi Messi Gimossi";
	}
}
