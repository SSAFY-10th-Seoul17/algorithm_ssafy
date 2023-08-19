import java.io.*;
import java.util.*;

class Pair {
	public int node;
	public int dist;
	public Pair(int a, int b){
		node = a;
		dist = b;
	}
}

public class Main {
	static int n;
		
	static ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
	static boolean[] visited;
	static Pair start;
	
	// 현재 start에 저장된 노드에서 가장 먼 점과 거리를 Pair로 반환한다.
	static Pair dfs() {
		Stack<Pair> stack = new Stack<>();
		stack.push(start);
		
		visited = new boolean[n + 1];
		visited[start.node] = true;
		
		Pair ans = new Pair(-1, -1);
		
		while(!stack.isEmpty()) {
			Pair now = stack.pop();

			boolean hasNext = false;
			for (Pair next : graph.get(now.node)) {
				next = new Pair(next.node, next.dist);
				if (visited[next.node])
					continue;

				next.dist += now.dist;
				visited[next.node] = true;;
				stack.push(next);

				hasNext = true;
			}
			if (!hasNext && ans.dist < now.dist) {
				ans = now;
			}
		}
		
		return ans;
	}
	
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		n = Integer.valueOf(br.readLine());
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<Pair>()); // 0 번 인덱스 포함
		}
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int node = Integer.valueOf(st.nextToken());
			ArrayList<Pair> temp = graph.get(node);

			int a, b;
			while ((a = Integer.valueOf(st.nextToken())) != -1) {
				b = Integer.valueOf(st.nextToken());
				temp.add(new Pair(a, b));
			}
			graph.add(temp); // 1 ~ n 번 인덱스의 그래프
		}
		
		for (int i = 1; i <= n; i++) {
			if (graph.get(i).size() == 1) {
				start = new Pair(i, 0);
				break;
			}
		}
		
		Pair endPoint = dfs();
		start = endPoint;
		start.dist = 0;
		Pair anotherEnd = dfs();
		
		System.out.println(anotherEnd.dist);
	} // end of main	
}
