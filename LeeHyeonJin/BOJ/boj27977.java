import java.io.*;
import java.util.*;

/**
 * 아이디어 :
 * 이분탐색
 * (1) start+1 < end 를 탐색반복 조건으로 준다면 -> start, end 조건에 상관없이 = mid 해주면 된다
 * (2) start+1 < end 조건을 줄 거라면 -> start 는 절대 나올 수 없는 수부터 시작해야 한다
 */
public class boj27977 {
	static int L, N, K;
	static int[] chargers;
	static int maxDist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		L = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		chargers = new int[N+2];
		// 충전소 사이의 거리를 입력받음과 동시에, 두 충전소 간의 거리차이 중 가장 큰 값 저장
		// -> 일단 L 까지 갈 수 있어야 하기 때문에 이분탐색할때 lower bound 가 될 예정
		st = new StringTokenizer(br.readLine(), " ");
		chargers[0] = 0;
		for(int i=1; i<=N; i++) {
			chargers[i] = Integer.parseInt(st.nextToken());
			maxDist = Math.max(maxDist, chargers[i]-chargers[i-1]);
		}
		chargers[N+1] = L;
		maxDist = Math.max(maxDist, chargers[N+1]-chargers[N]);

		// solution
		int res = binarySearch(maxDist-1, L, K);

		// output
		System.out.println(res);
	}

	static int binarySearch(int start, int end, int target) {
		while(start+1 < end) {
			int mid = (start + end) / 2;
			/** 어려웠던 부분 시작(틀렸던 이유)
			 * 회고 :
			 * 전체 거리의 각 단위(0,1,2,...,L-1,L)를 기준으로 잡으니까 mid 만큼씩 이동시킬 때
			 * 이전위치 ~ mid 사이의 '충전소가 있는지 여부', '가장 마지막 충전소 위치'를 구해주기가 애매해져서..
			 * HashSet 을 선언할 때부터 무언가 잘못됐음을 직감..
			 * 어떤 부분이 문제인지 생각해보니까 충전소 위치(chargers[0,1,...,N-1,N])를 기준으로 잡아보자라고 생각하게 되었음
			 *
			 * 지나가는 충전소의 개수를 카운팅할 때 고려해야할 경우의 수 :
			 * 1. 이전 위치 ~ 이전위치 + mid 사이의 거리에 충전소가 있다
			 * 2. 가장 마지막 충전소를 이전위치로 갱신 & 충전소 개수++
			 */
			int cnt = 0;  // 임시 용량(mid)일때 들러야하는 충전소 개수
			int prev = chargers[0]; // 이전에 머물렀던 위치
			for(int i=1; i<N+2; i++) { // 어차피 0~charger[0], charger[N-1]~L 까지는 100% 도달
				if(chargers[i] - prev > mid) { // 다음이동할위치 - 이전위치 가 mid(이동거리) 보다 크다면 -> 지금까지 마지막으로 들를 수 있는 충전소의 인덱스는 i-1 -> 무조건 1개 이상은 mid 보다 작아야 하니까
					cnt++;
					prev = chargers[i-1];
					i -= 1;
				}
			}
			/** 어려웠던 부분 끝 */
			if(cnt > target) {
				start = mid;
			} else {
				end = mid;
			}
		}
		return end;
	}
}

