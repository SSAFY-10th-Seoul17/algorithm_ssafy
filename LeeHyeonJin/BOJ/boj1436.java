import java.io.*;
import java.util.*;

public class boj1436 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// input
		int n = Integer.parseInt(br.readLine());

		// solution
		int cnt = 1;
		int num = 1;
		while(cnt <= n) {
			num++;
			if(String.valueOf(num).contains("666")) {
				cnt++;
			}
		}

		// output
		bw.write(num+"");
		bw.flush();
		bw.close();
	}
}
