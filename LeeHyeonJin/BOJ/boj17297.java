import java.io.*;
import java.util.*;

public class boj17297 {
	static List<Integer> length = new ArrayList<>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		int M = Integer.parseInt(br.readLine());

		// solution

		// (1) N 찾기
		length.add(0); //idx 0 : X
		length.add(5); //idx 1 : Messi
		length.add(13);//idx 2 : Messi Gimossi Messi

		int N = 2;
		while(length.get(N) < M) { //규칙대로 M 을 포함할 수 있을때까지 길이를 늘려감
			int now = length.get(N);
			int prev = length.get(N-1);
			int next = now + 1 + prev;
			length.add(next);
			N++;
		}

		// (2) M 찾기
		String res = findM(N, M);

		// output
		System.out.println(res);
	}

	static String findM(int idx, int M) {
		if(idx <= 2) {
			return findRes(M);
		}
		//messi(N) = messi(N-1) + 공백 + messi(N-2)
		if(M <= length.get(idx-1)) { //messi(N-1)
			return findM(idx-1, M);
		}
		else if(M == length.get(idx-1)+1) { //공백
			return  "Messi Messi Gimossi";
		}
		else if(M > length.get(idx-1)+1 && M <= length.get(idx)) { //messi(N-2)
			return findM(idx-2, M-(length.get(idx-1)+1)); //시작점(오프셋)을 맞춰주기 위해 M 조작
		}
		return "";
	}

	static String findRes(int M) {
		switch(M) {
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
			case 6:
				return "Messi Messi Gimossi";
			case 7:
				return "G";
			case 9:
				return "m";
			case 10:
				return "o";
		}
		return "";
	}
}

