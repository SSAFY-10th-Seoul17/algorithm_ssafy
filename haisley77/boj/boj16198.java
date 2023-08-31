import java.io.*;
import java.util.*;

public class boj16198 {
	static int N, max = Integer.MIN_VALUE;
	static int[] A, W;
	static boolean[] remo;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		W = new int[N];
		A = new int[N-2];
		remo = new boolean[N];
		for (int i = 0; i < N; i++) {
			W[i] = Integer.parseInt(st.nextToken());
		}
		perm(0,0);
		System.out.println(max);
	}
	public static void perm(int cnt, int flag) {
		if (cnt == N-2) {
			Arrays.fill(remo,false);
			int energy = 0;
			for (int i = 0; i < N-2; i++) {
				int index = A[i];
				int left = index - 1;
				int right = index + 1;
				while (remo[left]) left--;
				while (remo[right]) right++;
				energy += W[left] * W[right];
				remo[index] = true;
			}
			if (max < energy) {
				max = energy;
			}
			return;
		}
		for (int i = 1; i <= N-2; i++) {	// 비트마스킹
			if ((flag & (1 << (i-1))) != 0) continue;
			A[cnt] = i;
			perm(cnt+1, flag | (1 << (i-1)));
		}
	}

}
