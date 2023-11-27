import java.io.*;
import java.util.*;

public class boj14226_이모티콘 {
	static int S;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		S = Integer.parseInt(br.readLine());

		// solution
		int res = bfs();

		// output
		System.out.println(res);
	}

	static int bfs() {
		Queue<int[]> queue = new ArrayDeque<>();
		int[][] dp = new int[2*S+1][S+1]; // 문자열길이, 클립보드길이 = 시간
		queue.offer(new int[] { 1,0,0 }); // 문자열길이, 클립보드길이, 시간
		for(int i=0; i<=S; i++) Arrays.fill(dp[i], Integer.MAX_VALUE);

		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			if(now[2] < dp[now[0]][now[1]]) {
				dp[now[0]][now[1]] = now[2];
				queue.offer(new int[] { now[0],now[0],now[2]+1 });
				if(now[1] != 0) queue.offer(new int[] { now[0]+now[1],now[1],now[2]+1 });
				if(now[0]-1 > 0) queue.offer(new int[] { now[0]-1,now[1],now[2]+1 });
			}
		}

		return getMin(dp);
	}

	static int getMin(int[][] dp) {
		int min = Integer.MAX_VALUE;
		for(int time : dp[S]) {
			min = Math.min(min, time);
		}
		return min;
	}
}

