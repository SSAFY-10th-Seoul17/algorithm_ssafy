import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class bj16947 {
	
	private static boolean[] cycle;
	private static boolean[] isVisited;
	private static int n;
	private static ArrayList<Integer>[] subway;
	private static int[] distance;

	// 역 51개, 구간 51개, 순환선, 지선
	// 입력 : 역의 개수 N
	// 		역과 역을 연결하는 구간의 정보
	// 같은 구간이 여러번 주어지지 않는다, 역은 1번부터 N번, 두 역 사이의 경로가 항상 존재하는 노선만 입력으로 주어짐
	// 해결
	// 1. 순환선에 속하는 역 구하기
	// 2. 역과 순환선 사이의 최소 거리 구하기
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// 역의 수 n
		n = Integer.parseInt(br.readLine());
		// 순환선
		cycle = new boolean[n+1];
		// 간선 정보
		subway = new ArrayList[n+1];
		for(int i = 0; i <= n; i++) {
			subway[i] = new ArrayList<Integer>();
		}
		
		
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			subway[a].add(b); // a -> b
			subway[b].add(a); // b -> a
		}
		
		//순환선인지 체크
		isVisited = new boolean[n+1];
		for(int i = 1; i <= n; i++) {
			Arrays.fill(isVisited, false);
			searchCycle(i, i, 0);
		}		
		
		distance = new int[n+1];
		Arrays.fill(distance, -1);
		// 최소 거리 구하기
		minDist();
		
		//출력
		for(int i = 1; i <= n; i++) {
			sb.append(distance[i]).append(" ");
		}
		System.out.println(sb.toString());
		
	}// end of main

	private static void minDist() {
		Deque<Integer> q = new ArrayDeque<>();
		// 순환선 내부의 역 거리 = 0
		for(int i = 1; i <= n; i++) {
			if(cycle[i]) {
				q.offer(i);
				distance[i] = 0;
			}
		}
		
		while(!q.isEmpty()) {
			int station = q.poll();
			for( int i : subway[station]) {// 연결된 역
				if(distance[i] == -1) { // 순환선 내부가 아닐 경우, 방문한 적이 없을 경우
					q.offer(i);
					// 현재 역에서 거리 +1
					distance[i] = distance[station] + 1;
				}	
			}
		}// end of q
	
	} // end of minDist

	private static void searchCycle(int start, int node, int count) {
		if(start == node && count > 1) { // 다시 돌아왔을 경우
			cycle[start] = true;
			return;
		}
		isVisited[node] = true;
		for(int station : subway[node]) {
			if(!isVisited[station]) { // 다음 역
				searchCycle(start, station, count+1);		
			}else if(station == start && count > 1) { // 순환
				searchCycle(start, station, count);
			}
		}
	}// end of searchCycle

} // end of class
