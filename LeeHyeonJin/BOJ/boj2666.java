import java.io.*;
import java.util.*;

public class boj2666 {
	static int M;
	static int[] plans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		int N = Integer.parseInt(br.readLine());
		int[] opened = new int[2];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<2; i++) {
			opened[i] = Integer.parseInt(st.nextToken());
		}

		M = Integer.parseInt(br.readLine());
		plans =  new int[M];
		for(int i=0; i<M; i++) {
			plans[i] = Integer.parseInt(br.readLine());
		}

		// solution
		int res = DFS(0, opened[0], opened[1]);

		// output
		System.out.println(res);
	}

	// 완탐 => 당장 현재 사용할 순서의 문과 가까운 열려있는 문을 선택한다고 해서 최단거리일 거라는 보장 X
	// 벽장 최대 개수가 20 => O(2^N)인 재귀호출 사용한다고 하면(매선택마다, 2가지 경우의 수로 갈림) 최악의 경우 1,048,576 => 가능할듯
	static int DFS(int cnt, int opened1, int opened2) {
		if(cnt == M) {
			return 0;
		}
		// 현재 사용할 순서의 벽장 번호로 부터 각각 문까지의 거리
		int diff1 = Math.abs(plans[cnt]-opened1);
		int diff2 = Math.abs(plans[cnt]-opened2);
		return Math.min(
			// 현재 사용할 순서의 벽장을 열고, 거리를 사용한 문은 닫아준다
			diff1 + DFS(cnt+1, plans[cnt], opened2),
			diff2 + DFS(cnt+1, opened1, plans[cnt])
		);
	}
}
