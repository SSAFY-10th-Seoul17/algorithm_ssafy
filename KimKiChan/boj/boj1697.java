import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj1697 {
	static int n, k;
	static int[] visited; // 방문 횟수
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		visited = new int[100001]; // 0 <= n <= 100,000
		
		//걷기: +-1, 텔레포트 : *2
		//n -> k 최소 이동, k는 n의 좌 우 어디에든 있을 수 있다
		//예) 5 17 -> 5 *2 -1 *2 -1 총 4번
		int cnt = bfs(n);
		
		System.out.println(cnt);
		
	}// end of main
	
	
	public static int bfs(int node) {
		visited[node] = 1;
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.add(node);
		int pos;
		
		while(queue.isEmpty() == false) {
			pos = queue.remove();
			if(pos == k) { // 도착하면 끝
				return visited[pos]-1;
			}
			
			if(pos-1 >= 0 && visited[pos-1] == 0) {// 좌로 1 이동
				visited[pos-1] = visited[pos]+1;
				queue.add(pos-1);
			}
			if(pos+1 <= 100000 && visited[pos+1] == 0) { // 우로 1 이동
				visited[pos+1] = visited[pos]+1;
				queue.add(pos+1);
			}
			if(pos*2 <= 100000 && visited[pos*2] == 0) { // 텔레포트 *2
				visited[pos*2] = visited[pos]+1;
				queue.add(pos*2);
			}			
		}
		
		return 0;
		
	} // end of bfs

    
}// end of class
