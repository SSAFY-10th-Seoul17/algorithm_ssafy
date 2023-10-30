import java.io.*;
import java.util.*;

public class boj2467_용액 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// input
		int N = Integer.parseInt(br.readLine());
		int[] values = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++) values[i] = Integer.parseInt(st.nextToken());

		// solution
		int left = 0, right = N-1; // 포인터 2개
		int min = Integer.MAX_VALUE; // 절댓값과 비교해줄 값
		int[] res = new int[2]; // 조건에 부합하는 용액 저장 배열

		while(left < right) { // 용액 2개는 서로 달라야 하므로 겹치지 않을 때까지
			int diff = values[left] + values[right];
			// 특성값이 0에 더 가까운 경우
			if(min > Math.abs(diff)) {
				min = Math.abs(diff);
				res[0] = values[left];
				res[1] = values[right];
			}
			// 포인터 조절
			if(diff < 0) { // 음수라면 right 을 키워봤자 어차피 더 작아질 것(0에서 더 멀어질 것) -> left 감소
				left++;
			} else { // 양수라면 left 를 키워봤자 어차피 더 커질 것(0에서 더 멀어질 것) -> right 감소
				right--;
			}
		}

		// output
		sb.append(res[0]).append(" ").append(res[1]);
		System.out.println(sb.toString());
	}
}
