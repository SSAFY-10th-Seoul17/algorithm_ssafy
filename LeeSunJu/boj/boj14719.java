import java.io.*;
import java.util.*;

public class boj14719 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		Stack<Integer> stack = new Stack<>();
		int maxLen = Integer.MIN_VALUE;
		int result = 0;
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < W; i++) {
			int blockLen = Integer.parseInt(st.nextToken());
			if (blockLen >= maxLen) {
				while (!stack.empty()) {
					result += maxLen - stack.pop();
				}
				maxLen = blockLen;
			} else {
				stack.push(blockLen);
			}
		}

		maxLen = Integer.MIN_VALUE;
		while (!stack.empty()) {
			int tmp = stack.pop();
			if (tmp >= maxLen) {
				maxLen = tmp;
			} else {
				result += maxLen - tmp;
			}
		}

		System.out.println(result);
	}
}
