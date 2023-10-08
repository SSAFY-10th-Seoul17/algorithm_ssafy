import java.io.*;
import java.util.*;

/*
 * 아이디어 :
 * 완전탐색의 형태 -> 데이터가 너무 커서 그냥 하면 불가능
 * 메모이제이션 적용 : 층수, 인덱스 가 동일하고 이미 탐색한 DFS 메서드라면 저장되어 있는 리턴값을 반환하고 리턴 -> 시간초과
 * DP 로 풀어보기 위해 일단 배열을 그려보고 중간지점을 체크해본다 -> 점화식 도출
 * - 점화식 : 이전 테이블(이미 최적값 저장됨, 나한테 올 수 있는 애들)의 값 중 가장 크거나/작은 값 + 지금 체크하는 중인 인덱스의 값
 * - 추가 : 메모리 제한이 있어서 입력값을 따로 저장하지 않고 입력받는 즉시 솔루션을 적용해본다 -> 입력 정수 배열 int[N][3] -> int[3]으로 줄일 수 있다
 */
public class boj2096 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] num = new int[3];
		int[][] maxDp = new int[N][3];
		int[][] minDp = new int[N][3];

		for(int i=0; i<N; i++) {
			// input
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<3; j++) {
				num[j] = Integer.parseInt(st.nextToken());
			}

			// solution
			if(i == 0) {
				maxDp[0][0] = minDp[0][0] = num[0];
				maxDp[0][1] = minDp[0][1] = num[1];
				maxDp[0][2] = minDp[0][2] = num[2];
				continue;
			}

			maxDp[i][0] = Math.max(maxDp[i-1][0], maxDp[i-1][1]) + num[0];
			maxDp[i][1] = Math.max(maxDp[i-1][0], Math.max(maxDp[i-1][1], maxDp[i-1][2])) + num[1];
			maxDp[i][2] = Math.max(maxDp[i-1][1], maxDp[i-1][2]) + num[2];

			minDp[i][0] = Math.min(minDp[i-1][0], minDp[i-1][1]) + num[0];
			minDp[i][1] = Math.min(minDp[i-1][0], Math.min(minDp[i-1][1], minDp[i-1][2])) + num[1];
			minDp[i][2] = Math.min(minDp[i-1][1], minDp[i-1][2]) + num[2];
		}

		// output
		int max = Math.max(maxDp[N-1][0], Math.max(maxDp[N-1][1], maxDp[N-1][2]));
		int min = Math.min(minDp[N-1][0], Math.min(minDp[N-1][1], minDp[N-1][2]));
		StringBuilder sb = new StringBuilder();
		sb.append(max).append(" ").append(min);
		System.out.println(sb.toString());
	}
}

