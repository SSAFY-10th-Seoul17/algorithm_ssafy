package boj;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

//16397번
public class 탈출 {
	private static int N;
	private static int T;
	private static int G;
	private static boolean possible = false;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		
		bfs();
		
	}

	private static void bfs() {
		
		Deque<Integer> dq = new ArrayDeque<>(); // 현재 숫자
		dq.offer(N);
		int[] count = new int[100000]; // 누른 횟수
		count[N] = 1;
		
		while(!dq.isEmpty()) {
			int num = dq.poll();
			
			//최대 T번
			if(count[num] <= T+1 && num == G) {
				possible = true;
				break;
			}
			if(count[num] == T+1) {
				continue;
			}
			
			//A버튼
			//num+1
			if(num+1 <= 99999 && count[num+1] == 0) {
				count[num+1] = count[num] + 1;
				dq.offer(num+1);
			}
			//B버튼
			//2배 후 최대자리수-1
			if(num > 0 && num*2 <= 99999) {
				int temp = num*2; // 2배
				int digit = 1;
				while(temp % Math.pow(10, digit) != temp) { //최고자리수 구하기
					digit++;
				}
				temp -= Math.pow(10, digit-1); // 최고자리수 -1
				if(count[temp] == 0) {
					count[temp] = count[num] + 1;
					dq.offer(temp);
				}
			}
			
		}// end of while
		
		if(possible) System.out.println(count[G]-1);
		else System.out.println("ANG");
		
	}
}
