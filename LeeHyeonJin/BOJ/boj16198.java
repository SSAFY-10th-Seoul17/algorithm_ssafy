import java.io.*;
import java.util.*;

public class boj16198 {
	static int N;
	static int[] marbles, copiedMarbles;
	static int[] orders;
	static boolean[] selected;
	static int res = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		N = Integer.parseInt(br.readLine());
		marbles = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) marbles[i] = Integer.parseInt(st.nextToken());

		// solution
		orders = new int[N-2];
		selected = new boolean[N];
		permutations(0);

		// output
		System.out.println(res);
	}

	static void permutations(int cnt) {
		if(cnt == N-2) {
			getMaxSum();
			return;
		}
		for(int i=1; i<N-1; i++) {
			if(selected[i]) continue;
			orders[cnt] = i;
			selected[i] = true;
			permutations(cnt+1);
			selected[i] = false;
		}
	}

	static void getMaxSum() {
		copy();
		int sum = 0;
		for(int order : orders) {
			int left = order-1; int right = order+1;
			while(copiedMarbles[left] == 0) left--;
			while(copiedMarbles[right] == 0) right++;
			sum += copiedMarbles[left] * copiedMarbles[right];
			copiedMarbles[order] = 0;
		}
		res = res < sum ? sum : res;
	}

	static void copy() {
		copiedMarbles = new int[N];
		for(int i=0; i<N; i++) copiedMarbles[i] = marbles[i];
	}
}

