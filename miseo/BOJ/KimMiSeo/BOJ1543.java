package KimMiSeo;

// ✅ 
// 06:40 - 07:01 : 21m

import java.util.*;
import java.io.*;

public class BOJ1543 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String doc = br.readLine();
		String word = br.readLine();
		
		// 인덱스 + 작은친구 가 긴친구의 인덱스와 같거나 작을때까지
		int start = 0;
		int longlen = doc.length();
		int shortlen = word.length();
		int result = 0;
		
		// start + shortlen <= longlen!!
		while (start+shortlen <= longlen) {
			String find = doc.substring(start, start+shortlen);
			if (find.equals(word)) {
				result++;
				start += shortlen;
			}
			else {
				start++;
			}			
		}
		
		System.out.println(result);
	}

}
