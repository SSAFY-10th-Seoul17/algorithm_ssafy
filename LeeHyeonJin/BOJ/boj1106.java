import java.io.*;
import java.util.*;

/*
* 배낭문제 아이디어 : 재귀를 통한 완전탐색을 해야하는데, 깊이가 100이 넘어가버린다? -> 성능 X
* 				=> 기존의 중복되는 연산을 반복하지 않도록, 저장해두고 앞의 결과가 고정된 정답이라는 가정하에 이를 이용해서 뒤에서 사용
* 구해야하는 값 : 고객수(1 ~ C)까지의 경우를 완전탐색 -> 그런데, 여기서는 적어도 N명을 확보해야하며 정확하게 N을 만들 수 있다는 보장도 X
*               => 한 도시당 최대 100만큼의 고객을 확보할 수 있다는 조건이 있다 => C + 100까지를 완전탐색해본다
* 값을 구하는 재료 : 비용 => 비용별로 1 ~ C+100까지의 경우를 일일이 확인하며 더 작은 경우로 값을 갱신해준다
*
* 이 문제만의 특징 :
* - 최소비용을 골라야 함 : 비교하기 위해 디폴트값을 Integer.MAX_VALUE로 잡아주었는데, 이때 MAX_VALUE에 계속 값을 누적해서 쓰레기값이 생기는 상황을 피해야함
* - 적어도 C 값을 만드는 경우의 수 : 적어도라는 말은 그 뒤에 더 최적의 해가 있을 수도 있다는 말 -> C ~ C+100까지의 인덱스의 dp테이블 요소를 모두 확인할 필요 있음
* */
public class boj1106 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int[] cost = new int[N];  //비용
		int[] value = new int[N]; //고객수
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			cost[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}

		// solution
		int[] dp = new int[C+101];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		for(int i=0; i<N; i++) {
			for(int j=1; j<C+101; j++) {
				if(j-value[i] >= 0) {
					if(dp[j-value[i]] != Integer.MAX_VALUE) { //Integer.MAX_VALUE일때 값을 누적하면 쓰레기값이 생김
						dp[j] = Math.min(dp[j], dp[j-value[i]] + cost[i]);
					}
				}
			}
		}

		int res = Integer.MAX_VALUE;
		for(int i=C; i<C+101; i++) {
			res = res > dp[i] ? dp[i] : res;
		}

		// output
		System.out.println(res);
	}
}

