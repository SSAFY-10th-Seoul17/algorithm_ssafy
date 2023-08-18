import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int F, S, G, U, D;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		F = Integer.parseInt(st.nextToken());  // 총 층
		S = Integer.parseInt(st.nextToken());  // 현재 층
		G = Integer.parseInt(st.nextToken());  // 목표 층
		U = Integer.parseInt(st.nextToken());  // 위로 올라가는 층
		D = Integer.parseInt(st.nextToken());  // 아래로 내려가는 층
		
		int[] floor = new int[F+1];  // 이동 횟수 저장
		Queue<Integer> q = new LinkedList<>();
		q.offer(S);
		floor[S] = 1;
		while (!q.isEmpty()) {
			int w = q.poll();
			if (w == G) {  // 목표 층 도착
				 break;
			}
			if (0 < w + U && w + U <= F && floor[w + U] == 0) {
				q.offer(w + U);
				floor[w + U] = floor[w] + 1;
			}
			if (0 < w - D && w - D <= F && floor[w - D] == 0) {
				q.offer(w - D);
				floor[w - D] = floor[w] + 1;
			}
		}
		
		if (floor[G] == 0) {
			System.out.println("use the stairs");
		} else {
			System.out.println(floor[G]-1);
		}
		
	}

}
