import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//30024번
public class 옥수수밭 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		//1. 겉부분을 우선순위 큐에 넣는다
		PriorityQueue<Corn> pq = new PriorityQueue<>((Corn a, Corn b) -> a.num < b.num ? 1:-1);
		
		//옥수수밭
		int[][] cornField = new int[n][m];
		boolean[][] searched = new boolean[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < m; j++) {
				cornField[i][j] = Integer.parseInt(st.nextToken());
				if(i==0 || i==n-1 || j==0 || j==m-1) {
					pq.offer(new Corn(cornField[i][j], i, j));
					searched[i][j] = true;
				}
			}
		}

		//2.가장 높은 옥수수를 뽑고 그 주위를 넣는다
		StringBuilder sb = new StringBuilder();
		int[][] dxdy = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
		//k그루의 옥수수
		int k = Integer.parseInt(br.readLine());
		while(k-- > 0) {
			Corn corn = pq.poll();
			sb.append(corn.x+1).append(" ").append(corn.y+1).append("\n");
			
			for(int dir = 0; dir < 4; dir++) {
				int nx = corn.x + dxdy[dir][0];
				int ny = corn.y + dxdy[dir][1];
				if(nx>=0 && ny>=0 && nx<n && ny<m) {
					if(searched[nx][ny]) continue;
					searched[nx][ny] = true;
					pq.offer(new Corn(cornField[nx][ny], nx, ny));
				}
			}
			
		}
		
		System.out.println(sb.toString());
	}
	
	private static class Corn{
		int num;
		int x;
		int y;
		public Corn(int num, int x, int y) {
			super();
			this.num = num;
			this.x = x;
			this.y = y;
		}
		
	}
}
