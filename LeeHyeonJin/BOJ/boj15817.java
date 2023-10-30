import java.io.*;
import java.util.*;

/**
 * 아이디어 : 개수가 정해져있는 냅색문제
 * - dpNext 라는 추가 배열을 하나 만들어서 활용
 * - dp : 이전까지 갱신해둔 값들을 저장해줄 배열(참조용)
 * - dpNext : 새로운 재료들을 갱신해줄 배열(갱신용)
 */
public class boj15817 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());

		List<List<Integer>> pipes = new ArrayList<>();
		for(int i=0; i<N; i++) pipes.add(new ArrayList<>());
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int type = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());
			pipes.get(i).add(type);
			pipes.get(i).add(cnt);
		}

		// solution
		int[] dp = new int[x+1];
		int[] dpNext = new int[x+1];
		dpNext[0] = 1;
		for(int i=0; i<N; i++) {
			dp = Arrays.copyOf(dpNext, x+1);
			for(int j=1; j<=x; j++) {
				int pipe = 0;
				for(int k=0; k<pipes.get(i).get(1); k++) {
					pipe += pipes.get(i).get(0);
					if(j < pipe) continue;
					dpNext[j] += dp[j-pipe];
				}
			}
		}

		// output
		System.out.println(dpNext[x]);
	}
}
