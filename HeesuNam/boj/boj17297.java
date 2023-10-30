import java.io.*;
import java.util.*;

// messi(N) = messi(N-1) + 1 + Messi(N-2)
// M이 존재하는 N의 길이 구하기
// M의 위치 추측하기
// M <= messi(N-1) : messi(N-1)에서 찾기
// M = messi(N-1)+1 :공백
// messi(N-1)+1 < M <= messi(N -2) : messi(N-2)에서 찾기

public class Main {
	private static ArrayList<Integer> len;
	private static int M;
	static final String SHOUT = "Messi Gimossi";
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		M = Integer.parseInt(br.readLine());
		// M이 존재하는 N구하기
		len = new ArrayList<Integer>();
		len.add(5);
		len.add(13);
		int inx = 2;
		while (len.get(inx - 1) < M) {
			len.add(len.get(inx - 1) + 1 + len.get(inx - 2));
			inx++;
		}
		// M의 위치 추측하기
		System.out.println(messi(M, inx-1));
	} // end of main

	private static String messi(int m, int inx) {
		if (inx < 2)
			return printStr(m-1);
		// M <= messi(N-1) : messi(N-1)에서 찾기
		if (m <= len.get(inx - 1))
			return messi(m, inx - 1);
		// M = messi(N-1)+1 :공백
		else if (m == len.get(inx - 1) + 1)
			return "Messi Messi Gimossi";
		// messi(N-1)+1 < M <= messi(N -2) : messi(N-2)에서 찾기
		else if (m <= len.get(inx - 1) + 1+ len.get(inx - 2))
			return messi(m - len.get(inx - 1) - 1, inx - 2);
		return "";

	}

	private static String printStr(int inx) {
		if(SHOUT.charAt(inx)==' ') return "Messi Messi Gimossi";
		return ""+SHOUT.charAt(inx);
	}
} // end of class
