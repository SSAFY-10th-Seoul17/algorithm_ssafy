import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj5014 {
	// 건물 총 F층, 목표: G층, 현재 : S층
	// U버튼 : 위로 U, D버튼 : 아래로 D -> 해당 층이 없으면 움직이지 않는다
	// 버튼 최소 누르는 횟수
	// 엘리베이터를 이용해서 갈 수 없다면 "use the stairs" 출력
	static int[] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int f = Integer.parseInt(st.nextToken()); // 최대 높이
		int s = Integer.parseInt(st.nextToken()); // 현재 높이
		int g = Integer.parseInt(st.nextToken()); // 목표 높이
		int u = Integer.parseInt(st.nextToken()); // 위로 
		int d = Integer.parseInt(st.nextToken()); // 아래로
		visited = new int[f+1];
		
		bfs(f, s, g, u, d);
		
	}

	private static void bfs(int f, int s, int g, int u, int d) {
		Queue<Integer> elevator = new ArrayDeque<>();
		elevator.offer(s);
		visited[s] = 1;
		while(!elevator.isEmpty()) {
			int cur = elevator.poll();
			if(cur == g) {
				System.out.println(visited[cur]-1);
				return;
			}
			if(cur + u <= f) {
				if(visited[cur+u] == 0) {
					visited[cur+u] = visited[cur]+1;
					elevator.offer(cur+u);
				}
			}
				
			if(cur - d >= 1) {
				if(visited[cur-d] == 0) {
					visited[cur-d] = visited[cur]+1;
					elevator.offer(cur-d);
				}
			}
			
		}// end of while
		System.out.println("use the stairs");
		
		return;
		
	}// end of bfs

}