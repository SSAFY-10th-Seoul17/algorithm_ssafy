import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	
	static int N, maxWeight;
	static List<Integer> weight;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());  // 에너지 구슬 개수 
		weight = new ArrayList<>();  // 에너지 구슬 무게 
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			weight.add(Integer.parseInt(st.nextToken()));
		}
		
		calcWeight(N, 0);
		System.out.println(maxWeight);
		
	}
	
	public static void calcWeight(int n, int sum) {
		if (n == 2) {
			maxWeight = Math.max(maxWeight, sum);
			return;
		}
		
		for (int i = 1; i < n-1; i++) {
			int w = weight.get(i-1) * weight.get(i+1);
			int value = weight.get(i);
			weight.remove(i);
			calcWeight(n-1, sum+w);
			weight.add(i, value);
		}
		
	}
	

}
