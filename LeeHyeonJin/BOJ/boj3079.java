import java.util.*;
import java.io.*;

/*
 * 아이디어 :
 * - 완전탐색으로 풀어야 한다 -> 데이터가 너무 크다 -> dp? 이분탐색?
 * - 정답은 "최적해" -> 이분탐색일 가능성 높음
 * - 첫번째 연산만으로 초기값을 확신할 수 있나? NO -> 이분탐색 확신
 *
 * - 이분탐색 내부에서 어떻게 최적의 해라고 확신하는가?
 * - 구해야하는 것 : M명을 입국심사대를 통과시켰을때 걸리는 최소시간
 * - "현재의 추정값(mid)으로 총몇명의 인원을 통과시킬 수 있는지 구한다" -> mid 시간동안 각 입국심사대(times[i])는 몇명을 통과시킬 수 있는지
 */
public class boj3079 {
	static int N, M;
	static long[] times;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		times = new long[N];
		long max = Long.MIN_VALUE;
		for(int i=0; i<N; i++) {
			times[i] = Long.parseLong(br.readLine());
			max = max < times[i] ? times[i] : max;
		}

		// solution
		long res = parametricSearch(0, max * M, M);

		// output
		System.out.println(res);
	}

	// 러프한 핵심로직 : 이분탐색
	static long parametricSearch(long start, long end, long target) {
		long res = 0;
		while(start <= end) {
			long mid = (start + end) / 2;

			// 세부 핵심로직 : 현재의 추정값(mid)으로 총몇명의 인원을 통과시킬 수 있는지 구한다
			long cnt = 0;
			for(long time : times) {
				cnt += mid/time;
				// 주의 : 어차피 더이상 둘러볼 필요 없는 경우 바로 반복을 중단해야 틀리지않음
				if(cnt >= M) {
					break;
				}
			}

			if(cnt >= target) {
				end = mid - 1;
				res = mid;
			} else {
				start = mid + 1;
			}
		}
		return res;
	}
}

