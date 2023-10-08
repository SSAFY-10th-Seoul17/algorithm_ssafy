import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int M;
	private static int X;
	private static int Y;
	private static ArrayList<ArrayList<Home>> graph;
	private static int[] dist;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			graph.add(new ArrayList<Home>());
		}
		for (int i = 0; i < M; i++) { // 집 도로 잇기
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
			graph.get(A).add(new Home(B,dist));
			graph.get(B).add(new Home(A,dist));
		}
		giveTteock(Y); // 떡 돌리기
		
	} // end of main
	private static void giveTteock(int home) {
		if(!djikstra(home)) {
			System.out.println(-1);
			return;
		}
		int day = 1;
		int total = 0;
		Arrays.sort(dist);
		for(int i = 1; i<N;i++) {
			if(total+dist[i]*2>X) {
				total = 0;
				i--;
				day++;
				continue;
			}
			total+=2*dist[i];
		}
		System.out.println(day);
		
	}
	private static boolean djikstra(int home) {
		PriorityQueue<Home> pq = new PriorityQueue<>((h1,h2)->h1.dist-h2.dist);
		dist = new int[N];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[home]=0;
 		pq.offer(new Home(home,0));
 		while(!pq.isEmpty()) {
			Home cur = pq.poll();
			if(cur.dist > dist[cur.num]) continue;
			for (Home nxt:graph.get(cur.num)) {
				int nxtDist = dist[cur.num]+nxt.dist;
				if(nxtDist < dist[nxt.num]) {
					dist[nxt.num] = nxtDist;
					pq.offer(new Home(nxt.num,nxtDist));
				}
			}
		}
		return isRoundPossible() ? true:false;
	}
	private static boolean isRoundPossible() {
		for(int d:dist) {
			if(d == Integer.MAX_VALUE || 2*d>X) {
				return false;
			}
		}
		return true;
	}
	static class Home{
		int num;
		int dist;
		public Home(int num, int dist) {
			super();
			this.num = num;
			this.dist = dist;
		}
	}
} // end of class
