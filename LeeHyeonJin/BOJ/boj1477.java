import java.io.*;
import java.util.*;

/*
* 휴게소 사이의 거리를 "특정 거리"로 분할했을때 총 M번 분할되어야 함
* 위 조건에 부합하는 특정 거리를 "1 ~ L-1" 까지 탐색하며 찾아야 함 => 이분탐색
*
* 이분탐색 내부에서 가능여부 확인하는 코드는 "임시 거리(mid)"로 각 휴게소 사이의 거리를 분할할 수 있는 개수를 카운팅 해봄
* 이때, 단순히 [직전 휴게소 ~ 직후 휴게소] 사이의 거리를 나누면 X <- 거리가 아니라 좌표의 구간(정수)를 나눠야 함
* => 0 ~ 5 사이의 구간의 수 = 5(길이) + 1, 근데 여기서 시작과 끝 구간은 이미 포함되어 휴게소 설치 X => 구간의 수 - 2 해주어야 하고, 이는 길이(끝점-시작점) - 2와 동일
* */
public class boj1477 {
	static int N, M, L;
	static int[] restAreas;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		restAreas = new int[N+2];
		restAreas[0] = 0;
		restAreas[1] = L;
		st = new StringTokenizer(br.readLine());
		for(int i=2; i<N+2; i++) restAreas[i] = Integer.parseInt(st.nextToken());

		// solution
		Arrays.sort(restAreas);
		int res = binarySearch(1, L - 1, M);

		// output
		System.out.println(res);
	}

	static int binarySearch(int start, int end, int cnt) {
		int len = 0;
		while(start <= end) {
			int mid = (start + end) / 2;
			int temp = 0;
			for(int i=1; i<N+2; i++) {
				temp += (restAreas[i] - restAreas[i-1] - 1) / mid; //포인트(휴게소) 개수 - 1 = 길이(거리) => 포인트(휴게소) 개수 - 2 = 길이(거리) - 1
			}
			if(temp <= cnt) {
				end = mid - 1;
				len = mid;
			} else {
				start = mid + 1;
			}
		}
		return len;
	}
}
