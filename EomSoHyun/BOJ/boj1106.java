import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static class City {
		int cost;
		int num;
		
		public City(int cost, int num) {
			super();
			this.cost = cost;
			this.num = num;
		}

	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int c = Integer.parseInt(st.nextToken());  // 늘려야 하는 고객 최소 인원 수 
		int n = Integer.parseInt(st.nextToken());  // 도시 수 
		City[] cities = new City[n];
		int[] dp = new int[100001];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int cost = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			cities[i] = new City(cost, num);
		}
		
		for (int i = 0; i < cities.length; i++) {
			for (int j = 1; j < dp.length; j++) {
				if (j-cities[i].cost >= 0) {
					dp[j] = Math.max(dp[j], dp[j-cities[i].cost] + cities[i].num);
				}
			}
		}
		
		int minCost = 0;
		for (int i = 0; i < dp.length; i++) {
			if (dp[i] >= c) {
				minCost = i;
				break;
			}
		}
		
		System.out.println(minCost);
		
	}

}
