import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

//14699번
public class 관악산등산 {
	
	// 쉼터
	private static class Rest{
		int num;
		int height;
		public Rest(int num, int height) {
			super();
			this.num = num;
			this.height = height;
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		//쉼터의 개수 N
		int N = Integer.parseInt(st.nextToken());
		//쉼터 사이를 연결하는 길의 수 M
		int M = Integer.parseInt(st.nextToken());
		//각 쉼터의 높이
		ArrayList<Rest> rest = new ArrayList<>();
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 1; i <= N; i++) {
			rest.add(new Rest(i, Integer.parseInt(st.nextToken())));
		}
		//높이순으로 정렬
		Collections.sort(rest, (a, b) -> b.height - a.height);
		
		//두 쉼터를 연결하는 길
		ArrayList<Integer>[] route = new ArrayList[N+1];
		for(int i = 0; i <= N; i++) {
			route[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			route[from].add(to);
			route[to].add(from);
		}

		br.close(); // 입력 끝
		
		//Corea가 n번 쉼터에서 출발해서 산을 오를 때 최대로 방문할 수 있는 쉼터의 개수
		int[] visit = new int[N+1];
		
		for(int i = 0; i < N; i++) {
			int current = rest.get(i).num;
			for(int j = 0; j < route[current].size(); j++) {
				int before = route[current].get(j);
				if(visit[current] < visit[before]) {
					visit[current] = visit[before];
				}
			}
			visit[current]++;		
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			sb.append(visit[i]).append("\n");
		}
		System.out.println(sb);
		
	}// end of main

}// end of class
