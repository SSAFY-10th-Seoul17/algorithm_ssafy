import java.io.*;
import java.util.*;
import java.lang.*;

public class boj14606 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// input
		int n = Integer.parseInt(br.readLine());

		// solution : a = b*c일때, b,c가 각각 최댓값이 되어야 함
		if(n == 1) {
			bw.write(0+"");
		} else {
			Stack<Integer> s = new Stack<>();
			int b = n/2;
			int c = n-b;
			s.push(b);
			s.push(c);
			int sum = b*c;

			while(!s.isEmpty()) {
				int a = s.pop();
				if(a == 1) {
					continue;
				}
				b = a/2;
				c = a-b;
				s.push(b);
				s.push(c);
				sum += b*c;
			}

			bw.write(sum+"");
		}

		// output
		bw.flush();
		bw.close();
	}
}
