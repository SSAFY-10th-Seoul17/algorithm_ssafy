import java.io.*;
import java.util.*;

/*
 * 아이디어 :
 * (1) 현재 인덱스(i) 블럭을 기준으로 왼쪽과 오른쪽 각각 방향의 가장 높은 블럭의 높이 탐색
 * (2) 양방향의 가장 높은 블럭 중 더 낮은 높이(left/right) - 현재 블럭의 높이(i) 만큼이 현재 인덱스의 블럭 위에 고이게 될 빗물의 양
 * - 이때, 0번째 인덱스와 N-1 인덱스는 바로 옆이 낭떠러지(높이 0)이기 때문에 탐색(i)로 사용할 수 X
 * - 이때, 현재 인덱스(i) 블럭의 높이가 양쪽의 가장 높은 블럭보다 작아야 고일 수 있다
 *
 * [예제 2]
 * 1번 인덱스 :
 * (1) 왼쪽 가장 높은 높이 = 3, 오른쪽 가장 높은 높이 = 4
 * (2) 둘 중 더 낮은 높이 = 3 -> 현재 인덱스와의 차이 절댓값 = 2 => 1번 인덱스에 고이게 될 빗물의 양 = 2
 * ...
 * N-2번 인덱스까지 반복
 */
public class boj14719 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());

		int[] blocks = new int[W];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<W; i++) {
			blocks[i] = Integer.parseInt(st.nextToken());
		}

		// solution
		int res = 0;
		for(int now=1; now<W-1; now++) { // 0번째 인덱스와 N-1 인덱스는 바로 옆이 낭떠러지(높이 0)이기 때문에 탐색(i)로 사용할 수 X
			// (1) 현재 인덱스(i) 블럭을 기준으로 왼쪽과 오른쪽 각각 방향의 가장 높은 블럭의 높이 탐색
			int leftMax = 0, rightMax = 0;
			for(int left=now-1; left>=0; left--) {
				leftMax = Math.max(leftMax, blocks[left]);
			}
			for(int right=now+1; right<=W-1; right++) {
				rightMax = Math.max(rightMax, blocks[right]);
			}

			// (2) 양방향의 가장 높은 블럭 중 더 낮은 높이(left/right) - 현재 블럭의 높이(i) 만큼이 현재 인덱스의 블럭 위에 고이게 될 빗물의 양
			if(blocks[now] < leftMax && blocks[now] < rightMax) { // 현재 인덱스(i) 블럭의 높이가 양쪽의 가장 높은 블럭보다 작아야 고일 수 있다
				int hei = Math.min(leftMax, rightMax);
				res += hei - blocks[now];
			}
		}

		// output
		System.out.println(res);
	}
}
