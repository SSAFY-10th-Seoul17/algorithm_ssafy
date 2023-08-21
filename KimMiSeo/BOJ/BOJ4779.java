// 칸토어 집합
// 1. ' '인 인덱스를 저장해서 다시 result를 출력해주려고 함 -> 시간초과
// 2. result 자체를 바꿔서 출력 -> 시간초과
// 3. bufferedwriter 사용

// 11:32 - 1:20 .. 시간초과

import java.util.*;
import java.io.*;

public class BOJ4779 {
	public static int n;
	public static char[] result;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = "";
		while((input = br.readLine())!=null) {
			n = Integer.parseInt(input);

			
			int len = (int)Math.pow(3, n);
	
			result = new char[len];
			for (int i=0; i<len; i++) {
				result[i] = '-';
			}
			

			solve(0,len);	
			
			for (int i=0; i<len; i++) {
				bw.write(result[i]);
			}
			
			bw.newLine();
			bw.flush();
			
		}

	}
	
	private static void solve(int start, int end) {
		
		if ( (end-start) <= 1 ) {
			return;
		}
		
		int totalLen = end - start;
		int nullStartIndex = start + (totalLen / 3);
		int	nullEndIndex = nullStartIndex + (totalLen / 3);
		
		for (int i=nullStartIndex; i<nullEndIndex; i++) {
			result[i] = ' ';
		}

		solve(start, nullStartIndex);
		solve(nullEndIndex, end);

	}
}
