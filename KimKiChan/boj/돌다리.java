package boj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

//12761번
public class 돌다리 {
	private static int a;
	private static int b;
	private static int n;
	private static int m;
	private static int[] dx;

	// 번호 0 - 100000
	// 동규 : N, 주미 :M
	// 스카이콩콩 A, B 
	// 이동 +1, -1, A만큼 좌우, B만큼 좌우, A배 , B배
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		dx = new int[] {1, -1, a, -a, b, -b};
		
		bfs();

	}// end of main

	private static void bfs() {
		int[] moved = new int[100001];
		moved[n] = 1;
		
		Deque<Integer> dq = new ArrayDeque<>();
		dq.offer(n);
		
		while(!dq.isEmpty()) {
			int now = dq.poll();
			
			if(now == m) {
				break;
			}
			
			for(int i = 0; i < dx.length; i++) {
				int nx = now + dx[i];
				if(nx <= 100000 && nx >= 0 && moved[nx] == 0) {
					moved[nx] = moved[now]+1;
					dq.offer(nx);
				}
			}
			// * A
			if(now*a <= 100000 && moved[now*a] == 0) {
				moved[now*a] = moved[now]+1;
				dq.offer(now*a);
			}
			// * B
			if(now*b <= 100000 && moved[now*b] == 0) {
				moved[now*b] = moved[now]+1;
				dq.offer(now*b);
			}

		}
		System.out.println(moved[m]-1);
		
	}// end of bfs
	
}// end of class
