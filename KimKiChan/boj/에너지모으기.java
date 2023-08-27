import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 에너지모으기 {
	private static int n;
	private static int[] energy;
	private static int maxEnergy;
	private static boolean[] isExtracted;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
		energy = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < n; i++) {
			energy[i] = Integer.parseInt(st.nextToken());			
		}// end of 입력
		
		maxEnergy = 0;
		isExtracted = new boolean[n+1];
		findEnergy(0, 0);
		
		System.out.println(maxEnergy);
		
		
	}// end of main

	private static void findEnergy(int sum, int count) {
		if(count == n-2) {
			maxEnergy = maxEnergy < sum ? sum : maxEnergy;
			return;
		}
		
		for(int i = 1; i < n-1; i++) {
			if(isExtracted[i]) continue;
			isExtracted[i] = true;
			
			int w1 = 1;
			int w2 = 1;
			for(int j = i-1; j >= 0; j--) {
				if(!isExtracted[j]) {
					w1 = energy[j];
					break;
				}
			}
			for(int j = i+1; j <= n; j++) {
				if(!isExtracted[j]) {
					w2 = energy[j];
					break;
				}
			}
			
			findEnergy(sum + w1*w2, count+1);
			
			isExtracted[i] = false;			
		}
		
	}
}// end of class
