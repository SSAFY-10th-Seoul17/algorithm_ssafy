import java.io.*;
import java.util.*;

/*
* 최소한의 이동 횟수 -> 최단거리 => BFS 사용
* 메모 : 움직이는 경우의 수 8가지를 잘 구분해서 메모리가 덮어쓰지 않게 새로운 객체(int배열)를 잘 선언하며 사용하면 된다
* */
public class boj12761 {
	static int A, B, N, M;
	static Queue<int[]> queue = new ArrayDeque<>();
	static boolean[] visited = new boolean[100001];
	static int res = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// input
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// solution
		BFS();

		// output
		System.out.println(res);
	}

	static void BFS() {
		queue.offer(new int[] { N, 0 });
		visited[N] = true;

		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			if(now[0] == M) {
				res = res > now[1] ? now[1] : res;
				continue;
			}
			for(int type=1; type<=8; type++) {
				if(check(now[0], type)) {
					move(now, type);
				}
			}
		}
	}

	static boolean check(int now, int type) {
		switch(type) {
			case 1:
				return (now + 1 >= 0 && now + 1 <= 100000 && !visited[now+1]);
			case 2:
				return (now - 1 >= 0 && now - 1 <= 100000 && !visited[now-1]);
			case 3:
				return (now + A >= 0 && now + A <= 100000 && !visited[now+A]);
			case 4:
				return (now - A >= 0 && now - A <= 100000 && !visited[now-A]);
			case 5:
				return (now + B >= 0 && now + B <= 100000 && !visited[now+B]);
			case 6:
				return (now - B >= 0 && now - B <= 100000 && !visited[now-B]);
			case 7:
				return (now * A >= 0 && now * A <= 100000 && !visited[now*A]);
			default:
				return (now * B >= 0 && now * B <= 100000 && !visited[now*B]);
		}
	}

	static void move(int[] now, int type) {
		switch(type) {
			case 1:
				queue.offer(new int[] { now[0]+1, now[1]+1 });
				visited[now[0]+1] = true;
				break;
			case 2:
				queue.offer(new int[] { now[0]-1, now[1]+1 });
				visited[now[0]-1] = true;
				break;
			case 3:
				queue.offer(new int[] { now[0]+A, now[1]+1 });
				visited[now[0]+A] = true;
				break;
			case 4:
				queue.offer(new int[] { now[0]-A, now[1]+1 });
				visited[now[0]-A] = true;
				break;
			case 5:
				queue.offer(new int[] { now[0]+B, now[1]+1 });
				visited[now[0]+B] = true;
				break;
			case 6:
				queue.offer(new int[] { now[0]-B, now[1]+1 });
				visited[now[0]-B] = true;
				break;
			case 7:
				queue.offer(new int[] { now[0]*A, now[1]+1 });
				visited[now[0]*A] = true;
				break;
			default:
				queue.offer(new int[] { now[0]*B, now[1]+1 });
				visited[now[0]*B] = true;
				break;
		}
	}
}
