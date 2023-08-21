import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * [boj] 1436. 영화감독 숌
 */
public class boj1436 {
	static int n;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(br.readLine());
		
		// brute-force
		int count = 0;
		int num = 665;
		int loopNum;
//		while(count != n) {
//			num++;
//			String numStr = String.valueOf(num);
//			if(numStr.contains("666")) {
//				count++;
//			}
//		}
		while(count != n) {
			num++;
			loopNum = num;
			while(loopNum > 100) {
				if(loopNum % 1000 == 666) {
					count++;
					break;
				}					
				loopNum /= 10;
			}
		}
		
		System.out.println(num);
	}
}
