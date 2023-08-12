import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj24230 {
	static int[] target; //목표 색 
	static ArrayList<Integer>[] tree;
	static int cnt;
	static int[] colored; // 색칠
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 정점의 개수
		int n = Integer.parseInt(br.readLine());
		
		int numColor = 0; // 색의 종류
		// 목표 색
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		colored = new int[n+1];
		target = new int[n+1];
		for(int i = 1; i <= n; i++) {
			int num = Integer.parseInt(st.nextToken());
			target[i] = num;
			if(numColor < num) {
				numColor = num;
			}
		}
		
		//간선 입력
		tree = new ArrayList[n+1];
		for(int i = 0; i <= n; i++) {
			tree[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < n-1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int min = Math.min(a, b);
			int max = Math.max(a, b);
			tree[min].add(max); //  작은 수 -> 큰수
		}
		
		cnt = 0;
		// 위에서 아래로 색 
		dfs(1);
		System.out.println(cnt);
		
	}// end of Main

	private static void dfs(int node) {
		if(colored[node] != target[node] && target[node] != 0) {//색 비교
			colored[node] = target[node];//본인
			cnt++; // 색 추가
			for(int i : tree[node]) {//자식
				dfsColor(i, target[node]);
			}
		}
		for(int i : tree[node]) {
			dfs(i);
		}
	}
	
	private static void dfsColor(int node, int c) {
		colored[node] = c;
		for(int i: tree[node]) {
			dfsColor(i, c);
		}
	}
}
