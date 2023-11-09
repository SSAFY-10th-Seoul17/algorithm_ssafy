import java.io.*;
import java.util.*;

public class boj23829_인문예술탐사주간 {
	static int N, Q;
	static int[] p, x;
	static long[] sumDistFromZero;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());
		sumDistFromZero = new long[N+1];
		p = new int[N];
		x = new int[Q];

		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) p[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(p);
		sumDistFromZero[1] = p[0];
		for(int i=2; i<=N; i++) sumDistFromZero[i] = sumDistFromZero[i-1] + p[i-1];
		for(int i=0; i<Q; i++) x[i] = Integer.parseInt(br.readLine());

		// solution
		StringBuilder sb = new StringBuilder();
		for(int pic : x) {
			// 1. 나무들 사이의 사진의 인덱스 구하기(사진위치보다 크거나 같은 첫번째 나무 인덱스)
			int idx = lowerBound(0, N-1, pic);
			// 2. pic 인덱스를 기준으로 각각 오른쪽, 왼쪽에 있는 나무들과의 거리합 구하기
			sb.append(getSumDistBtw(idx, pic)).append("\n");
		}

		// output
		System.out.println(sb.toString());
	}

	static int lowerBound(int left, int right, int target) {
		while(left < right) {
			int mid = (left+right)/2;
			if(p[mid] >= target) {
				right = mid;
			} else {
				left = mid+1;
			}
		}
		return right;
	}

	static long getSumDistBtw(int idx, int pic) {
		if(pic > p[N-1]) {
			return Math.abs(sumDistFromZero[N] - (long)pic *N);
		}
		int cntL = idx, cntR = N-idx;
		long sum = 0;
		sum += Math.abs(sumDistFromZero[idx] - (long)pic *cntL);
		sum += sumDistFromZero[N] - sumDistFromZero[idx] - (long)pic *cntR;
		return sum;
	}
}
