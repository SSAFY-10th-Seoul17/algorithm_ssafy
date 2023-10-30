import java.io.*;
import java.util.*;

	// 만드는 문자열이 == len(T)멈춤
	  // S랑 비교
	// 이미 만든 문자열인지 검사(HashSet)
	// A추가
	// B추가 and 뒤집기
public class Main {
    private static StringBuilder S, T;
    private static HashSet<StringBuilder> chk = new HashSet<>();
	public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = new StringBuilder(br.readLine());
        T = new StringBuilder(br.readLine());
        makeStr(T,T.length());
        System.out.println(0);
    } // end of main

	private static void makeStr(StringBuilder sb, int len) {
		if(len==S.length()) {
			if(S.compareTo(sb)==0) {
				System.out.println(1);
				System.exit(0);
			}
			return;
		}
		if(chk.contains(sb)) return;
		chk.add(sb);
		if(sb.charAt(len-1)=='A')makeStr(new StringBuilder(sb).deleteCharAt(len-1),len-1);
		if(sb.charAt(0)=='B')makeStr(new StringBuilder(sb).reverse().deleteCharAt(len-1),len-1);
	}
} // end of class

/**
import java.io.*;

// T에서 S만들기
	// 맨뒤가 A면 end줄이기
	// 맨앞이 B면 reverse하기
public class Main {
    private static char[] S, T;
	public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine().toCharArray();
        T = br.readLine().toCharArray();
        makeStr(0,T.length-1,false);
        System.out.println(0);
    } // end of main

	private static void makeStr(int start, int end, boolean isReverse) {
		if(end-start+1==S.length) {
			if(isEqual(start,end,isReverse)) {
				System.out.println(1);
				System.exit(0);
			}
			return;
		}
		if(T[isReverse?start:end]=='A')makeStr(start+(isReverse?1:0),end+(isReverse?0:-1),isReverse);
		if(T[isReverse?end:start]=='B')makeStr(start+(isReverse?0:1),end+(isReverse?-1:0),!isReverse);
	}

	private static boolean isEqual(int start, int end, boolean isReverse) {
		if(isReverse) {
			for (int i = end, inx = 0; inx < S.length; i--, inx++) {
				if(S[inx]!=T[i]) return false;
			}
		}else {
			for (int i = start, inx = 0; inx < S.length; i++, inx++) {
				if(S[inx]!=T[i]) return false;
			}
		}
		return true;
	}
} // end of class

**/
