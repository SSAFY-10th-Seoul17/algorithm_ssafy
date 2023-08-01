import java.io.*;
import java.util.*;
import java.lang.*;

public class boj4779 {
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// input
		String input = "";
		while((input = br.readLine()) != null) {
			int n = Integer.parseInt(input);
			sb = new StringBuilder();
			for(int i=0; i<Math.pow(3,n); i++) {
				sb.append("-");
			}

			// solution
			slice(0, sb.length());

			// output
			bw.write(sb.toString()+"\n");
			bw.flush();
		}
		bw.close();
	}

	static void slice(int start, int length) {
		if(length == 1) { // 선의 길이가 1이면 끝
			return;
		}
		int slicedLength = length/3;
		for(int i=start+slicedLength; i<start+2*slicedLength; i++) {
			sb.setCharAt(i, ' ');
		}
		// 공백인 가운데를 제외한 양끝에 대해 다시 반복
		slice(start, slicedLength);
		slice(start+2*slicedLength, slicedLength);
	}
}
