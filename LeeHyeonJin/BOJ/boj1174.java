import java.io.*;
import java.util.*;

public class boj1174 {
	static int N;
	// static Set<int>
	static int res = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		N = Integer.parseInt(br.readLine());

		// solution
		DFS(0, 0);

		// output
		System.out.println(res);
	}

	static void DFS(int cnt, int num) {
		if(cnt == N) {
			res = num-1;
			return;
		}
		if(descending(num)) {
			DFS(cnt+1, num+1);
		} else {
			DFS(cnt, num+1);
		}
	}

	static boolean descending(int num) {
		// int 타입의 숫자의 각 자릿수를 서로 비교하려면? 10으로 나눈 나머지가 가장 끝 자리수의 숫자
		while(num > 9) {
			int next = num % 10;
			num /= 10;
			int prev = num % 10;
			if(prev <= next) {
				return false;
			}
		}
		return true;
	}
}
