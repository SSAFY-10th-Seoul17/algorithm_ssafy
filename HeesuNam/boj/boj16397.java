import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int T;
	private static int G;
	private static final int LIMIT = 100000;
	private static int[] len = { 10000, 1000, 100, 10, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		pushButton();
	} // end of main

	private static void pushButton() {
		Queue<int[]> p = new ArrayDeque<>();
		boolean[] visited = new boolean[LIMIT];
		p.offer(new int[] { N, 0 }); // 숫자, 버튼 누른 숫자
		while (!p.isEmpty()) {
			int[] cur = p.poll();
			if (cur[1] > T)
				break;
			if (cur[0] == G) {
				System.out.println(cur[1]);
				return;
			}
			if (cur[0] == -1 || visited[cur[0]])
				continue;
			visited[cur[0]] = true;
			p.offer(new int[] { pushA(cur[0]), cur[1] + 1 });
			if (cur[0] != 0)
				p.offer(new int[] { pushB(cur[0]), cur[1] + 1 });
		}
		System.out.println("ANG");
	}

	private static int pushA(int num) {
		num += 1;
		if (num >= LIMIT) {
			return -1;
		}
		return num;
	}

	private static int pushB(int num) {
		num *= 2;
		if (num >= LIMIT) {
			return -1;
		}
		int length = 0;
		for (int l : len) {
			if (num >= l) {
				length = l;
				break;
			}
		}
		return num - length;
	}

} // end of class
