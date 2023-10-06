import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static final int INF = 52;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] dist = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			Arrays.fill(dist[i], INF);
		}
		
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a==-1 && b==-1) break;
			dist[a][b] = dist[b][a] = 1;
		}
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				if(i==k)continue;
				for (int j = 1; j <= N; j++) {
					if(j==k || j==i)continue;
					int d = dist[i][k]+dist[k][j];
					if(dist[i][j]>d) {
						dist[i][j] = d;
					}
				}
			}
		}
		int minDist = INF;
		ArrayList<Integer> nodes = new ArrayList<>();
		
		for (int i = 1; i <= N; i++) {
			int max = 0;
			for (int j = 1; j <= N; j++) {
				if(i==j)continue;
				if(max<dist[i][j]) {
					max = dist[i][j];
				}
			}
			if(minDist>max) {
				minDist = max;
				nodes.clear();
				nodes.add(i);
			}else if(minDist==max) {
				nodes.add(i);
			}
		}
	StringBuilder sb = new StringBuilder();
	sb.append(minDist).append(" ").append(nodes.size()).append("\n");
	for(int node:nodes) {
		sb.append(node).append(" ");
	}
	System.out.println(sb.toString());
	} // end of main
} // end of class
