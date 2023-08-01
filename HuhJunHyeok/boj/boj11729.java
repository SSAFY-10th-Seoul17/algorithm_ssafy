import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * [boj] 11729. 하노이 탑 이동 순서
 */
public class boj11729 {
	static int n;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		// 하노이 탑 문제의 최소 이동 횟수는 2^n - 1 이다. hanoi(n) = 2 * hanoi(n - 1) + 1
		sb.append((int) Math.pow(2, n) - 1).append("\n");
		
		hanoi(n, 1, 2, 3);
		
		System.out.print(sb);
	}
	
	/**
	 * @param num : 원판의 수
	 * @param start : 출발지
	 * @param temp : 임시 목적지
	 * @param end : 목적지
	 */
	public static void hanoi(int num, int start, int temp, int end) {
		if(num == 1) {
			sb.append(start).append(" ").append(end).append("\n");
			return;
		}
		
		// A B C 3개의 기둥이 있다.
		// N개의 원판을 A에서 C로 옮긴려고 한다.
		
		// (N - 1)개의 원판을 A에서 B로 옮긴다.
		hanoi(num - 1, start, end, temp);
		
		// 1개의 원판을 A에서 C로 옮긴다.
		sb.append(start).append(" ").append(end).append("\n");
		
		// (N - 1)개의 원판을 B에서 C로 옮긴다.
		hanoi(num - 1, temp, start, end);
	}
}
