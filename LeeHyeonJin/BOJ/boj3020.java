import java.io.*;
import java.util.*;

/*
 * 아이디어 :
 * H 높이에서 최소의 장애물을 지나가는 일직선 높이를 탐색해야 한다
 * 탐색, 최적의 해 구하기, 데이터도 큼 -> 때려박아서 up/down => 파라메트릭 서치
 *
 * 이때, 종유석과 석순이 시작하는 위치가 위, 아래로 각각 다르기 때문에 한꺼번에 검사하기 어려움 => 구분해서 합치면 된다
 * 최솟값이 정해져있지 않아서 높이를 mid 의 범위로 잡아버리면 target 을 정하기 어려움 => 종유석의 인덱스를 범위로 잡으면 된다
 */
public class boj3020 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		int[] top = new int[N/2]; // 종유석
		int[] bottom = new int[N/2]; // 석순
		for(int i=0; i<N; i++) {
			if(i % 2 == 0) {
				bottom[i/2] = Integer.parseInt(br.readLine());
			} else {
				top[i/2] = Integer.parseInt(br.readLine());
			}
		}

		// solution
		Arrays.sort(bottom);
		Arrays.sort(top);
		int minCnt = Integer.MAX_VALUE, minRangeCnt = 0;

		for(int hei=1; hei<=H; hei++) {
			// 1 ~ H 까지 모든 높이에 대해 장애물과 곂치는 구간 탐색
			int bottomCnt = binarySearch(0, bottom.length, hei, bottom);
			int topCnt = binarySearch(0, top.length, H-hei+1, top);
			int cnt = bottomCnt + topCnt;

			// 탐색된 장애물의 개수가 현재 최솟값보다 작으면 -> 최솟값, 최소구간 개수 갱신
			if(cnt < minCnt) {
				minRangeCnt = 1;
				minCnt = cnt;
			}

			// 탐색된 장애물의 개수가 현재 최솟값과 동일하면 -> 최소구간 개수 +1
			else if(cnt == minCnt) {
				minRangeCnt++;
			}
		}

		// output
		StringBuilder sb = new StringBuilder();
		sb.append(minCnt).append(" ").append(minRangeCnt);
		System.out.println(sb.toString());
	}

	static int binarySearch(int start, int end, int target, int[] obstacles) {
		while(start < end) {
			int mid = (start+end)/2; // 부딪히는 장애물의 첫번째 인덱스 가정
			if(obstacles[mid] >= target) { // 부딪히면?
				end = mid; // 장애물의 첫번째 인덱스를 줄여서 -> 부딪힐 장애물 개수 증가
			} else { // 안부딪히면?
				start = mid + 1; // 장애물의 첫번째 인덱스 늘여서 -> 부딪힐 장애물 개수 감소
			}
		}
		return obstacles.length - end; // target 과 처음만나는 장애물 이후로 모든 장애물이 부딪힘
	}
}

