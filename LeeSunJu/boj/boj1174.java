import java.io.*;
import java.util.*;

public class boj1174 {

	static int[] nums = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0}; // 줄어드는 수를 위한 배열
	static int N;
	static ArrayList<Long> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());

		dfs(0, 0);
		Collections.sort(list); // 9부터 시작했으므로 오름차순 정렬

		if (N >= list.size()) { // N이 만들 수 있는 줄어드는 수의 개수보다 클 경우
			System.out.println(-1);
		} else {
			System.out.println(list.get(N));
		}
	}

	private static void dfs(int idx, long total) {
		if (idx == nums.length) { // 숫자 배열을 너어가면 stop
			list.add(total);
			return;
		}

		dfs(idx + 1, total * 10 + nums[idx]); // 선택함.
		dfs(idx + 1, total); // 선택 안함
	}
}
